Estimation = function(projectTreeData, epicTreeData, contextPath) {
	var ITERATION_TAB = 1;
	var ESTIMATION_TAB = 2;

	var selectedTab = ITERATION_TAB;
	var selectedProjects;
	var selectedEpics;

	prepareForTree(projectTreeData);
	prepareForTree(epicTreeData);

	var iterationTab = function() {
		$("#epicTab").attr('class', '');
		$("#itTab").attr('class', 'active');
		$("#iterationForm").show();
		$("#epicForm").hide();
		selectedTab = ITERATION_TAB;
	}
	iterationTab();

	$("#downloadForm")
			.submit(
					function(e) {
						var formData = $(this).serializeArray();
						var reportName = '';

						// then append Dynatree selected 'checkboxes':
						var tree = $("#projectTreeBox").dynatree("getTree");
						formData = formData.concat(tree.serializeArray());

						// and/or add the active node as 'radio button':
						if (tree.getActiveNode()) {
							formData.push({
								name : "activeNode",
								value : tree.getActiveNode().data.id
							});
						}

						if (selectedTab == ESTIMATION_TAB) {
							if (!selectedEpics) {
								showWarningDialog("Select epics, please!");
								return false;
							}
							reportName = 'EpicReport.xlsx'

							var epicTree = $("#epicTreeBox").dynatree("getTree");
							formData = formData.concat(epicTree.serializeArray());
							// and/or add the active node as 'radio button':
							if (epicTree.getActiveNode()) {
								formData.push({
									name : "activeNode",
									value : epicTree.getActiveNode().data.id
								});
							}

						} else {
							var iteration = $("#iterationComboBox").val();
							var teams = $("#teamComboBox").val();
							if (!selectedProjects) {
								showWarningDialog("Select projects, please!");
								return false;
							} else if (teams == null) {
								showWarningDialog("Select Team, please!");
								return false;
							} else if (iteration == null) {
								showWarningDialog("Select Sprint, please! If sprint list is empty select project don't divide on sprints or you don't have permissions to see them.");
								return false;
							}
							reportName = "IterationReport.xlsx";
						}

						$("#dialog").attr("title", "Generating in process").text(
								"Please wait while report is being generated. Downloading will start automatically.").dialog({
							modal : true
						}).show();

						var url = contextPath + "/";
						url += reportName;
						url += '?' + jQuery.param(formData) + "&type=" + selectedTab;
						$(this).attr("action", url);
						return true;
					});

	$("#hiddenFrame").load(function(e) {
		var text = $("#hiddenFrame").contents().find("body").text();
		if (text) {
			$("#dialog").attr("title", "Error").text(text).dialog({
				modal : true
			}).show();
		}
	});

	$("#projectTreeBox").dynatree({
		checkbox : true,
		selectMode : 3,
		children : projectTreeData,
		onSelect : function(select, node) {
			selectedProjects = $.map(node.tree.getSelectedNodes(), function(node) {
				return node.data.id;
			});

			// clear iterations combobox
			$("#iterationComboBox").empty();
			var nodeList = node.tree.getSelectedNodes();
			var arr = [];
			// generate array of iterations by
			// selected projects from JSON
			for ( var i = 0, l = nodeList.length; i < l; i++) {
				if (nodeList[i].data.iterations != null && nodeList[i].data.children == null) {
					var iterationsString = '"' + nodeList[i].data.iterations + '"';
					var iterationArray = iterationsString.split(",");
					iterationArray[0] = iterationArray[0].replace('"', '');
					iterationArray[iterationArray.length - 1] = iterationArray[iterationArray.length - 1].replace('"', '');
					arr = arr.concat(iterationArray).unique().sort().reverse();
				}
			}

			// add iterations into combobox
			if (arr.length > 0) {
				var labels = " ";
				$.each(arr, function() {
					labels += $("#iterationComboBox").html() + '<option>' + this + '</option>';
				});
				$("#iterationComboBox").html(labels);
				return false;
			}

			// clear combobox on all unchecked
			// projects
			if (selectedProjects.length < 1) {
				$("#iterationComboBox").empty();
			}
		},
		onDblClick : function(node, event) {
			node.toggleSelect();
		},
		onKeydown : function(node, event) {
			if (event.which == 32) {
				node.toggleSelect();
				return false;
			}
		},
		cookieId : "dynatree-Cb3",
		idPrefix : "dynatree-Cb3-"
	});

	$("#epicTreeBox").dynatree({
		checkbox : true,
		selectMode : 3,
		children : epicTreeData,
		onSelect : function(select, node) {
			selectedEpics = $.map(node.tree.getSelectedNodes(), function(node) {
				return node.data.id;
			});
		},
		onDblClick : function(node, event) {
			node.toggleSelect();
		},
		onKeydown : function(node, event) {
			if (event.which == 32) {
				node.toggleSelect();
				return false;
			}
		},
		cookieId : "dynatree-Cb3",
		idPrefix : "dynatree-Cb3-"
	});

	$("#itTab").click(iterationTab);

	$("#epicTab").click(function() {
		document.getElementById('itTab').setAttribute('class', '');
		document.getElementById('epicTab').setAttribute('class', 'active');
		document.getElementById('iterationForm').style.display = 'none';
		document.getElementById('epicForm').style.display = '';
		selectedTab = ESTIMATION_TAB;
	});

	function prepareForTree(obj) {
		if (obj instanceof Array) {
			for ( var i = 0; i < obj.length; i++) {
				replaceNameRecursively(obj[i]);
			}
		} else {
			replaceNameRecursively(obj);
		}

	}

	function replaceNameRecursively(obj) {
		if (!obj.title) {
			obj.title = obj.name;
		}

		if (!obj.key) {
			obj.key = obj.id;
		}
		if (obj.children) {
			var children = obj.children;
			for ( var i = 0; i < children.length; i++) {
				replaceNameRecursively(children[i]);
			}
		}
	}
}
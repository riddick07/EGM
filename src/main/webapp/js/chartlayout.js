ChartLayout = function(url, reportTab, passedReportType) {
	var blockMessage = "Please, wait while report will be ready";

	$("#refresh").click(function() {
		if (ifCheck()) {
			refreshReport();
		}
	});

	function refreshReport() {
		PageBlock.block(blockMessage);
		var chart = '#report';
		var reportType = "";
		if (passedReportType) {
			reportType = passedReportType;
			chart = '#' + passedReportType + 'Container';
		} else {
			reportType = $("#reportType").val();
		}
		startChartGenerating(chart);
		var ifAll = reportType == "ALLREPORTS";

		if (ifAll) {
			$("#liveTables").css("margin-left", "15%");
			$("#reviewTable").css("margin-left", "15%");
		} else {
			$("#liveTables").css("margin-left", "");
			$("#reviewTable").css("margin-left", "");
		}

		Server.ajax({
			url : url + '/chartData.dt?tab=' + reportTab,
			type : 'post',
			data : {
				'reportType' : reportType,
				'team' : getTeam(),
				'iteration' : getIteration()
			},
			success : function(data) {
				var obj = dataToObj(chart, data);

				$.each(obj, function() {
					var chartType = this['chartName'];
					var chartData = this['dataArray'];
					var chartId = this['chartId'];
					var id = chartId + "" + chartType;
					if (passedReportType) {
						$('#' + passedReportType + 'Container').append('<div id="' + id + '"></div>');
					} else {
						$('.report').append('<div id="' + id + '"></div>');
					}

					if (reportType == "BURNDOWN" || (chartType == "BURNDOWN" && ifAll)) {
						createChart(chartData, {
							isStacked : false,
							vAxis : {
								title : "Burndown",
								minValue : 0
							}
						}, false, id);
					}

					if (reportType == "CUMULATIVE" || (chartType == "CUMULATIVE" && ifAll)) {
						createChart(chartData, {
							title : chartId,
							isStacked : true,
							vAxis : {
								title : "Cumulative Flow"
							}
						}, false, id);
					}

					if (reportType == "STORYPOINTS" || (chartType == "STORYPOINTS" && ifAll)) {
						if (reportTab == "Review") {
							drawLineChart(chartData, {
								vAxis : {
									title : "Story Points"
								}
							}, id);
						} else {
							drawColumnChart(chartData, {
								vAxis : {
									title : "Story Points"
								},
								height : 400
							}, id);
						}
					}

					if (reportType == "EFFORT" || (chartType == "EFFORT" && ifAll)) {
						createChart(chartData, {
							vAxis : {
								title : 'Effort Entry'
							},
							isStacked : false
						}, false, id);

						if ($("#usersCombo").val() != '') {
							effortByUser();
						}
					}
				});
				PageBlock.unblock();
			},
			error : function() {
				PageBlock.unblock();
				$(chart).progressbar("destroy");
			}
		});

		if (reportType == "STORYPOINTS" || (ifAll)) {

			$("#changedItems").html("");
			$("#addedItems").html("");
			$("#addedPoints").html("");

			if (reportTab == "Review") {
				PageBlock.block(blockMessage);
				Server.ajax({
					url : url + "/previousIterations.dt",
					type : 'post',
					data : {
						'iteration' : getIteration()
					},
					success : function(data) {
						$("#reviewTable").css("display", "block");
						churnReportTableModel.buildTable($("#addedPoints"), churnReportTableModel.addedPoints(data), "Added", url
								+ "/churnReviewData.dt", {
							'reportType' : reportType,
							'team' : getTeam(),
							'iteration' : getIteration()
						});
					}
				});
			} else {
				$("#liveTables").css("display", "block");
				PageBlock.block(blockMessage);
				PageBlock.block(blockMessage);
				churnReportTableModel.buildTable($("#changedItems"), churnReportTableModel.changedItems(), "Changed", url
						+ "/changedItemsData.dt", {
					'reportType' : reportType,
					'team' : getTeam(),
					'iteration' : getIteration()
				});

				churnReportTableModel.buildTable($("#addedItems"), churnReportTableModel.addedItems(), "Added", url + "/addedItemsData.dt",
						{
							'reportType' : reportType,
							'team' : getTeam(),
							'iteration' : getIteration()
						});
			}
		}
	}

	$("#usersCombo").change(function() {
		if ($("#usersCombo").val() != '' && teamCheck() && sprintCheck()) {
			effortByUser();
		}
	});

	// Start generating data for Effort Chart report by user
	function effortByUser() {
		PageBlock.block(blockMessage);
		var chart = '#reportUser';
		var reportType = $("#reportType").val();
		startChartGenerating(chart);
		Server.ajax({
			url : url + '/chartData.dt?tab=' + reportTab,
			type : 'post',
			data : {
				'reportType' : 'EFFORTBYUSER',
				'team' : getTeam(),
				'iteration' : getIteration(),
				'selectedUser' : $("#usersCombo option:selected").text()
			},
			success : function(data) {
				var obj = dataToObj(chart, data);
				var chartType = obj[0]['chartName'];
				var chartData = obj[0]['dataArray'];
				if (reportType == "EFFORT" && $("#usersCombo").val() != ""
						|| (chartType == "EFFORT" && reportType == "ALLREPORTS" && $("#usersCombo").val() != "")) {
					createChart(chartData, {
						isStacked : false
					}, true, "reportUser");
				}
				PageBlock.unblock();
			},
			error : function() {
				$(chart).progressbar("destroy");
				PageBlock.unblock();
			}
		});
	}

	function dataToObj(chart, data) {
		$("#reportType").removeAttr("disabled");
		$("#usersCombo").removeAttr("disabled");
		$(chart).progressbar("destroy");
		try {
			return jQuery.parseJSON(data);
		} catch (e) {
			$(chart).html("No Data Found");
			return;
		}
	}

	// Set options (if haven't coming) to chart and create chart
	function createChart(data, options, secondDiv, id) {
		options = options || {
			height : 400,
			vAxis : {
				title : "Hours"
			},
			hAxis : {
				title : "Days"
			}
		};

		if (!options.vAxis) {
			options.vAxis = {
				title : "Hours"
			};
		}

		if (!options.hAxis) {
			options.hAxis = {
				title : "Days"
			};
		}
		if (options.isStacked == undefined) {
			options.isStacked = true;
		}

		if (!options.height) {
			options.height = 400;
		}

		drawAreaChart(data, options, id);
	}

	// On ComboBox case changing
	$("#reportType").change(function() {
		onCheckLabel();
		$("#sprintComboBox").val("");
		$("#repName").html($("#reportType option:selected").text());
		$("#changedItems").html("");
		$("#addedItems").html("");
		$("#addedPoints").html("");
		$("#liveTables").hide();
		$("#reviewTable").hide();
		$('#report').html("");
		$('#reportUser').html("");
		$("label", ".left-bar").removeClass("btn-info");
		setTextToTeamLabel();
		setTextToSprintLabel();
		if ($("#reportType").val() == "EFFORT") {
			$("#memDiv").removeClass("hidden");
		} else {
			$("#memDiv").addClass("hidden");
		}
	});

	// On ComboBox case changing
	$("#sprintComboBox").change(function() {
		$("label[name='sprintGroupLabel']").removeClass("btn-info");
		setTextToSprintLabel($("#sprintComboBox").val());
		callOnLabelCheck();
	});

	// On labels check actions
	$("label").click(function() {
		var name = $(this).attr("name");

		if (name == 'sprintGroupLabel') {
			$("#sprintComboBox").val("");
		}

		$("label[name='" + name + "']").removeClass("btn-info");
		$(this).addClass("btn-info");
		callOnLabelCheck();
		return true;
	});

	// Check on label selecting
	function callOnLabelCheck() {
		onCheckLabel();
		if (sprintCheck()) {
			setTextToSprintLabel(sprintCheck());
		}

		if (teamCheck()) {
			setTextToTeamLabel($(".btn-info[name='teamGroupLabel']").text());
		}

		if (teamCheck() && sprintCheck()) {
			$('#reportLabel').html('');
			refreshReport();
		}
		return true;
	}

	// if team check return true
	function teamCheck() {
		return $(".btn-info[name='teamGroupLabel']").length != 0;
	}

	// if sprint check return true
	function sprintCheck() {
		var label = $(".btn-info[name='sprintGroupLabel']");
		var cboxVal = $('#sprintComboBox').val();
		var btnchk = label.length != 0;
		var combocheck = cboxVal != "";
		if (btnchk)
			return label.text();
		if (combocheck)
			return cboxVal;
	}

	// if all parameters check return true
	function ifCheck() {
		if (!teamCheck() && !sprintCheck()) {
			showErrorDialog("Please, select sprint and team");
			return false;
		}
		if (!teamCheck() && sprintCheck()) {
			showErrorDialog("Please, select team");
			return false;
		}

		if (!sprintCheck() && teamCheck()) {
			showErrorDialog("Please, select sprint");
			return false;
		}
		return true;
	}

	// get selected iteration
	function getIteration() {
		var val = "";
		var btnVal = $(".btn-info[name='sprintGroupLabel']");
		var cbox = $("#sprintComboBox option:selected");
		if (btnVal.length != 0) {
			val = btnVal;
		}
		if (cbox.val() != ""  && cbox.val()) {
			val = cbox.val();
		}

		var iterations = $.map(val, function(n) {
			var tmp = $(n);
			var id = tmp.attr('id');
			var name = tmp.text();
			var state = tmp.attr('state');
			var startDate = tmp.attr('startDate');
			var endDate = tmp.attr('endDate');
			if (cbox.val() != "" && cbox.val()) {
				id = cbox.attr('id');
				name = cbox.text();
				state = cbox.attr('state');
				endDate = cbox.attr('endDate');
				startDate = cbox.attr('startDate');
			}
			return {
				"id" : id,
				"startDate" : startDate,
				"endDate" : endDate,
				"name" : name,
				"state" : state
			};
		});

		return JSON.stringify(iterations[0]);
	}

	// get selected team
	function getTeam() {
		return $(".btn-info[name='teamGroupLabel']").text();
	}

	function setTextToSprintLabel(data) {
		var str = "";
		if (data != null) {
			str = data;
		}
		document.getElementById("sprintLabel").innerHTML = "Active Sprint: " + str;
	}

	function setTextToTeamLabel(data) {
		var str = "";
		if (data != null) {
			str = data;
		}
		document.getElementById("teamLabel").innerHTML = "Team: " + str;
	}

	function onCheckLabel() {
		$('#reportLabel').html('Select Sprint and Team');
	}

	function startChartGenerating(chart) {
		$(chart).html("");
		$(chart).progressbar({
			value : 100
		});
	}

	function drawAreaChart(array, options, id) {
		var data = google.visualization.arrayToDataTable(array);
		var chart = new google.visualization.AreaChart(document.getElementById(id));
		chart.draw(data, options);
	}
	function drawColumnChart(array, options, id) {
		var data = google.visualization.arrayToDataTable(array);
		var chart = new google.visualization.ColumnChart(document.getElementById(id));
		chart.draw(data, options);
	}
	function drawLineChart(array, options, id) {
		var data = google.visualization.arrayToDataTable(array);
		var chart = new google.visualization.LineChart(document.getElementById(id));
		chart.draw(data, options);
	}
};
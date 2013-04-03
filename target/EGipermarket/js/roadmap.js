Roadmap = function(url) {

	// On labels check actions
	$("label").click(function() {
		var project = document.getElementById('projectCheckBox').checked;
		var customer = document.getElementById('customerCheckBox').checked;
		var team = document.getElementById('teamCheckBox').checked;
		refreshReport(project, customer, team);
	});

	function refreshReport(project, customer, team) {
		PageBlock.block("Please, wait while projections will be ready");
		Server.ajax({
			url : url + "/projectionsData.dt",
			type : 'post',
			data : {
				'project' : project,
				'customer' : customer,
				'team' : team,
			},
			success : function(data) {
				var grid = $("#projectionsTable");
				grid.html('');

				var obj = dataToObj(data);
				var title = [];
				var tableData = [];

				$.each(obj, function() {
					var o = new Object();
					$.each(this['def'], function(key, val) {
						o[key] = val;
					});
					$.each(this['map'], function(key, val) {
						title.push(key);
						o[key] = val;
					});
					tableData.push(o);
				});

				title = title.unique().sort();
				var model = generateTable(title);
				projectionsTableModel.buildTable(grid, model, tableData);
				PageBlock.unblock();
			},
			error : function() {
				PageBlock.unblock();
			}
		});
	}

	function generateTable(cols) {
		var project = document.getElementById('projectCheckBox').checked;
		var customer = document.getElementById('customerCheckBox').checked;
		var team = document.getElementById('teamCheckBox').checked;

		var width = "100%";
		var columns = [];

		if (project) {
			columns.push(projectionsTableModel.createColumn("projectName", "Project", width));
		}
		if (team) {
			columns.push(projectionsTableModel.createColumn("teamName", "Team", width));
		}

		if (customer) {
			columns.push(projectionsTableModel.createColumn("customerName", "Customer", width));
		}

		$.each(cols, function() {
			var date = new Date();
			date.setTime(this);
			var formated = date.getDate() + " " + date.getMonthNameShort() + " " + date.getFullYear();
			columns.push(projectionsTableModel.createColumn(this, formated, "50%", "number", 'number'));
		});

		
		return columns;
	}

	function dataToObj(data) {
		try {
			return jQuery.parseJSON(data);
		} catch (e) {
			return e;
		}
	}
};
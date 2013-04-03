Standup = function(url, teams) {
	refreshTable = function() {
		if ($(".btn-info[name='teamGroupLabel']").length != 0 && $(".btn-info[name='sprintGroupLabel']").length != 0) {
			$("#items").html("");
			PageBlock.block("Please, wait while report will be ready");

			var iterations = $.map($(".btn-info[name='sprintGroupLabel']"), function(n) {
				var tmp = $(n);
				return {
					"id" : tmp.attr('id'),
					"startDate" : tmp.attr('startDate'),
					"endDate" : tmp.attr('endDate'),
					"name" : tmp.text(),
					"state" : tmp.attr('state')
				};
			});

			standupPageTableModel.buildTable($("#items"), standupPageTableModel.items(), "Items", url + '/standupItems.dt', {
				'team' : $(".btn-info[name='teamGroupLabel']").text(),
				'iteration' : JSON.stringify(iterations[0]),
				'member' : $("#users").val()
			});
		}
	};

	$("label").click(function() {
		var teamLabel = $(".btn-info[name='teamGroupLabel']");
		if (teamLabel.length != 0) {
			$("#users").html("");
			$.each(teams, function() {
				if (this.name == teamLabel.text()) {
					var opts = "<option></option>";
					var members = this.members.sort();
					$.each(members, function() {
						opts += $("#users").html() + '<option>' + this + '</option>';
					});
					$("#users").html(opts);
				}
			});
		}
		refreshTable();
	});

	$("#users").change(function() {
		if ($(".btn-info[name='teamGroupLabel']").length == 0 && $(".btn-info[name='sprintGroupLabel']").length == 0) {
			showErrorDialog("Please, select both sprint and team");
		} else {
			refreshTable();
		}
	});
};
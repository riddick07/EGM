Projection = function(pageParameters) {
	if (pageParameters == null) {
		pageParameters.projects = {
			"v1Project" : [ "Projection A", "Projection B" ], // TODO: Change
			// on parameters
			// with id's
			"sprints" : [ {
				"startSprint" : "test",
				"endSprint" : "test name",
			} ],
		};
	}

	var projRows, startDateRows, endDateRows = "";
	var emptyOption = '<option value=""></option>';
	$.each(pageParameters.v1Project, function() {
		projRows += '<option value="' + this + '">' + this + '</option>';
	});
	$.each(pageParameters.sprints, function() {
		// TODO: Maybe need a validation by corresponding on check
		startDateRows += '<option value="' + this.startSprint + '">' + this.startSprint + '</option>';
		endDateRows += '<option value="' + this.endSprint + '">' + this.endSprint + '</option>';
	});
	$("#v1Project").html(emptyOption + projRows);
	$("#startSprint").html(emptyOption + startDateRows);
	$("#endSprint").html(emptyOption + endDateRows);
};
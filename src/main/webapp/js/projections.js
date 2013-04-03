ProjectionsObj = function(filters, projectionsList, contextPath) {

	if (filters == null)
		filters = [ {
			"id" : "ID1",
			"name" : "Filer TEST 1"
		}, {
			"id" : "ID2",
			"name" : "Filer TEST 2"
		}, ];

	if (projectionsList == null)
		projectionsList = [ {
			"id" : "ID1",
			"name" : "Projection A",
			"project" : "Mainsail",
			"description" : "First descr",
			"startDate" : 1360061066735,
			"endDate" : 1361875466735
		}, {
			"id" : "ID2",
			"name" : "Projection B",
			"project" : "Mainsail",
			"description" : "Second descr",
			"startDate" : 1360061066735,
			"endDate" : 1361875466735
		}, ];

	if (filters.length > 0) {
		var tabs = " ";
		$.each(filters, function() {
			tabs += '<ul class="nav"> <li id="' + this.id + '"><a>' + this.name + '</a></li></ul>';
		});
		$("#tabsDiv").html(tabs);
	}

	var rows = " ";
	if (projectionsList.length > 0) {
		$.each(projectionsList,	function() {
							rows += '<tr id="'
									+ this.id
									+ '"class="success"><td>'
									+ this.name
									+ '</td><td>'
									+ this.project
									+ '</td>	<td>'
									+ this.description
									+ '</td><td>'
									+ new Date(this.startSprint)
									+ '</td>	<td>'
									+ new Date(this.endSprint)
									+ '</td><td><select> <option value=""></option><option value="Edit">Edit</option>	<option value="Delete">Delete</option>	<option value="Generate">Generate</option>	</select></td>	</tr>';
						});
		$("#projectionsViewTable").html(rows);
	}
}
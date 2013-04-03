Risks = function(pageParameters, projectionsList) {

	if (pageParameters == null)
		pageParameters = [ {
			"id" : "ID1",
			"projection" : "Projection A",
		}, {
			"id" : "ID2",
			"projection" : "Projection B",
		}, ];
	
	if (pageParameters.length > 0) {
		var rows = "";
		var emptyOption = '<option value=""></option>';
		$.each(pageParameters, function() {
			rows += '<option value="' + this.id + '">' + this.projection + '</option>';
		});
		$("#customerProjectionCombo").html(emptyOption + rows);
	}
	// TODO: Add on click "Add bundle" the action
	
	var rows = " ";
		$.each(projectionsList,	function() {
							rows += '<tr id="'
									+ this.id
									+ '"class="success"><td>'
									+ this.name
									//TODO: change on a true parameters
									+ '</td><td><select> <option value=""></option><option value="Edit">Edit</option>	<option value="Delete">Delete</option>	<option value="Generate">Generate</option>	</select></td>	</tr>';
						});
	$("#bundleTable").html(rows);
	//TODO: implement "add risk action"
};
var projectionsTableModel = {

	createColumn : function(id, name, width, editType, formatter) {
		column = {};
		column.id = id;
		column.name = name || this.id;
		column.sortable = true;
		column.hidden = false;
		column.resizable = true;
		column.editable = true;
		column.edittype = editType || 'string';
		column.formatter = formatter || 'string';
		column.width = width;
		return column;
	},

	buildTable : function(element, model, data) {
		var colModel = [], colNames = [], naming = element.attr('id'), tableId = naming + "Table", navigationId = naming + "Navigation";

		element.append('<table id="' + tableId + '"></table><div id="' + navigationId + '"></div>');
		navigationId = "#" + navigationId;
		tableId = "#" + tableId;
		var table = $(tableId);
		window.table = table;

		$.each(model, function(index, value) {
			colNames.push(value.name);
			colModel.push({
				name : value.id,
				sortable : value.sortable,
				editable : value.editable,
				edittype : value.edittype,
				formatter : value.formatter,
				formatoptions : {
					disabled : false
				},
				editoptions : {
					value : "1:0",
					defaultValue : "1"
				},
				width : value.width
			});
		});

		colModel.push({
			name : 'total',
			index : 'total',
			width : '70%',
			align : "right",
			sorttype : 'number',
			formatter : 'number'
		});
		colNames.push('Total');

		var scrollOff = 15;
		if (navigator.userAgent.toLowerCase().indexOf('chrome') > -1) {
			scrollOff = 42;
		}

		table.jqGrid({
			datatype : 'jsonstring',
			datastr : data,
			colNames : colNames,
			colModel : colModel,
			scrollOffset : scrollOff,
			height : 'auto',
			rowNum : 1000000,
			
			autowidth : true, // width 100%
			rownumbers : true, // add column with numeration
			loadonce : true, // load data and working local with sorting and
			gridview : true,
			altRows : true,
			loadComplete : function() {
				$("input.ui-pg-input").css({
					'font-size' : '10px',
					height : '3ex',
					width : '3em',
					margin : '0em'
				});
				$("select.ui-pg-selbox").css({
					'font-size' : '10px',
					height : '5ex',
					width : '7em',
					margin : '0em'
				});
				$("div.ui-jqgrid-pager").css({
					height : '7ex'
				});

				var valueObj = {};

				$.each(colModel, function(i, model) {
					var sum = '';
					var val = model.name;
					if (parseInt(val)) {
						sum = table.jqGrid('getCol', i+1, false, 'sum');
					} 
					valueObj[val] = sum;
				});

				table.jqGrid('addRowData', 0, valueObj);

				var rows = $(tableId + " tr[role='row'].jqgrow").length;
				for ( var id = 0; id <= rows; id++) {
					var dataObj = table.jqGrid('getRowData', id);
					var sum = 0;

					for ( var i in dataObj) {
						var parsed = parseFloat(dataObj[i]);
						if (!isNaN(parsed)) {
							sum += parsed;
						}
					}
					dataObj = table.jqGrid('setCell', id, "total", sum);
				}

			},

			loadError : function(jqXHR, textStatus, errorThrown) {
				if (jqXHR['status'] == 401) {
					window.location.href = jqXHR['responseText'];
				}

				var errorObject = jQuery.parseJSON(jqXHR.responseText);
				if (errorObject.error) {
					showErrorDialog(errorObject.error);
				} else {
					showErrorDialog(errorThrown);
				}
			},

			jsonReader : {
				repeatitems : false,
				id : "Id",
				root : function(obj) {
					return obj;
				},
				page : function(obj) {
					return 1;
				},
				total : function(obj) {
					return 1;
				},
				records : function(obj) {
					return obj.length;
				}
			}
		});

		table.jqGrid('navGrid', navigationId, {
			edit : false,
			add : false,
			del : false,
			view : false,
			cloneToTop : false
		});
	}
};
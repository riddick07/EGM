var standupPageTableModel = {

	doubleFormatter : function(cellvalue, options, rowObject) {
		var val = cellvalue + "";
		val = val.match(/(.*\...)/);

		if (!val) {
			return cellvalue;
		}
		return val[0];
	},

	createColumn : function(id, name, width, editType, formatter) {
		column = {};
		column.id = id;
		column.name = name || this.id;
		column.sortable = true;
		column.hidden = false;
		column.resizable = true;
		column.editable = true;
		column.edittype = editType;
		column.formatter = formatter;
		column.width = width;
		return column;
	},

	items : function() {
		return [ this.createColumn("formatedDate", "Last Change", "25%"), 
		         this.createColumn("id", "Item", "20%"),
		         this.createColumn("name", "Title", "110%"), 
		         this.createColumn("status", "Status", "25%"),
		         this.createColumn("storyPoints", "Story Points", "20%"),
		         this.createColumn("toDo","Todo","20%")];
	},

	buildTable : function(element, model, tableCapture, url, post) {
		var colNames = [], colModel = [], naming = element.attr('id'), tableId = naming + "Table", navigationId = naming + "Navigation";

		element.append('<table id="' + tableId + '"></table><div id="' + navigationId + '"></div>');
		navigationId = "#" + navigationId;
		tableId = "#" + tableId;
		var table = $(tableId);

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

		table.jqGrid({
			url : url,
			datatype : 'json',
			mtype : 'POST',
			postData : post,
			caption : tableCapture,
			colNames : colNames,
			colModel : colModel,
			height : 'auto',
			rowNum : 10,
			rowList : [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
			pager : navigationId,
			autowidth : true, // width 100%
			rownumbers : true, // add column with numeration
			loadonce : true, // load data and working local with sorting and
			// etc.
			viewrecords : true,
			gridview : true,

			loadComplete : function() {
				PageBlock.unblock();
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
			},

			loadError : function(jqXHR, textStatus, errorThrown) {
				if (jqXHR['status'] == 401) {
					window.location.href = jqXHR['responseText'];
				}

				PageBlock.unblock();
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
		table.jqGrid('inlineNav', navigationId);
	}
};
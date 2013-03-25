/*File contains util functions*/

Server = {};
Server.ajax = function(options) {
	var errorFunc = options.error;
	options.error = function(jqXHR, textStatus, errorThrown) {
		if (jqXHR['status'] == 401) {
			window.location.href = jqXHR['responseText'];
		}

		var errorObject = jQuery.parseJSON(jqXHR.responseText);
		if (errorObject.error) {
			showErrorDialog(errorObject.error);
		} else {
			showErrorDialog(errorThrown);
		}

		if (errorFunc) {
			errorFunc(jqXHR, textStatus, errorThrown);
		}
	};

	$.ajax(options);
};

PageBlock = {};

PageBlock.calls = 0;
PageBlock.block = function(message) {
	if (!message) {
		message = "Please, wait";
	}
	if (this.calls == 0) {
		$("#blockDiv").css("display", "table-cell");
		$("#blockDiv h1").html(message);
	}
	PageBlock.calls++;
};

PageBlock.unblock = function() {
	PageBlock.calls--;
	if (PageBlock.calls == 0) {
		$("#blockDiv").css("display", "none");
	}
};

function showWarningDialog(text) {
	return showDialog("Warning", text);
}

function showErrorDialog(text) {
	var d = showDialog("Error", text);
	d.parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
	return d;
}

function showDialog(headerText, contentText) {
	var dialogId = "dialog" + Math.round(Math.random(100) * 100);
	return $("<div></div>").attr("id", dialogId).dialog({
		title : headerText,
		width : 450,
		height : 200,
		modal : true,
		buttons : {
			"Close" : function() {
				$(this).dialog("close");
			}
		},
		close : function(event, ui) {
			$("#" + dialogId).remove();
		}

	}).html(contentText);

}

Array.prototype.unique = function() {
	var newArr = [], origLen = this.length, found, x, y;

	for (x = 0; x < origLen; x++) {
		found = undefined;
		for (y = 0; y < newArr.length; y++) {
			if (this[x] === newArr[y]) {
				found = true;
				break;
			}
		}
		if (!found)
			newArr.push(this[x]);
	}
	return newArr;
};

Date.prototype.getMonthName = function(lang) {
	lang = lang && (lang in Date.locale) ? lang : 'en';
	return Date.locale[lang].month_names[this.getMonth()];
};

Date.prototype.getMonthNameShort = function(lang) {
	lang = lang && (lang in Date.locale) ? lang : 'en';
	return Date.locale[lang].month_names_short[this.getMonth()];
};

Date.locale = {
	en : {
		month_names : [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November',
				'December' ],
		month_names_short : [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ]
	}
};
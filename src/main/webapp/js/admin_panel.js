AdminPanel = function() {

	if ("${model.message}".trim() != "" && "${model.message}" != null) {
		showErrorDialog("${model.message}");
	}

	if ("${model.message}" != "" && ($("#password").val().trim() != "")
			&& ($("#login").val().trim() != "")) {
		showErrorDialog("${model.message}");
	}

	if (typeof String.prototype.trim !== 'function') {
		String.prototype.trim = function() {
			return this.replace(/^\s+|\s+$/g, '');
		};
	}

	var ACCOUNT_TAB = 1;
	var CONNECTION_TAB = 2;

	var accountTab = function() {
		$(".admin-panel").addClass('hidden');
		$("#accountTab").removeClass('hidden');
		selectedTab = ACCOUNT_TAB;
	}

	var connectionTab = function() {
		$(".admin-panel").addClass('hidden');
		$("#connectionTab").removeClass('hidden');
		selectedTab = CONNECTION_TAB;
	}

	$("#accountTabLink").on('click', accountTab());
	$("#connectionTabLink").on('click', connectionTab());

	accountTab();

	$("#connectionForm").submit(
			function(e) {
				if ($("#passwordToSite").val().trim() == ""
						&& $("#loginToSite").val().trim() == ""
						&& $("#retype_passwordToSite").val().trim() == "") {
					showErrorDialog("Login and password can't be empty!");
					return false;
				} else if ($("#loginToSite").val().trim() == "") {
					showErrorDialog("Please enter login!");
					return false;
				} else if ($("#password").val().trim() == "") {
					showErrorDialog("Please enter password!");
					return false;
				}
				if (validate('connectionForm', 'mail')) {
					$("#connectionForm").submit();
				}
			});

	$("#accountForm")
			.submit(
					function(e) {
						if ($("#password").val().trim() == ""
								&& $("#login").val().trim() == ""
								&& $("#retype_password").val().trim() == "") {
							showErrorDialog("Login, User name and password can't be empty!");
							return false;
						} else if ($("#login").val().trim() == "") {
							showErrorDialog("Please enter login!");
							return false;
						} else if ($("#password").val().trim() == "") {
							showErrorDialog("Please enter password!");
							return false;
						} else if ($("#retype_password").val().trim() == "") {
							showErrorDialog("Please enter your name!");
							return false;
						}
						if (validate('accountForm', 'mail')) {
							$("#accountForm").submit();
						}

					});

	function validate(form_id, email) {
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var address = document.forms[form_id].elements[email].value;
		if (reg.test(address) == false) {
			showErrorDialog('Invalid Email Address');
			return false;
		} else {
			return true;
		}
	}

}
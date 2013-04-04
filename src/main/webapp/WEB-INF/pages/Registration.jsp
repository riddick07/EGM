<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to World of markets!</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<jsp:include page="/WEB-INF/pages/includes/css.jsp" />
<jsp:include page="/WEB-INF/pages/includes/jslib.jsp" />
<script type="text/javascript">
	window.onload = function() {
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

		$("#regBtn")
				.click(
						function() {
							if ($("#password").val().trim() == ""
									&& $("#login").val().trim() == ""
									&& $("#name").val().trim() == "") {
								showErrorDialog("Login, User name and password can't be empty!");
								return false;
							} else if ($("#login").val().trim() == "") {
								showErrorDialog("Please enter login!");
								return false;
							} else if ($("#password").val().trim() == "") {
								showErrorDialog("Please enter password!");
								return false;
							}
							if ($("#name").val().trim() == "") {
								showErrorDialog("Please enter your name!");
								return false;
							}
							validate('regForm', 'mail');

							$("#regForm").submit();
							return true;
						});
	};

	function validate(form_id, email) {
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var address = document.forms[form_id].elements[email].value;
		if (reg.test(address) == false) {
			showErrorDialog('Invalid Email Address');
			return false;
		}
	}
</script>
</head>
<body class="body-t">
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
				</a>
				<a class="brand login-p" href="${pageContext.request.contextPath}/pages/Login.vw"> </a>
				<div class="nav-collapse collapse"></div>
			</div>
		</div>
	</div>
	<div class="container pagination-centered">
		<div class="login-header span10">
			<h1>Registration form</h1>
		</div>
	</div>
	<div class="m-top">
		<form id="regForm" class="m-top pagination-centered" action="${pageContext.request.contextPath}/pages/Registration.vw"
			method="post">
			<input id="login" name="login" class="m-top reg" type="text" placeholder="Username">
			<input id="password" name="password" class="reg" type="password" placeholder="Password">
			<input id="retype_password" name="retype_password" class="reg" type="password" placeholder="Retype Password">
			<input id="name" name="name" class="reg" placeholder="Name" type="text">
			<input id="surname" name="surname" class="reg" placeholder="Surname" type="text">
			<input id="mail" name="mail" class="reg" placeholder="Mail" type="text">
			<input id="phone" name="phone" class="reg" placeholder="Phone" type="text">
			<div class="pagination-centered">
				<button id="regBtn" class="btn reg">Register</button>
			</div>
		</form>
	</div>
	<hr class="login-page-footer">
	<jsp:include page="/WEB-INF/pages/includes/footer.jsp" />
</body>
<style id="holderjs-style" type="text/css">
input.reg {
	margin-top: 15px;
	padding-left: 0;
	padding-right: 0;
	width: 260px;
	padding-left: 0;
}
</style>
</html>
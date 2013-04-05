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
							} else if ($("#name").val().trim() == "") {
								showErrorDialog("Please enter your name!");
								return false;
							}
							if (validate('regForm', 'mail')) {
								$("#regForm").submit();
							}
							return true;
						});
	};

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
</script>
</head>
<body class="body-t">
	<div class="navbar-wrapper">
		<div class="container">
			<div class="navbar navbar-inverse">
				<div class="navbar-inner">
					<a class="brand" href="#">E-Gipermarket</a>

					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="active"><a href="${pageContext.request.contextPath}/pages/Login.vw">Home</a></li>
							<li><a href="${pageContext.request.contextPath}/pages/">About</a></li>
							<li><a href="${pageContext.request.contextPath}/pages/">Contact</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container pagination-centered offset6 span6">
		<div class="login-header">
			<h2>Registration form</h2>
		</div>
	</div>
	<div class="m-top  pagination-centered offset6">
		<form id="regForm" class="m-top" action="${pageContext.request.contextPath}/pages/Registration.vw" method="post">
			<input id="login" name="login" class="m-top reg" placeholder="Username"  type="text">
			<input id="password" name="password" class="reg" placeholder="Password"  type="password">
			<input id="retype_password" name="retype_password" class="reg" placeholder="Retype Password"  type="password">
			<input id="name" name="name" class="reg" placeholder="Name" type="text">
			<input id="surname" name="surname" class="reg" placeholder="Surname" type="text">
			<input id="mail" name="mail" class="reg" placeholder="Mail" type="text">
			<input id="phone" name="phone" class="reg" placeholder="Phone" type="text">
			<button id="regBtn" class="btn reg">Register</button>
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

div.pagination-centered {
	width: 290px;
}
</style>
</html>
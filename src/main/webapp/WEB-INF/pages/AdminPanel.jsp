<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<title>Home page</title>
<jsp:include page="/WEB-INF/pages/includes/css.jsp" />
<jsp:include page="/WEB-INF/pages/includes/jslib.jsp" />
<jsp:include page="/WEB-INF/pages/includes/header.jsp" />
</head>
<script type="text/javascript">
	window.onload = function() {
		var adminPanel = new AdminPanel();
		
		var roles = "${model.roles}";
		roles = roles.replace("[", "").replace("]", "").split(", ");
		roleComboBoxInit(roles);
		
		function roleComboBoxInit(roles) {
			var combo = $("#role");
			var value = combo.val();
			$.each(roles, function() {
				value += '<option id="' + this + '">' + this + '</option>';
			});
			combo.innerHTML = value;
		}
	};
</script>
<body>
	<div class="container marketing contact">
		<div class="tabbable">
			<!-- Only required for left/right tabs -->
			<ul class="nav nav-tabs">
				<li id="accountTabLink" class="active ap-tab"><a href="#tab1" data-toggle="tab">Account settings</a></li>
				<li id="connectionTabLink" class="ap-tab"><a href="#tab2" data-toggle="tab">Connection</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab1">
					<p>Your account settings</p>
				</div>
				<div class="tab-pane" id="tab2">
					<p>Set connections to your site</p>
				</div>
			</div>
		</div>
	</div>
	<div id="accountTab" class="admin-panel">
		<div class="m-top offset6 pagination-centered">
			<form id="accountForm" class="m-top" action="${pageContext.request.contextPath}/pages/AdminPanel.vw" method="post">
				<input id="formType" class="m-top reg hidden">
				<input id="login" name="login" class="m-top reg" placeholder="Username" type="text">
				<input id="password" name="password" class="reg" placeholder="Password" type="password">
				<input id="retype_password" name="retype_password" class="reg" placeholder="Retype Password" type="password">
				<select id="role" name="role" class="reg">
					<option></option>
				</select>
				<input id="name" name="name" class="reg" placeholder="Name" type="text">
				<input id="surname" name="surname" class="reg" placeholder="Surname" type="text">
				<input id="mail" name="mail" class="reg" placeholder="Mail" type="text">
				<input id="phone" name="phone" class="reg" placeholder="Phone" type="text">
				<button id="accountFormBtn" class="btn reg">Save</button>
			</form>
		</div>
	</div>

	<div id="connectionTab" class="admin-panel hidden">
		<div class="m-top  pagination-centered offset6">
			<form id="connectionForm" class="m-top" action="${pageContext.request.contextPath}/pages/AdminPanel.vw" method="post">
				<input id="loginToSite" name="loginToSite" class="m-top reg" placeholder="Login to ypur site" type="text">
				<input id="passwordToSite" name="passwordToSite" class="reg" placeholder="Password to your site" type="password">
				<input id="retype_passwordToSite" name="retype_passwordToSite" class="reg" placeholder="Input password to your site"
					type="password">
				<select id="siteType" name="siteType" class="reg">
					<option id="wordpress" value="Wordpress">Wordpress</option>
				</select>
				<button id="connectionBtn" class="btn reg">Register</button>
			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/pages/includes/footer.jsp" />
</body>
<style type="text/css">
select input.reg {
	margin-top: 15px;
	padding-left: 0;
	padding-right: 0;
	width: 290px;
	padding-left: 0;
}

div.pagination-centered {
	width: 260px;
}
</style>
</html>
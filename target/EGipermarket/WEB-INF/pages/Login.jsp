<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to V1Toolkit!</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<jsp:include page="/WEB-INF/pages/includes/css.jsp" />
<jsp:include page="/WEB-INF/pages/includes/jslib.jsp" />
<script type="text/javascript">
$().ready(function() {
	if ("${model.message}".trim() != "" && "${model.message}" != null){
		showErrorDialog("${model.message}");
	}

    if ("${model.message}" != ""&&($("#password").val().trim() != "")&&($("#login").val().trim() != "")) {
    	showErrorDialog("${model.message}");
    }

    if(typeof String.prototype.trim !== 'function') {
        String.prototype.trim = function() {
            return this.replace(/^\s+|\s+$/g, ''); 
        };
    }

    $("#loginBtn").click(
            function() {
                if ( ($("#password").val().trim() == "")&&($("#login").val().trim() == "")){
                    showErrorDialog("User name and password can't be empty!");
                    return false;
                }else if ($("#login").val().trim() == ""){
                    showErrorDialog("Please enter user name!");
                    return false;
                }else if ( $("#password").val().trim() == ""){
                    showErrorDialog("Please enter password!");
                    return false;
                }
                $("#loginForm").submit();
                return true;
    });
});
</script>
</head>
<body class="body-t">
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
				</a> <a class="brand login-p" href="${pageContext.request.contextPath}/pages/Login.vw"><img
					src="${pageContext.request.contextPath}/images/toolkit-head.png"></a>
				<div class="nav-collapse collapse"></div>
			</div>
		</div>
	</div>
	<div class="container pagination-centered">
		<div class="login-header span10">
			<h1>A tool for generating</h1>
			<h1>VersionOne reports.</h1>
		</div>
		<img src="${pageContext.request.contextPath}/images/product-logos.png" width="45%" />
	</div>
	<div class="m-top">
		<form id="loginForm" class="m-top pagination-centered" action="${pageContext.request.contextPath}/pages/Login.vw" method="post">
			<input id="login" name="login" class="m-top login-f" type="text" placeholder="Username" style="padding-left: 0; padding-right: 0"><br /> <input
				id="password" name="password" class="login-f" type="password" placeholder="Password" style="padding-left: 0; padding-right: 0"><br />
			<div class="pagination-centered">
				<button id="loginBtn" class="btn login-f">Log&nbsp;in</button>
			</div>
		</form>
	</div>
	<hr class="login-page-footer">
	<jsp:include page="/WEB-INF/pages/includes/footer.jsp" />
</body>
</html>
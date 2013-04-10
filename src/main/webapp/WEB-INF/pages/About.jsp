<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to world of markets!</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
<jsp:include page="/WEB-INF/pages/includes/css.jsp" />
<jsp:include page="/WEB-INF/pages/includes/jslib.jsp" />
<jsp:include page="/WEB-INF/pages/includes/header.jsp" />
<script type="text/javascript">
	$().ready(function() {
	});
</script>
</head>
<body>
	<div class="container marketing">
		<hr class="featurette-divider">
		<div class="featurette">
			<img class="featurette-image pull-right" src="${pageContext.request.contextPath}/images/browser-icon-chrome.png">

			<h2 class="featurette-heading">
				First featurette headling.
				<span class="muted">It'll blow your mind.</span>
			</h2>

			<p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod
				semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
		</div>

		<hr class="featurette-divider">

		<div class="featurette">
			<img class="featurette-image pull-left" src="${pageContext.request.contextPath}/images/browser-icon-firefox.png">

			<h2 class="featurette-heading">
				Oh yeah, it's that good.
				<span class="muted">See for yourself.</span>
			</h2>

			<p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod
				semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
		</div>

		<hr class="featurette-divider">
		<div class="featurette">
			<img class="featurette-image pull-right" src="${pageContext.request.contextPath}/images/browser-icon-safari.png">

			<h2 class="featurette-heading">
				And lastly, this one.
				<span class="muted">Checkmate.</span>
			</h2>

			<p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod
				semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
		</div>
		<hr class="featurette-divider">

	</div>



	<hr class="login-page-footer">
	<jsp:include page="/WEB-INF/pages/includes/footer.jsp" />



</body>
<style type="text/css">
</style>
</html>
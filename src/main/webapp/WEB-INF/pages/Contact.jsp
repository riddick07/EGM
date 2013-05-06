<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div class="container marketing contact">
		<div class="row">
			<div class="span4">
				<img data-src="holder.js/140x140" class="img-circle" alt="140x140" style="width: 140px; height: 140px;"
					src="${pageContext.request.contextPath}/images/partner.jpg">
				<h2>Our partners</h2>
				<p>Our world partners..</p>
				<p>
					<a href="#" class="btn">View details »</a>
				</p>
			</div>
			<!-- /.span4 -->
			<div class="span4">
				<img data-src="holder.js/140x140" class="img-circle" alt="140x140" style="width: 140px; height: 140px;"
					src="${pageContext.request.contextPath}/images/manager.jpg">
				<h2>Managers</h2>
				<p>Connecting managers..</p>
				<p>
					<a href="#" class="btn">View details »</a>
				</p>
			</div>
			<!-- /.span4 -->
			<div class="span4">
				<img data-src="holder.js/140x140" class="img-circle" alt="140x140" style="width: 140px; height: 140px;"
					src="${pageContext.request.contextPath}/images/dev.png">
				<h2>Developers</h2>
				<p>Contacting developers..</p>
				<p>
					<a href="#" class="btn">View details »</a>
				</p>
			</div>
			<!-- /.span4 -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/pages/includes/footer.jsp" />
</body>
</html>
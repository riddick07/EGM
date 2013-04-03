<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
			</a> <a class="brand" href="${pageContext.request.contextPath}/pages/Home.vw"><img src="${pageContext.request.contextPath}/images/toolkit-head.png"></a>
			<div id="h-navs" class="nav-collapse collapse">
				<ul class="nav hdr">
					<li><a href="${pageContext.request.contextPath}/pages/Home.vw">Home</a></li>
				</ul>
				<ul class="nav hdr">
					<li><a href="${pageContext.request.contextPath}/pages/ChartLayout.vw?chartType=LiveReport">Live Report</a></li>
				</ul>
				<ul class="nav hdr">
					<li><a href="${pageContext.request.contextPath}/pages/Estimations.vw">Estimations</a></li>
				</ul>
				<ul class="nav hdr">
					<li><a href="${pageContext.request.contextPath}/pages/ChartLayout.vw?chartType=Review">Review</a></li>
				</ul>
				<ul class="nav hdr">
					<li><a href="${pageContext.request.contextPath}/pages/Roadmap.vw">Roadmap</a></li>
				</ul>
                <ul class="nav hdr">
                    <li><a href="${pageContext.request.contextPath}/pages/Standup.vw">Standup</a></li>
                </ul>
                <ul class="nav hdr">
                    <li><a href="${pageContext.request.contextPath}/pages/Projections.vw">Projections</a></li>
                </ul>
				<ul class="nav pull-right">
					<li class="active m-top"><span style="color: cyan;" id="loginedUserLabel">${user},</span><a
						href="${pageContext.request.contextPath}/pages/Login.vw?logout=true" style="display: inline">Log&nbsp;out</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<style type="text/css">
ul.hdr {
	background-color: #2C2C2C;
	color: grey;
}

ul.hdr:HOVER {
	background-color: #04C;
	color: black;
}
</style>

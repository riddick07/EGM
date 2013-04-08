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
					<ul class="nav pull-right">
						<li class="active m-top"><span style="color: cyan;" id="loginedUserLabel">${user},</span> <a
								href="${pageContext.request.contextPath}/pages/Login.vw?logout=true" style="display: inline">Log&nbsp;out</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
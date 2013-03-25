<ul class="pager">
	<li><a onclick="onPaginationClick('prev')" class="previous" id="paginatorPrevious" href="">Previous</a></li>
	<li><a onclick="onPaginationClick('next')" class="next" id="paginatorNext" href="">Next</a></li>
</ul>
<script type="text/javascript">
	var pages = [ "Projection", "Customer", "Project", "Risks", "Roadmap", "SprintCap", "Maint" ];
	var contextPath = '${pageContext.request.contextPath}/pages/';

	function onPaginationClick(pg) {
		var pathArray = window.location.href.split("/");
		var curPageName = pathArray[pathArray.length - 1].split(".")[0];
		var curPageIndex = pages.indexOf(curPageName);
		if (curPageName == "Projection") {
			$("#paginatorPrevious").addClass("disabled");
			return;
		}
		if (curPageName == "Maint") {
			$("#paginatorNext").addClass("disabled");
			return;
		}
		$("#paginatorNext").removeClass("disabled");
		$("#paginatorPrevious").removeClass("disabled");
		if (pg == "prev")
			curPageIndex--;
		if (pg == "next")
			curPageIndex++;
		var reqestPath = "${pageContext.request.contextPath}/pages/" + pages[curPageIndex] + ".vw";
		location.href = reqestPath;
		console.log(reqestPath);
	}
</script>
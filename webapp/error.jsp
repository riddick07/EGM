<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
<style>
body,p {
	font-family: Tahoma;
	font-size: 10pt;
	padding-left: 30;
}

pre {
	font-size: 8pt;
}
</style>
</head>
<body>
		Request that failed: ${pageContext.errorData.requestURI}
		<br />
		Status code: ${pageContext.errorData.statusCode}
		<br />
		Exception: ${pageContext.errorData.throwable}
		<br />
</body>
</html>
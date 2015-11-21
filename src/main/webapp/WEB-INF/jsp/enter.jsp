<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security"
		   uri="http://www.springframework.org/security/tags" %>

<c:url var="loginURL" value="/login"/>

<c:set var="baseURL"
	   value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}"/>

<html>
<head>
	<title>PolyChat</title>
	<style>
		body {
			margin: 0;
			padding: 0;
			background: #dfdfdf;
		}

		.container {
			width: 480px;
			margin: 50px auto;
		}


	</style>
</head>
<body>
<div class="container">
	<form action="${loginURL}" method="post">
		<label>Enter your name</label><br/>
		<input type="text" name="username"/><br/>

		<input type="submit" value="Enter"/>
	</form>
</div>


</body>
</html>

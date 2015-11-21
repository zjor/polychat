<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec"
		   uri="http://www.springframework.org/security/tags" %>
<c:set var="baseURL"
	   value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}"/>

<sec:authentication property="principal.username" var="username"/>

<html>
<head>
	<title>PolyChat</title>
	<script src="${baseURL}/static/js/jquery-1.11.3.min.js"></script>
</head>
<body>
<h1>Welcome, ${username}</h1>
<div class="container">
	<div id="chat">

	</div>

	<div id="sender">
		<form action="${baseURL}/api/send" method="post">
			<input type="text" name="content"/>
			<input type="submit" value="Send">
		</form>
	</div>

</div>
<script>
$(function() {

	$.get('${baseURL}/api/messages', function(data) {
		data.forEach(function(message) {
			var m = $('<div></div>').append(message.owner.username + ": " + message.content);
			$('#chat').append(m);
		});
	});


});
</script>
</body>
</html>

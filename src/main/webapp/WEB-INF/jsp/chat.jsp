<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec"
		   uri="http://www.springframework.org/security/tags" %>
<c:set var="baseURL"
	   value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}"/>

<sec:authentication property="principal.username" var="username"/>
<!DOCTYPE html>
<html>
<head>
	<title>PolyChat</title>
	<!--Import Google Icon Font-->
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<!--Import materialize.css-->
	<link type="text/css" rel="stylesheet" href="${baseURL}/static/css/materialize.min.css" media="screen,projection"/>

	<!--Let browser know website is optimized for mobile-->
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

	<style>
		.brand-logo {
			margin-left: 24px;
		}

		.container {
			width: 480px;
			margin: 0 auto;
		}

		.margin-top {
			margin-top: 24px;
		}

	</style>

</head>
<body>
<main>

	<nav>
		<div class="nav-wrapper">
			<div class="brand-logo">PolyChat</div>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="#">${username}</a></li>
			</ul>
		</div>
	</nav>


	<div class="container margin-top">
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<div class="card-content">
						<div id="chat">

						</div>

						<div id="sender">
						</div>

					</div>
					<div class="card-action">
						<div class="row">
							<form action="${baseURL}/api/send" method="post">
								<input type="text" name="content"/>
								<button type="submit" class="btn">Send
									<i class="material-icons right">send</i>
								</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</main>
<footer>

</footer>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/js/materialize.min.js"></script>

<script>
	$(function () {

		$.get('${baseURL}/api/messages', function (data) {
			data.forEach(function (message) {
				var m = $('<div></div>').append(message.owner.username + ": " + message.content);
				$('#chat').append(m);
			});
		});


	});
</script>

</body>
</html>

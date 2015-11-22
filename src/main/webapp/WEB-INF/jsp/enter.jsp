<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security"
		   uri="http://www.springframework.org/security/tags" %>

<c:url var="loginURL" value="/login"/>

<c:set var="baseURL"
	   value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}"/>
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
		body {
			padding: 0;
			display: flex;
			min-height: 100vh;
			flex-direction: column;
		}

		main {
			flex: 1 0 auto;
		}

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
		</div>
	</nav>

	<div class="container margin-top">
		<div class="row">
			<div class="col s12 m12">
				<div class="card">
					<form action="${loginURL}" method="post">
						<div class="card-content">
							<span class="card-title">Please enter your name</span>

							<div class="row">
								<div class="input-field col s12">
									<input id="username" name="username" type="text" class="validate">
									<label for="username">Username</label>
								</div>
							</div>
							<div class="center">
								<input type="submit" class="btn" value="Enter"/>
							</div>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</main>

<footer class="page-footer">
	<div class="footer-copyright">
		<div class="container">© 2015 Copyright Text</div>
	</div>
</footer>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${baseURL}/static/js/materialize.min.js"></script>
</body>
</html>

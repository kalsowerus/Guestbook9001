<%--
  Created by IntelliJ IDEA.
  User: geroy
  Date: 21.09.2017
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ tag body-content="scriptless" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Guestbook</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/"><t:glyphicon glyphicon="book" /> Guestbook</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${not empty user}">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							   		aria-expanded="false">
								<c:if test="${user.admin}">
									<t:glyphicon glyphicon="star" />
								</c:if>
								<c:out value="${user.username}" /> <span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="/logout">Logout</a>
								</li>
							</ul>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="/login">Login</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
	<div class="container">
		<c:if test="${not empty error}">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
		<jsp:doBody />
	</div>
</body>
</html>

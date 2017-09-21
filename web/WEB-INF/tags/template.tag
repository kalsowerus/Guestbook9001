<%--
  Created by IntelliJ IDEA.
  User: geroy
  Date: 21.09.2017
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ tag body-content="scriptless" %>

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
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="/login">Login</a>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<jsp:doBody />
	</div>
</body>
</html>

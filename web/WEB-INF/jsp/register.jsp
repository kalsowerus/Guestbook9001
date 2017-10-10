<%--
  Created by IntelliJ IDEA.
  User: geroy
  Date: 21.09.2017
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
	<form action="/register" method="POST">
		<c:if test="${failed}">
			<div class="alert alert-danger" role="alert">invalid username or password</div>
		</c:if>
		<div class="form-group">
			<label for="username">Username</label>
			<input class="form-control" id="username" name="username" autofocus>
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" id="password" name="password">
		</div>
		<div class="form-group">
			<label for="password">Repeat Password</label>
			<input type="password" class="form-control" id="password_repeat" name="password_repeat">
		</div>
		<button type="submit" class="btn btn-default">Register</button>
	</form>
</t:template>

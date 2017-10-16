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
	<form action="/login" method="POST">
		<t:formGroup id="username" name="username" text="Username" type="text" autofocus="true" />
		<t:formGroup id="password" name="password" text="Password" type="password" />
		<button type="submit" class="btn btn-default">Login</button>
		<a href="/register" class="btn btn-link">Register</a>
	</form>
</t:template>

<%--
  Created by IntelliJ IDEA.
  User: geroy
  Date: 21.09.2017
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:template>
	<form:form action="/register" method="POST" modelAttribute="registerForm">
		<c:if test="${failed}">
			<div class="alert alert-danger" role="alert">invalid username or password</div>
		</c:if>
		<t:formGroup id="username" text="Username" type="text" autofocus="true" />
		<t:formGroup id="password" text="Password" type="password" />
		<t:formGroup id="passwordRepeat" text="Repeat Password" type="password" />
		<button type="submit" class="btn btn-default">Register</button>
	</form:form>
</t:template>

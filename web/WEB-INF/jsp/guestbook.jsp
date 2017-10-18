<%--
  Created by IntelliJ IDEA.
  User: geroy
  Date: 21.09.2017
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:template>
	<h1>Guestbook</h1>
	<c:if test="${not empty user}">
		<form:form method="POST" modelAttribute="guestbookForm">
			<input type="hidden" name="csrfToken" value="${csrfToken}" />
			<div class="form-group">
				<label for="text">Text</label>
				<textarea class="form-control" id="text" name="text" autofocus></textarea>
			</div>
			<t:formGroup id="text" text="Text" type="textarea" />
			<button type="submit" class="btn btn-default">Post</button>
		</form:form>
	</c:if>
</t:template>

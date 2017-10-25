<%--
  Created by IntelliJ IDEA.
  User: geroy
  Date: 21.09.2017
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="g" uri="/WEB-INF/custom.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:template>
	<h1>Guestbook</h1>
	<c:forEach var="entry" items="${entries}">
		<t:entry entry="${entry}" />
	</c:forEach>
	<c:if test="${not empty user}">
		<t:form action="/" method="POST" modelAttribute="guestbookForm">
			<div class="form-group">
				<form:label path="text">Text</form:label>
				<form:textarea class="form-control" path="text" autofocus="true" />
				<form:errors path="text" />
			</div>
			<button type="submit" class="btn btn-default">Post</button>
		</t:form>
	</c:if>
</t:template>

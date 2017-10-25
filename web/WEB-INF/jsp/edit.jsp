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
	<t:form action="/edit/${entry.id}" method="POST" modelAttribute="guestbookForm">
		<div class="form-group">
			<form:label path="text">Text</form:label>
			<form:textarea class="form-control" path="text" autofocus="true" />
			<form:errors path="text" />
		</div>
		<button type="submit" class="btn btn-default">Save Edits</button>
	</t:form>
</t:template>

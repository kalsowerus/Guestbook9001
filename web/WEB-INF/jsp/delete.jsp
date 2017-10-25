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
	<h2>Are you sure you want to delete this entry?</h2>
	<t:entry entry="${entry}" />
	<t:form action="/delete/${entry.id}" method="POST" modelAttribute="">
		<button type="submit" class="btn btn-default">Delete</button>
		<a href="/" class="btn btn-default">Cancel</a>
	</t:form>
</t:template>

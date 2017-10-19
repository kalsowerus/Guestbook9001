<%--
  Created by IntelliJ IDEA.
  User: geroy
  Date: 21.09.2017
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ attribute name="action" required="true" %>
<%@ attribute name="method" required="true" %>
<%@ attribute name="modelAttribute" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form action="${action}" method="${method}" modelAttribute="${modelAttribute}">
	<input type="hidden" name="csrfToken" value="${csrfToken}" />
	<jsp:doBody />
</form:form>

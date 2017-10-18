<%--
  Created by IntelliJ IDEA.
  User: geroy
  Date: 21.09.2017
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="text" required="true" %>
<%@ attribute name="type" required="true" %>
<%@ attribute name="autofocus" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="form-group">
	<form:label path="${id}">${text}</form:label>
	<form:input class="form-control" type="${type}" path="${id}" autofocus="${autofocus ? 'true' : ''}" />
	<form:errors path="${id}" />
</div>

<%--
  Created by IntelliJ IDEA.
  User: geroy
  Date: 21.09.2017
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="text" required="true" %>
<%@ attribute name="type" required="true" %>
<%@ attribute name="autofocus" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group">
	<label for="${id}">${text}</label>
	<input class="form-control" type="${type}" id="${id}" name="${name}" ${autofocus ? 'autofocus' : ''}>
</div>

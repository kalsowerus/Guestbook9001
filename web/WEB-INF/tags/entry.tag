<%--
  Created by IntelliJ IDEA.
  User: geroy
  Date: 21.09.2017
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="/WEB-INF/custom.tld" %>

<%@ attribute name="entry" type="guestbook.entity.Entry" required="true" %>

<div class="panel panel-default">
	<div class="panel-body">
		<g:escape value="${entry.text}" />
	</div>
	<div class="panel-footer">
		<b><c:out value="${entry.creator.username}" /></b>
		(<c:out value="${entry.creationTimestamp}" />)
		<c:if test="${not empty entry.lastModifier}">
			last edited by
			<b><c:out value="${entry.lastModifier.username}" /></b>
			(<c:out value="${entry.modificationTimestamp}" />)
		</c:if>
		<c:if test="${user.admin || user == entry.creator}">
			<a href="/edit/${entry.id}">edit</a>
			<a href="/delete/${entry.id}">delete</a>
		</c:if>
	</div>
</div>

<%--
  Created by IntelliJ IDEA.
  User: geroy
  Date: 21.09.2017
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
	<h1>Guestbook</h1>
	<c:if test="${not empty user}">
		<form method="POST">
			<div class="form-group">
				<label for="text">Text</label>
				<textarea class="form-control" id="text" name="text"></textarea>
			</div>
			<button type="submit" class="btn btn-default">Post</button>
		</form>
	</c:if>
</t:template>

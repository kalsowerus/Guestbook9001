package guestbook.util;

import guestbook.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LogUtil {
	public static String getRequestInfo(HttpServletRequest request) {
		return String.format("%s '%s' by '%s'/%s",
				request.getMethod(),
				request.getRequestURI(),
				getUser(request),
				request.getRemoteAddr());
	}

	public static String getUser(HttpServletRequest request) {
		User user = (User) request.getSession(true).getAttribute("user");
		return user != null ? user.getUsername() : "anonymous user";
	}
}

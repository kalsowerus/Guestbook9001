package guestbook.util;

import guestbook.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LogUtil {
	public static String getRequestInfo(HttpServletRequest request) {
		return String.format("%s '%s' by %s",
				request.getMethod(),
				request.getRequestURI(),
				getUserInfo(request));
	}

	public static String getUserInfo(HttpServletRequest request) {
		User user = (User) request.getSession(true).getAttribute("user");
		return String.format("%s/%s",
				user != null ? String.format("'%s'", user.getUsername()) : "<anonymous user>",
				request.getRemoteAddr());
	}
}

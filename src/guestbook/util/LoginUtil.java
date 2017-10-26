package guestbook.util;

import guestbook.entity.User;

import javax.servlet.http.HttpSession;

public final class LoginUtil {
	private static final String USER_ATTRIBUTE_NAME = "user";

	private LoginUtil() {}

	public static boolean isLoggedIn(HttpSession session) {
		return session.getAttribute(USER_ATTRIBUTE_NAME) != null;
	}

	public static User getUser(HttpSession session) {
		return (User) session.getAttribute(USER_ATTRIBUTE_NAME);
	}
}

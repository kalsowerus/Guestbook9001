package guestbook.util;

import javax.servlet.http.HttpSession;

public class LoginUtil {
	private static final String USER_ATTRIBUTE_NAME = "user";

	public static boolean isLoggedIn(HttpSession session) {
		return session.getAttribute(USER_ATTRIBUTE_NAME) != null;
	}
}

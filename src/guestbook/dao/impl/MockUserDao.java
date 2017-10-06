package guestbook.dao.impl;

import guestbook.dao.UserDao;
import guestbook.entity.Role;
import guestbook.entity.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;


public class MockUserDao implements UserDao {
	@Override
	public User getUser(String username) {
		if("admin".equals(username)) {
			return new User(username, Arrays.asList(Role.USER, Role.ADMIN));
		} else {
			return new User(username, Arrays.asList(Role.USER));
		}
	}

	@Override
	public boolean authenticateUser(String username, String password) {
		return "123".equals(password);
	}
}

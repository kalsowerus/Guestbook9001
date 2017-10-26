package guestbook.dao;

import guestbook.entity.User;

public interface UserDao {
	User getUser(String username);

	boolean authenticateUser(String username, String password);

	boolean createUser(User user);
}

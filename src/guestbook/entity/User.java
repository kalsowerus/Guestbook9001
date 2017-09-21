package guestbook.entity;

import java.util.List;

public class User {
	private final String username;
	private final List<Role> roles;

	public User(String username, List<Role> roles) {
		this.username = username;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public boolean hasRole(Role role) {
		return getRoles().contains(role);
	}
}

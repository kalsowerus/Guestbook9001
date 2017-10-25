package guestbook.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
	@NamedQuery(name="User.findByUsername", query="select u from User where u.username = :username")
})
public class User implements Serializable {

	public final static String FIND_BY_USERNAME = "User.findByUsername";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
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

	public boolean isAdmin() {
		return hasRole(Role.ADMIN);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;

		if(obj == null) return false;

		if(!(obj instanceof User)) return false;

		User other = (User) obj;
		return getUsername().equals(other.getUsername());
	}
}

package guestbook.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
	@NamedQuery(name="User.findByUsername", query="select u from User u where u.username = :username"),
	@NamedQuery(name="User.findByUsernameAndPassword", query="select u from User u where u.username = :username and u.password = :password")
})
public class User implements Serializable {

	public final static String FIND_BY_USERNAME = "User.findByUsername";
	public final static String FIND_BY_USERNAME_AND_PASSWORD = "User.findByUsernameAndPassword";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	@Column(unique=true)
	private String username;
	private String password;
	private String salt;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="creator")
	private List<Entry> entries = null;

	@Enumerated(EnumType.STRING)
	private Role role;

	public User(){}

	public User(String username,String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public void addEntry(Entry e){
		if(entries == null){
			entries = new ArrayList<Entry>();
		}
		entries.add(e);
		e.setCreator(this);
	}

	public Role getRole() {
		return role;
	}

	public void setRoles(Role role) {
		this.role = role;
	}

	/*public boolean hasRole(Role role) {
		return getRoles().contains(role);
	}*/

	public boolean isAdmin() {
		return role == Role.ADMIN;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

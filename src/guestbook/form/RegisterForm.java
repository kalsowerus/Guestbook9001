package guestbook.form;

import guestbook.form.constraint.Password;

import javax.validation.constraints.NotEmpty;

public class RegisterForm {
	@NotEmpty
	private String username;

	@Password
	private String password;

	@NotEmpty
	private String passwordRepeat;

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

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}
}

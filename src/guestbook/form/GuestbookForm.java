package guestbook.form;

import javax.validation.constraints.NotEmpty;

public class GuestbookForm {
	@NotEmpty(message = "Text must not be empty")
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

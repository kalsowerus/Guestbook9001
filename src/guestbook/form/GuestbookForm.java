package guestbook.form;

import javax.validation.constraints.NotEmpty;

public class GuestbookForm {
	@NotEmpty
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

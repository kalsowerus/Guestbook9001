package guestbook.entity;

import java.util.Date;

public class Entry {
	private final long id;
	private String text;
	private final User creator;
	private final Date creationTimestamp;
	private User lastModifier;
	private Date modificationTimestamp;

	public Entry(long id, String text, User creator, Date creationTimestamp) {
		this.id = id;
		this.text = text;
		this.creator = creator;
		this.creationTimestamp = creationTimestamp;
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getCreator() {
		return creator;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public User getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(User lastModifier) {
		this.lastModifier = lastModifier;
	}

	public Date getModificationTimestamp() {
		return modificationTimestamp;
	}

	public void setModificationTimestamp(Date modificationTimestamp) {
		this.modificationTimestamp = modificationTimestamp;
	}
}

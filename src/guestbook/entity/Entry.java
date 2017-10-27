package guestbook.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Entry implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="entry_id")
	private long id;

	private String text;

	@ManyToOne
	@JoinColumn(name = "creator",foreignKey = @javax.persistence.ForeignKey(name = "user_fk"), nullable = false)
	private User creator;

	private Date creationTimestamp;

	private String lastModifier;

	private Date modificationTimestamp;

	public Entry() {
	}

	public Entry(long id, String text, User creator, Date creationTimestamp) {
		this.id = id;
		this.text = text;
		this.creator = creator;
		this.creationTimestamp = creationTimestamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public String getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}

	public Date getModificationTimestamp() {
		return modificationTimestamp;
	}

	public void setModificationTimestamp(Date modificationTimestamp) {
		this.modificationTimestamp = modificationTimestamp;
	}
}

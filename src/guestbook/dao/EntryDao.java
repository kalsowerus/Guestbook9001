package guestbook.dao;

import guestbook.entity.Entry;

public interface EntryDao {
	Entry getEntries(int page);

	boolean updateEntry(Entry entry);
}

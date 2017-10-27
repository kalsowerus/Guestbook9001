package guestbook.dao;

import guestbook.entity.Entry;

import java.util.List;

public interface EntryDao {
	List<Entry> getEntries(int page, int pagesize);

	boolean updateEntry(Entry entry);

	void createEntry(Entry entry);

	Entry getEntry(long id);

	boolean deleteEntry(long id);
}

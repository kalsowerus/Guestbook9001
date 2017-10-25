package guestbook.dao.impl;

import guestbook.dao.EntryDao;
import guestbook.entity.Entry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class MockEntryDao implements EntryDao {
	private List<Entry> entries;

	public MockEntryDao() {
		setEntries(new ArrayList<>());
	}

	@Override
	public List<Entry> getEntries(int page) {
		return getEntries().subList(page * 2, Math.min((page + 1) * 2, getEntries().size()));
	}

	@Override
	public boolean updateEntry(Entry entry) {
		if(deleteEntry(entry.getId())) {
			getEntries().add(entry);
			return true;
		}
		return false;
	}

	@Override
	public void createEntry(Entry entry) {
		getEntries().add(entry);
	}

	@Override
	public Entry getEntry(long id) {
		for(Entry entry : getEntries()) {
			if(entry.getId() == id) {
				return entry;
			}
		}
		return null;
	}

	@Override
	public boolean deleteEntry(long id) {
		Iterator<Entry> iterator = getEntries().iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getId() == id) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
}

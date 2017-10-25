package guestbook.dao.impl;

import guestbook.dao.EntryDao;
import guestbook.entity.Entry;
import guestbook.helper.EntityManagerHelper;

import java.util.List;

public class DefaultEntryDao implements EntryDao{

    private final EntityManagerHelper emh = new EntityManagerHelper();

    @Override
    public List<Entry> getEntries(int page) {
        return null;
    }

    @Override
    public boolean updateEntry(Entry entry) {
        return false;
    }

    @Override
    public void createEntry(Entry entry) {

    }

    @Override
    public Entry getEntry(long id) {
        return null;
    }

    @Override
    public boolean deleteEntry(long id) {
        return false;
    }
}

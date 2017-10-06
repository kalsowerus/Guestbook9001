package guestbook.dao.impl;

import guestbook.dao.EntryDao;
import guestbook.entity.Entry;
import guestbook.helper.EntityManagerHelper;

public class DefaultEntryDAO implements EntryDao{

    private final EntityManagerHelper emh = new EntityManagerHelper();

    @Override
    public Entry getEntries(int page) {
        return null;
    }

    @Override
    public boolean updateEntry(Entry entry) {
        return false;
    }
}

package guestbook.dao.impl;

import guestbook.dao.EntryDao;
import guestbook.entity.Entry;
import guestbook.helper.EntityManagerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

//@Component
public class DefaultEntryDao implements EntryDao{
    private static final Logger LOG = LoggerFactory.getLogger(DefaultEntryDao.class);
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
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();
        try {
            em.persist(entry);
        } catch (Exception e) {
            LOG.error("Error while running query: " + e.getMessage());
        }
        EntityManagerHelper.commitAndCloseTransaction();
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

package guestbook.dao.impl;

import guestbook.dao.EntryDao;
import guestbook.entity.Entry;
import guestbook.helper.EntityManagerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultEntryDao implements EntryDao {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultEntryDao.class);

    @Override
    public List<Entry> getEntries(int page, int pagesize) {
        List<Entry> entries = new ArrayList<>();
        EntityManager em = EntityManagerHelper.getEntityManager();
        TypedQuery<Entry> typedQuery = em.createQuery("select e from Entry e order by e.creationTimestamp", Entry.class);
        typedQuery.setFirstResult((page - 1)*pagesize);
        typedQuery.setMaxResults(pagesize);
        try{
            entries = typedQuery.getResultList();
        } catch (Exception e) {
            LOG.error("Error while running query:", e);
        }
        return entries;
    }

    public int getPageCount(int pagesize) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Query q = em.createQuery("select count(*) from Entry");
        int numberofentries = (int)q.getSingleResult();
        EntityManagerHelper.closeEntityManager();
        return (int) Math.ceil(numberofentries / pagesize);
    }

    @Override
    public boolean updateEntry(Entry entry) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();
        try {
            em.merge(entry);
        } catch (Exception e) {
            LOG.error("Error while running query:", e);
        }
        EntityManagerHelper.commitAndCloseTransaction();
        return false;
    }

    @Override
    public void createEntry(Entry entry) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();
        try {
            em.persist(entry);
        } catch (Exception e) {
            LOG.error("Error while running query:", e);
        }
        EntityManagerHelper.commitAndCloseTransaction();
    }

    @Override
    public Entry getEntry(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();
        Entry entry = null;
        try {
            entry = em.find(Entry.class, id);
        } catch (Exception e) {
            LOG.error("Error while running query:", e);
        }
        EntityManagerHelper.commitAndCloseTransaction();
        return entry;
    }

    @Override
    public boolean deleteEntry(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();
        try {
            Entry e = em.find(Entry.class, id);
            em.remove(e);
        } catch (Exception e) {
            LOG.error("Error while running query:", e);
            return false;
        }
        EntityManagerHelper.commitAndCloseTransaction();
        return true;
    }
}

package guestbook.dao.impl;

import guestbook.dao.EntryDao;
import guestbook.entity.Entry;
import guestbook.entity.User;
import guestbook.helper.EntityManagerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        try{
            TypedQuery<Entry> typedQuery = em.createQuery("select e from Entry e order by e.creationTimestamp", Entry.class);
            typedQuery.setFirstResult((page - 1)*pagesize);
            typedQuery.setMaxResults(pagesize);
            entries = typedQuery.getResultList();
        } catch (NoResultException nre){

        }
        catch (Exception e) {
            throw new RuntimeException("Error getting pagecount", e);
        }
        return entries;
    }
    @Override
    public int getPageCount(int pagesize) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        int numberofentries = 0;
        try {
            // sql count is always returned as long from JPA
            TypedQuery q = em.createQuery("select count(*) from Entry", Long.class);
            numberofentries =  Math.toIntExact((long)q.getSingleResult());
        } catch(NoResultException nre){

        }
        catch (Exception e){
            throw new RuntimeException("Error getting pagecount", e);
        }
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
            throw new RuntimeException("Error updating entry", e);
        }
        EntityManagerHelper.commitAndCloseTransaction();
        return true;
    }

    @Override
    public void createEntry(Entry entry) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();
        try {
            User u = entry.getCreator();
            u.addEntry(entry);
            em.merge(u);
        } catch (Exception e) {
            throw new RuntimeException("Error creating entry", e);
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
            throw new RuntimeException("Error getting entry", e);
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
            throw new RuntimeException("Error removing entry", e);
        }
        EntityManagerHelper.commitAndCloseTransaction();
        return true;
    }
}

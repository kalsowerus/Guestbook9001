package guestbook.dao.impl;

import guestbook.dao.UserDao;
import guestbook.entity.User;
import guestbook.helper.EntityManagerHelper;
import guestbook.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


@Component
public class DefaultUserDao implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserDao.class);

    @Override
    public User getUser(String username) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        User user = null;
        try {
            TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_USERNAME, User.class);
            query.setParameter("username", username);
            user = query.getSingleResult();
        } catch (NoResultException e){

        } catch (Exception e) {
            LOG.error("Error while running query: " + e.getMessage());
        }
        return user;
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        User user = null;
        try{
            TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_USERNAME_AND_PASSWORD, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            user = query.getSingleResult();
        } catch (Exception e){
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
        if(user != null){
            return true;
        } else return false;
    }

    @Override
    public boolean createUser(User user) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();
        try {
            em.persist(user);
        } catch (Exception e) {
            LOG.error("Error while running query: " + e.getMessage());
            return false;
        }
        EntityManagerHelper.commitAndCloseTransaction();
        return true;
    }
}

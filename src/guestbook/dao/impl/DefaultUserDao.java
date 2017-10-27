package guestbook.dao.impl;

import guestbook.dao.UserDao;
import guestbook.entity.User;
import guestbook.helper.EntityManagerHelper;
import guestbook.util.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
            throw new RuntimeException("Error getting user", e);
        }
        return user;
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        String pw = "";
        try{
            TypedQuery<String> query = em.createNamedQuery(User.GET_USER_PASSWORD, String.class);
            query.setParameter("username", username);
            pw = query.getSingleResult();
        } catch (NoResultException e){

        } catch (Exception e){
            throw new RuntimeException("Error authenticating user", e);
        }
        return EncryptionUtil.checkPassword(password, pw);
    }

    @Override
    public boolean createUser(User user) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityManagerHelper.beginTransaction();
        try {
            EncryptionUtil.injectPassword(user);
            em.persist(user);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user", e);
        }
        EntityManagerHelper.commitAndCloseTransaction();
        return true;
    }
}

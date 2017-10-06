package guestbook.dao.impl;

import guestbook.dao.UserDao;
import guestbook.entity.User;
import guestbook.helper.EntityManagerHelper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Component
public class DefaultUserDao implements UserDao {
    @Override
    public User getUser(String username) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        User user = null;
        try {
            TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_USERNAME, User.class);
            query.setParameter("username", username);
            EntityManagerHelper.commit();
            user = query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        return false;
    }
}

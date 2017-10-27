package guestbook.util;

import guestbook.dao.impl.DefaultUserDao;
import guestbook.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptionUtil {
    private static final Logger LOG = LoggerFactory.getLogger(EncryptionUtil.class);

    public static String encryptPassword(String password, String salt){
        try {
            return BCrypt.hashpw(password, salt);
        } catch (Exception e) {
            LOG.error("Exception: " + e.getMessage());
        }
        return null;
    }

    private static String generateSalt() {
        return BCrypt.gensalt();
    }

    public static void injectPassword(User user) {
        String salt = generateSalt();
        String password;
        try {
            password = encryptPassword(user.getPassword(), salt);
            user.setPassword(password);
        } catch (Exception e) {
            LOG.error("Error" +  e.getMessage());
        }
    }

    public static boolean checkPassword(String plaintextPW, String hashedPW){
        return BCrypt.checkpw(plaintextPW, hashedPW);
    }

}


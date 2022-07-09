package controller;

import common.exception.ExpiredSessionException;
import common.exception.FailLoginException;
import dao.user.UserDAO;
import entity.user.User;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author
 */
public class AuthenticationController extends BaseController {

    public boolean isAnonymousSession() {
        try {
            getMainUser();
            return false;
        } catch (Exception ex) {
            return true;
        }
    }

    public User getMainUser() throws ExpiredSessionException {
        if (SessionInformation.mainUser == null
                || SessionInformation.expiredTime == null
                || SessionInformation.expiredTime.isBefore(LocalDateTime.now())) {
            logout();
            throw new ExpiredSessionException();
        } else {
            return SessionInformation.mainUser.cloneInformation();
        }
    }

    public void login(String email, String password) {
        try {
            User user = new UserDAO().authenticate(email, MessageDigestUtils.genDigestByMd5(password));
            if (Objects.isNull(user)) {
                throw new FailLoginException();
            }
            SessionInformation.mainUser = user;
            SessionInformation.expiredTime = LocalDateTime.now().plusHours(24);
        } catch (SQLException ex) {
            throw new FailLoginException();
        }
    }

    public void logout() {
        SessionInformation.mainUser = null;
        SessionInformation.expiredTime = null;
    }

}

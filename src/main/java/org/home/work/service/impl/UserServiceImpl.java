package org.home.work.service.impl;

import org.home.work.Exception.UserException;
import org.home.work.Utils.Store;
import org.home.work.Utils.UtilTool;
import org.home.work.entity.User;
import org.home.work.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * the implementation of The UserService interface.
 */
public class UserServiceImpl implements UserService {

    private Map<String, User> userMap = Store.getUserMap();

    /**
     * create user implementation.
     * @param userName
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    @Override
    public boolean createUser(String userName, String password) throws NoSuchAlgorithmException {

        if (userMap.containsKey(userName)) {
            throw new UserException("the user: " +  userName + " has existed, creat failed");
        }
        User user = new User();
        user.setId(UtilTool.generateUserId());
        user.setName(userName);
        user.setPassword(UtilTool.passwordEncrypt(password));
        userMap.put(userName, user);
        return true;
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public boolean deleteUser(User user) {

        if (!userMap.containsKey(user.getName())) {
            throw new UserException("the user: " +  user.getName() + " does not exist, delete failed");
        }
        userMap.remove(user.getName());
        return true;
    }
}

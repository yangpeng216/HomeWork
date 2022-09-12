package org.home.work.service;

import org.home.work.entity.User;

import java.security.NoSuchAlgorithmException;

/**
 * user service interface
 */
public interface UserService {

    /**
     *  create user
     * @param userName
     * @param password
     * @return
     */
    boolean createUser(String userName, String password) throws NoSuchAlgorithmException;

    /**
     * delete user
     * @param user
     * @return
     */
    boolean deleteUser(User user);


}

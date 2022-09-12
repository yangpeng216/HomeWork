package org.home.work.service;

import org.home.work.entity.Role;
import org.home.work.entity.User;

import java.security.NoSuchAlgorithmException;

/**
 * authenticate and check service interface
 */
public interface IdentityService {

    /**
     * use the user information to authenticate
     * @param userName
     * @param password
     * @return
     */
    String authenticate(String userName, String password) throws NoSuchAlgorithmException;

    /**
     * invalidate the token
     * @param token
     */
    void invalidate(String token);

    /**
     * check the role with the token.
     * @param token
     * @param role
     * @return
     */
    boolean checkRole(String token, Role role);
}

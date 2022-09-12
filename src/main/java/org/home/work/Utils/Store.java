package org.home.work.Utils;

import org.home.work.entity.Role;
import org.home.work.entity.User;
import org.home.work.entity.UserMapRole;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Store {
    /**
     * store the user information
     */
    static Map<String, User> userMap = new ConcurrentHashMap<>();
    /**
     * store the role information
     */
    static Map<String, Role> roleMap = new ConcurrentHashMap<>();
    /**
     * store the token information to check.
     */
    static Map<String,Long> tokenMap = new ConcurrentHashMap<>();

    static Map<String,User> tokenUser = new ConcurrentHashMap<>();

    static Map<String, List<UserMapRole>> userMapRoleMap = new ConcurrentHashMap<>();

    public static Map<String, User> getUserMap() {
        return userMap;
    }

    public static Map<String, Role> getRoles() {
        return roleMap;
    }

    public static Map<String, Long> getTokenMap() {
        return tokenMap;
    }

    public static Map<String, List<UserMapRole>> getUserMapRoleMap() {
        return userMapRoleMap;
    }

    public static Map<String, User> getTokenUser() {
        return tokenUser;
    }
}

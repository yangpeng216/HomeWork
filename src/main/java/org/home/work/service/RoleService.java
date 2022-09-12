package org.home.work.service;

import org.home.work.entity.Role;
import org.home.work.entity.User;

import java.util.List;

/**
 * role service interface
 */
public interface RoleService {
    /**
     * Create role
     * @param roleName
     * @return
     */
    boolean createRole(String roleName);

    /**
     * delete role
     * @param role
     * @return
     */
    boolean deleteRole(Role role);

    /**
     * add role to user, create the association with the user.
     * @param user
     * @param role
     */
    void addRoleToUser(User user, Role role);

    /**
     * returns all roles  for the user, error if token is invalid
     * @param token
     * @return
     */
    List<Role> allRoles(String token);
}

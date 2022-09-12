package org.home.work.service.impl;

import org.home.work.Exception.RoleException;
import org.home.work.Exception.UserException;
import org.home.work.Utils.Store;
import org.home.work.Utils.UtilTool;
import org.home.work.entity.Role;
import org.home.work.entity.User;
import org.home.work.entity.UserMapRole;
import org.home.work.service.RoleService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * RoleService implementation
 */
public class RoleServiceImpl implements RoleService {

    private Map<String, Role> roleMap = Store.getRoles();
    private Map<String, User> userMap = Store.getUserMap();

    private Map<String, List<UserMapRole>> userMapRoleMap = Store.getUserMapRoleMap();
    private Map<String,User> tokenUser  = Store.getTokenUser();

    /**
     * create role implementation
     * @param roleName
     * @return
     */
    @Override
    public boolean createRole(String roleName) {
        if (roleMap.containsKey(roleName)) {
            throw new RoleException("The role: " + roleName + " has existed! Creat failed");
        }
        Role role = new Role();
        role.setId(UtilTool.generateRoleId());
        role.setName(roleName);
        roleMap.put(roleName, role);
        return true;
    }

    @Override
    public boolean deleteRole(Role role) {

        if (!roleMap.containsKey(role.getName())) {
            throw new RoleException("the role: " +  role.getName() + " does not exist, delete failed");
        }
        roleMap.remove(role.getName());
        return true;
    }

    @Override
    public void addRoleToUser(User user, Role role) {

        if (!userMap.containsKey(user.getName()) || !roleMap.containsKey(role.getName())) {
            throw new RoleException("the user: " +  user.getName() + " does not existed.");
        }
        List<UserMapRole> userMapRoles = userMapRoleMap.getOrDefault(user.getName(), new ArrayList<>());
        for (UserMapRole userMapRole : userMapRoles) {
            if (userMapRole.getUserId().equals(user.getId()) && userMapRole.getRoleId().equals(role.getId())) {
                throw new RoleException("the user has associated with the role.");
            }
        }
        UserMapRole userMapRole = new UserMapRole();
        userMapRole.setUserId(user.getId());
        userMapRole.setRoleId(role.getId());
        userMapRoles.add(userMapRole);
        userMapRoleMap.put(user.getName(), userMapRoles);
    }

    @Override
    public List<Role> allRoles(String token) {
        List<Role> roles = new ArrayList<>();
        if (UtilTool.checkTokenIsExpire(token)) {
            throw new RoleException("the token has expired.");
        }
        User user = tokenUser.get(token);
        List<UserMapRole> userMapRoleList = userMapRoleMap.get(user.getName());
        for (UserMapRole userMapRole : userMapRoleList) {
            roleMap.values().forEach( role -> {
                if (role.getId().equals(userMapRole.getRoleId())) {
                    roles.add(role);
                }
            });
        }
        return roles;
    }
}

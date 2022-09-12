package org.home.work.service.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.home.work.Exception.CheckRoleException;
import org.home.work.Utils.Store;
import org.home.work.Utils.UtilTool;
import org.home.work.entity.Role;
import org.home.work.entity.User;
import org.home.work.entity.UserMapRole;
import org.home.work.service.IdentityService;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IdentityServiceImpl implements IdentityService {

    private Map<String, Role> roleMap = Store.getRoles();
    private Map<String, User> userMap = Store.getUserMap();

    private Map<String, List<UserMapRole>> userMapRoleMap = Store.getUserMapRoleMap();
    private Map<String,Long> tokenMap  = Store.getTokenMap();
    private Map<String,User> tokenUser  = Store.getTokenUser();

    @Override
    public String authenticate(String userName, String password) throws NoSuchAlgorithmException {
        if (userMap.containsKey(userName) && userMap.get(userName).equals(UtilTool.passwordEncrypt(password))) {
            User user = userMap.get(userName);
            String token = UtilTool.generateToken(user);
            tokenMap.put(token, System.currentTimeMillis());
            tokenUser.put(token,user);
            return token;
        }
        return "authenticate failed";
    }

    @Override
    public void invalidate(String token) {
        tokenMap.remove(token);
        tokenUser.remove(token);
    }

    @Override
    public boolean checkRole(String token, Role role) {
        User user = tokenUser.get(token);
        if (null == user) {
            throw new CheckRoleException("The token expired");
        }
        List<UserMapRole> userMapRoleList = userMapRoleMap.getOrDefault(user.getName(), new ArrayList<>());
        for (UserMapRole userMapRole : userMapRoleList) {
            if (userMapRole.getRoleId().equals(role.getId())) {
                return true;
            }
        }
        return false;
    }
}

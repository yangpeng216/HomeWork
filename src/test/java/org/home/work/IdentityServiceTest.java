package org.home.work;

import org.home.work.Utils.Store;
import org.home.work.entity.Role;
import org.home.work.entity.User;
import org.home.work.factory.ServiceStrategy;
import org.home.work.service.IdentityService;
import org.home.work.service.RoleService;
import org.home.work.service.UserService;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class IdentityServiceTest {

    private RoleService roleService = (RoleService) ServiceStrategy.getService("roleService");
    private IdentityService identityService = (IdentityService) ServiceStrategy.getService("identityService");
    private UserService userService = (UserService) ServiceStrategy.getService("userService");

    @Before
    public void initData() throws NoSuchAlgorithmException {
        roleService.createRole("Admin");
        userService.createUser("Bob", "Bob12345");
    }

    @Test
    public void authenticateTest() throws NoSuchAlgorithmException {
        userService.createUser("Bob", "Bob12345");
        User user = Store.getUserMap().get("Bob");
        Role role = Store.getRoles().get("Admin");
        roleService.addRoleToUser(user, role);
        identityService.authenticate("Bob", "Bob12345");
    }

    @Test
    public void  invalidateTest() throws NoSuchAlgorithmException {
        User user = Store.getUserMap().get("Bob");
        Role role = Store.getRoles().get("Admin");
        roleService.addRoleToUser(user, role);
        String authenticate = identityService.authenticate("Bob", "Bob12345");
        identityService.invalidate(authenticate);
        if (identityService.checkRole(authenticate, role)) {
            assertEquals(1,roleService.allRoles(authenticate).size());
        }
    }
}

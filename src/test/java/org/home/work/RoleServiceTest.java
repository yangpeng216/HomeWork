package org.home.work;

import org.home.work.Utils.Store;
import org.home.work.Utils.UtilTool;
import org.home.work.entity.Role;
import org.home.work.entity.User;
import org.home.work.entity.UserMapRole;
import org.home.work.factory.ServiceStrategy;
import org.home.work.service.IdentityService;
import org.home.work.service.RoleService;
import org.home.work.service.UserService;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RoleServiceTest {

    private RoleService roleService = (RoleService) ServiceStrategy.getService("roleService");
    private IdentityService identityService = (IdentityService) ServiceStrategy.getService("identityService");
    private UserService userService = (UserService) ServiceStrategy.getService("userService");

    @Before
    public void initData() throws NoSuchAlgorithmException {
        roleService.createRole("Admin");
        userService.createUser("Bob", "Bob12345");
    }
    @Test
    public void createRoleTest() {
        assertEquals(true, roleService.createRole("Admin"));
    }

   @Test
    public void deleteRoleTest(){
        Role role = new Role();
        role.setName("Admin");
       assertEquals(true, roleService.deleteRole(role));
   }

    @Test
    public void addRoleToUser(){
        User user = new User();
        user.setId(UtilTool.generateUserId());
        user.setName("Bob");
        user.setPassword("Bob123456");
        Role role = new Role();
        role.setId(UtilTool.generateRoleId());
        role.setName("admin");
        roleService.addRoleToUser(user, role);
        Map<String, List<UserMapRole>> userMapRoleMap = Store.getUserMapRoleMap();
        assertEquals(1, userMapRoleMap.size());
    }
    @Test
    public void allRoles() throws NoSuchAlgorithmException {

        User user = Store.getUserMap().get("Bob");
        Role role = Store.getRoles().get("Admin");
        roleService.addRoleToUser(user, role);
        String authenticate = identityService.authenticate("Bob", "Bob12345");
        if (identityService.checkRole(authenticate, role)) {
            assertEquals(1,roleService.allRoles(authenticate).size());
        }
    }

}

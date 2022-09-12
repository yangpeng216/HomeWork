package org.home.work;


import org.home.work.entity.User;
import org.home.work.factory.ServiceStrategy;
import org.home.work.service.UserService;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    private UserService userService = (UserService) ServiceStrategy.getService("userService");

    @Test
    public void createUserTest() throws NoSuchAlgorithmException {
        String name = "Bob";
        String password = "Bob123";
        assertEquals(true, userService.createUser(name, password));
    }

    @Before
    public void initData() throws NoSuchAlgorithmException {
        String name = "Bob";
        String password = "Bob123";
        assertEquals(true, userService.createUser(name, password));
    }
    @Test
    public void deleteUserTest() {
        String name = "Bob";
        String password = "Bob123";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        assertEquals(true, userService.deleteUser(user));
    }
}

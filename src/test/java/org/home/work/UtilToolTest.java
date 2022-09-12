package org.home.work;

import org.home.work.Utils.Store;
import org.home.work.Utils.UtilTool;
import org.home.work.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class UtilToolTest {

    private Map<String,Long> tokenMap = Store.getTokenMap();

    @Before
    public void initData() throws NoSuchAlgorithmException {
        User user = new User();
        user.setId(UtilTool.generateUserId());
        user.setName("userName1");
        user.setPassword("password12");
        tokenMap.put(UtilTool.generateToken(user), System.currentTimeMillis() - 60*60*1000*3);
    }

    @Test
    public void passwordEncryptTest() throws NoSuchAlgorithmException {
        String password = "homework123456";
        assertEquals("d904c7072727a135da1cdda99feb97518d6acb011881b16a3236b7a8d9f682f6", UtilTool.passwordEncrypt(password));
    }

    @Test
    public void checkTokenIsExpire() throws InterruptedException, NoSuchAlgorithmException {
        TimeUnit.SECONDS.sleep(5);
        User user = new User();
        user.setId(UtilTool.generateUserId());
        user.setName("userName1");
        user.setPassword("password12");
        assertEquals(true, UtilTool.checkTokenIsExpire(UtilTool.generateToken(user)));
    }
}

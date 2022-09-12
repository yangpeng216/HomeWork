package org.home.work.Utils;


import org.home.work.entity.User;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicInteger;

public class UtilTool {


    /**
     * the token can exist for two hours.
     * It'll be invalid in two hours.
     */
    private static long tokenExpireTime = 7200*1000;
    /**
     * user id generator
     */
    private static AtomicInteger userId = new AtomicInteger(0);
    /**
     * role id generator
     */
    private static AtomicInteger roleId = new AtomicInteger(0);
    /**
     * user SHA-256 to encode the password.
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String passwordEncrypt(String password) throws NoSuchAlgorithmException {

        return hashEncrypt(password);
    }

    /**
     * generate token based on user
     * @param user
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateToken(User user) throws NoSuchAlgorithmException {
        return hashEncrypt(user.getName() + user.getPassword());
    }

    /**
     * check if the token is expired.
     * @param token
     * @return
     */
    public static boolean checkTokenIsExpire(String token) {
        Long tokenStart = Store.getTokenMap().get(token);
        if (System.currentTimeMillis() - tokenStart >= tokenExpireTime) {
            return true;
        }
        return false;
    }

    /**
     * generate user id and return
     * @return
     */
    public static Integer generateUserId() {
        return userId.incrementAndGet();
    }

    /**
     * generate role id an return
     * @return
     */
    public static Integer generateRoleId() {
        return roleId.incrementAndGet();
    }

    private static String hashEncrypt(String src) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(src.getBytes(Charset.defaultCharset()));
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bytes = messageDigest.digest();
        String tempStr;
        for (byte aByte : bytes) {
            tempStr = Integer.toHexString(aByte & 0xff);
            if (tempStr.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(tempStr);
        }
        return stringBuffer.toString();
    }


}

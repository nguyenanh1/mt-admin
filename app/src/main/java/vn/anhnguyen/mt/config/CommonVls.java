package vn.anhnguyen.mt.config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonVls {
    public static final String BASE_URL = "http://192.168.43.140:9085/";
   // public static final String BASE_URL = "http://192.168.100.101:9085/";
    //Lý do đến LoginActivity
    public static final String REASON_FOR_LOGIN = "reason for login";

    public static final int CODE_SUCCESS = 200;

    public static final String TOKEN_TIME_OUT = "406";
    public static final String SESSION_TIME_OUT = "401";

    public static final String SUCCESS = "200";
    public static final String SYSTEM_ERROR = "001";
    public static final String ARGUMENT_NOT_VALID = "002";
    public static final String USER_NOT_EXITS = "003";
    public static final String PASSWORD_WRONG = "004";
    public static final String LOGIN_OTHER_DEVICE = "005";
    public static final String USER_REGISTER_EXITS = "006";
    public static final String TOKEN_IN_VALID = "007";
    public static final String TOKEN_IS_EMPTY = "008";
    public static final String LIST_EMPTY = "009";
    public static final String OBJECT_EMPTY = "010";
    public static final String TICKET_IS_HOLDING = "011";



    public static String md5(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
            while (result.length() < 32) {
                result = "0" + result;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}

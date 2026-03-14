package tp.appliSpring.generic.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyBcryptUtil {

    public static String encode(String plainTextPwd) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //default strength is 10
        return bCryptPasswordEncoder.encode(plainTextPwd);
    }

    public static boolean matches(String plainTextPwd, String hashedPwd) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(plainTextPwd, hashedPwd);
    }
}

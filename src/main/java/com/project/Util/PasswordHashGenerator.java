package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MYKOLA.GOROKHOV on 20.04.2017.
 */
public class PasswordHashGenerator {
    private PasswordHashGenerator() {
    }

    public static String generate(String password) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] passBytes = password.getBytes();
        byte[] passHash = sha256.digest(passBytes);
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < passHash.length; i++) {
            hexString.append(Integer.toString(0xFF & passHash[i]));
        }

        return hexString.toString();
    }


}

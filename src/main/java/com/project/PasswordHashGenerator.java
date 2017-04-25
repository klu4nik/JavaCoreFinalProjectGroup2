package main.java.com.project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MYKOLA.GOROKHOV on 20.04.2017.
 */
public class PasswordHashGenerator {

    public String generate(String password) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] passBytes = password.getBytes();
        byte[] passHash = sha256.digest(passBytes);
        return passHash.toString();
    }


}

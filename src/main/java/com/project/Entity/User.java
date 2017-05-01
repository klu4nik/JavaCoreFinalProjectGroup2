package Entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MYKOLA.GOROKHOV on 20.04.2017.
 */
public class User implements Serializable {
    private String firsName;
    private String lastName;
    private String login;
    private String password;

    public User(String firsName, String lastName, String login, String password) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public String getFirsName() {
        return firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", firstName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}'+"\n";
    }
}

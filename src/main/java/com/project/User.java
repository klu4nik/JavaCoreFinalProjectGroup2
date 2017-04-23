import java.security.NoSuchAlgorithmException;

/**
 * Created by MYKOLA.GOROKHOV on 20.04.2017.
 */
public class User {
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
        return "firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'';
    }
}

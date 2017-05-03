package Entity;

import java.io.Serializable;

/**
 * Created by MYKOLA.GOROKHOV on 20.04.2017.
 */
public class User implements Serializable {
    private Integer id;
    private String firsName;
    private String lastName;
    private String login;
    private String password;

    public User(String firsName, String lastName, String login, String password) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.id = this.hashCode();
    }

    public Integer getId() { return id; }


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
                "id=" + id +
                ", firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!firsName.equals(user.firsName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!login.equals(user.login)) return false;
        return password.equals(user.password);
    }


    @Override
    public int hashCode() {
        int result = firsName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}

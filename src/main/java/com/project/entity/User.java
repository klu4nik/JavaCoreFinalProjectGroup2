package entity;


import util.PasswordHashGenerator;

/**
 * @version final :)
 *          <p>
 *          entity-Class. Describes the entity of USER.
 *          Have next fields:
 *          id - for identification entry in DB;
 *          firstName - First Name of user;
 *          lastName - Last Name of user;
 *          login - unique login of user;
 *          password - login of user
 * @see PasswordHashGenerator
 */

public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;

    public User(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.id = this.hashCode();
    }

    public User(Integer id, String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
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
        return "id: " + id +
                ", First Name:" + firstName +
                ", Last Name: " + lastName +
                ", login: " + login + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!login.equals(user.login)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}

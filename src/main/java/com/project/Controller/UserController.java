package Controller;

import DAO.DAO_Users_Impl_TXT;
import Entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version final :)
 *          Class-Controller. Provides functionality for working with the database of Users.
 * @see DAO_Users_Impl_TXT
 */
public class UserController {
    private HashMap<Integer, User> users;
    private DAO_Users_Impl_TXT dui = new DAO_Users_Impl_TXT();

    /**
     * Read DB from file and put data to the local variable.
     * Is executed once when you create a new entity of class
     */
    public UserController() {
        try {
            users = dui.get();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Check Users DB
     *
     * @return false if DB is Empty
     */
    public boolean isUsersDBisEmpty() {
        try {
            return dui.get().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check Users DB
     *
     * @param id of User which we are looking for
     * @return User with ID
     */
    public User findUserById(Integer id) {
        return users.get(id);
    }

    /**
     * Check Users DB
     *
     * @param login of User which we are looking for
     * @return User with login
     */
    public User findUserByLogin(String login) {
        try {
            List<User> userNames =
                    users.values().stream().filter(p -> p.getLogin().equals(login)).collect(Collectors.toList());
            return userNames.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Add User to the DB
     *
     * @param user which should be added
     * @return updated DB
     */
    public HashMap<Integer, User> addUser(User user) {
        users.put(user.getId(), user);
        return users;
    }

    /**
     * Remove User from the DB
     *
     * @param login of User which should be deleted
     * @return updated DB
     */
    public HashMap<Integer, User> deleteUserByLogin(String login) {
        users.remove(login);
        return users;
    }

    /**
     * Update file with DB
     */
    public void flush() {
        try {
            dui.set(users);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

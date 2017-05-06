package Controller;

import DAO.*;
import Entity.User;

import java.util.HashMap;

/**
 * Created by MYKOLA.GOROKHOV on 28.04.2017.
 */


public class UserController {
    private HashMap<String, User> users;
    private DAO_Users_Impl_TXT dui = new DAO_Users_Impl_TXT();

    public UserController() {
        try {
            users = dui.get();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public User findUserById(Integer id) {

        return users.get(id);
    }

    public User findUserByLogin(String login) {
        try {
            return users.get(login);
        } catch (Exception e) {
            return null;
        }
    }

    public HashMap<String, User> addUser(User user) {
        users.put(user.getLogin(), user);
        return users;
    }

    public HashMap<String, User> deleteUserByLogin(String login) {
        users.remove(login);
        return users;
    }

    public void flush() {
        try {
            dui.set(users);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}

package Controller;

import DAO.DAO_Users_Impl_TXT;
import Entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MYKOLA.GOROKHOV on 28.04.2017.
 */


public class UserController {
    private HashMap<Integer, User> users;
    private DAO_Users_Impl_TXT dui = new DAO_Users_Impl_TXT();

    public UserController() {
        try {
            users = dui.get();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean isUsersDBisEmpty() {
        try {
            return dui.get().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public User findUserById(Integer id) {

        return users.get(id);
    }

    public User findUserByLogin(String login) {
        try {
            List<User> userNames =
                    users.values().stream().filter(p -> p.getLogin().equals(login)).collect(Collectors.toList());
            return userNames.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public HashMap<Integer, User> addUser(User user) {
        users.put(user.getId(), user);
        return users;
    }

    public HashMap<Integer, User> deleteUserByLogin(String login) {
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

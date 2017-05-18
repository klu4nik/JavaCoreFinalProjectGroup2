package dao;

import entity.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @version final :)
 *          Implementation of dao Interface for working with Users DB.
 *          DB stored in txt file (location of the file is described by constant "PATH")
 * @see DAO
 */
public class DAOUsersImpl implements DAO<HashMap<Integer, User>> {
    final static String PATH = "./ext/DB/Users";
    final static char SEPARATOR = (char) 29;

    /**
     * Used for read DB from the file.
     *
     * @return HashMap<UserID, User>
     * @throws IOException
     * @see DAO#get()
     */
    @Override
    public HashMap<Integer, User> get() throws IOException {
//        Читаем файл построчно
        List<String> currentUsers = null;
        currentUsers = Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
//        Описываем вид результата
        HashMap<Integer, User> result = new HashMap<Integer, User>();
//        бъем каждую cтроку на поля
        for (String str : currentUsers) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, SEPARATOR + "");
            Integer id = Integer.parseInt(stringTokenizer.nextToken());
            String login = stringTokenizer.nextToken();
            String firstName = stringTokenizer.nextToken();
            String lastName = stringTokenizer.nextToken();
            String password = stringTokenizer.nextToken();
            result.put(id, new User(id, firstName, lastName, login, password));
        }
        return result;
    }

    /**
     * Used for writing DB to the file.
     *
     * @@param hashMapUsers
     * @see DAO#set(Object)
     */
    @Override
    public void set(HashMap<Integer, User> hashMapUsers) {
        File usersFile = new File(PATH);

        try (FileWriter writer = new FileWriter(usersFile)) {
            for (HashMap.Entry<Integer, User> currentEntry : hashMapUsers.entrySet()) {
                writer.write(
                        currentEntry.getValue().getId().toString() + SEPARATOR +
                                currentEntry.getValue().getLogin() + SEPARATOR +
                                currentEntry.getValue().getFirstName() + SEPARATOR +
                                currentEntry.getValue().getLastName() + SEPARATOR +
                                currentEntry.getValue().getPassword()
                                + (char) 13 + (char) 10 // Конец строки
                );
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

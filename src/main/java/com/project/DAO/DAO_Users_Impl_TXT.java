package DAO;

import Entity.User;

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
 * Created by MYKOLA.GOROKHOV on 22.04.2017.
 */
public class DAO_Users_Impl_TXT implements DAO<HashMap<String, User>> {
    final static String PATH = "./ext/DB/Users";
    final static char SEPARATOR = (char) 29;

    @Override
    public HashMap<String, User> get() throws IOException {
//        Читаем файл построчно
        List<String> currentUsers = null;
        currentUsers = Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
//        Описываем вид результата
        HashMap<String, User> result = new HashMap<String, User>();
//        бъем каждую cтроку на поля
        for (String str : currentUsers) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, SEPARATOR + "");
            Integer id = Integer.parseInt(stringTokenizer.nextToken());
            String login = stringTokenizer.nextToken();
            String firstName = stringTokenizer.nextToken();
            String lastName = stringTokenizer.nextToken();
            String password = stringTokenizer.nextToken();
            result.put(login, new User(id, firstName, lastName, login, password));
        }
        return result;
    }


    @Override
    public void set(HashMap<String, User> hashMapUsers) {
        File usersFile = new File(PATH);

        try (FileWriter writer = new FileWriter(usersFile)) {
            for (HashMap.Entry<String, User> currentEntery : hashMapUsers.entrySet()) {
                writer.write(
                        currentEntery.getValue().getId().toString() + SEPARATOR +
                                currentEntery.getValue().getLogin() + SEPARATOR +
                                currentEntery.getValue().getFirstName() + SEPARATOR +
                                currentEntery.getValue().getLastName() + SEPARATOR +
                                currentEntery.getValue().getPassword()
                                + (char) 13 + (char) 10 // Конец строки
                );
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

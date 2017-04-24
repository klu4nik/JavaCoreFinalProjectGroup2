import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by MYKOLA.GOROKHOV on 22.04.2017.
 */
public class DAO_Users_Impl implements DAO_Users_Interface {
    final static String PATH = "../Users";
    final static char SEPARATOR = (char) 29;

    @Override
    public HashMap<String, User> getUsers() throws IOException {
//        Читаем файл построчно
        List<String> currentUsers = null;
        try {
            currentUsers = Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.getMessage();
        }
//        Описываем вид результата
        HashMap<String, User> result = new HashMap<String, User>();
//        бъем каждую cторку на поля
        for (String str : currentUsers) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, SEPARATOR + "");
            if (!stringTokenizer.hasMoreTokens()) {
                throw new IOException("File have incorrect data");
            }
            String firsName = stringTokenizer.nextToken();
            if (!stringTokenizer.hasMoreTokens()) {
                throw new IOException("File have incorrect data");
            }
            String lastName = stringTokenizer.nextToken();
            if (!stringTokenizer.hasMoreTokens()) {
                throw new IOException("File have incorrect data");
            }
            String login = stringTokenizer.nextToken();
            if (!stringTokenizer.hasMoreTokens()) {
                throw new IOException("File have incorrect data");
            }
            String password = stringTokenizer.nextToken();
            result.put(login, new User(firsName, lastName, login, password));
        }
        return result;
    }


    @Override
    public void setUsers(HashMap<String, User> hashMapUsers) {
        File usersFile = new File(PATH);

        try (FileWriter writer = new FileWriter(usersFile)) {
            for (HashMap.Entry<String, User> currentEntery : hashMapUsers.entrySet()) {
                writer.write(
                        currentEntery.getValue().getLogin() + SEPARATOR +
                                currentEntery.getValue().getFirsName() + SEPARATOR +
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

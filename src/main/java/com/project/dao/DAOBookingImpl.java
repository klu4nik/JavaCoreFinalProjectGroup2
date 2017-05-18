package dao;

import entity.Booking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @version final :)
 *          Implementation of dao Interface for working with Booking DB.
 *          DB stored in txt file (location of the file is described by constant "PATH")
 * @see DAO
 * @see DAOUsersImpl
 * @see DAOHotelsImpl
 */
public class DAOBookingImpl implements DAO<HashMap<Integer, Booking>> {
    final static String PATH = "./ext/DB/Booking";
    final static char SEPARATOR = (char) 29;

    /**
     * Used for read DB from the file.
     *
     * @return HashMap<UserID, User>
     * @throws IOException
     * @see DAO#get()
     */
    @Override
    public HashMap<Integer, Booking> get() throws IOException, ClassNotFoundException {

//        Читаем файл построчно
        List<String> currentBooking = null;
        try {
            //проверяем, что если файл не существует то создаем его
            File file = new File(PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
        }
        try {
            currentBooking = Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.getMessage();
        }
//        Описываем вид результата
        HashMap<Integer, Booking> result = new HashMap<Integer, Booking>();
//        бъем каждую cтроку на поля
        for (String str : currentBooking) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, SEPARATOR + "");
            Integer id = Integer.valueOf(stringTokenizer.nextToken());
            Integer user_id = Integer.valueOf(stringTokenizer.nextToken());
            Integer room_Number = Integer.valueOf(stringTokenizer.nextToken());
            Integer hotel_id = Integer.valueOf(stringTokenizer.nextToken());
            String date_start = stringTokenizer.nextToken();
            String date_end = stringTokenizer.nextToken();

            Booking nextBooking = new Booking(id, user_id, room_Number, hotel_id, new Date(Long.parseLong(date_start)), new Date(Long.parseLong(date_end)));

            result.put(user_id, nextBooking);
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
    public void set(HashMap<Integer, Booking> hashMapBooking) throws IOException {
        File bookingFile = new File(PATH);

        try (FileWriter writer = new FileWriter(bookingFile)) {
            for (HashMap.Entry<Integer, Booking> currentEntery : hashMapBooking.entrySet()) {
                writer.write(
                        currentEntery.getValue().getId().toString() + SEPARATOR +
                                currentEntery.getValue().getUser_id().toString() + SEPARATOR +
                                currentEntery.getValue().getRoom_Number().toString() + SEPARATOR +
                                currentEntery.getValue().getHotel_id().toString() + SEPARATOR +
                                currentEntery.getValue().getDate_start().getTime() + SEPARATOR +
                                currentEntery.getValue().getDate_end().getTime());
                writer.write(new StringBuilder().append((char) 13).append((char) 10).toString()); // Конец строки
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

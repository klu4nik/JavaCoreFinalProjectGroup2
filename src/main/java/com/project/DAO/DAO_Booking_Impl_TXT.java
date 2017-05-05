package DAO;

import Entity.Booking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by MYKOLA.GOROKHOV on 02.05.2017.
 */
public class DAO_Booking_Impl_TXT implements DAO<HashMap<Integer, Booking>> {
    final static String PATH = "./ext/DB/Booking";
    final static char SEPARATOR = (char) 29;

    @Override
    public HashMap<Integer, Booking> get() throws IOException, ClassNotFoundException {

//        Читаем файл построчно
        List<String> currentBooking = null;
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
            Integer date_start = Integer.valueOf(stringTokenizer.nextToken());
            Integer date_end = Integer.valueOf(stringTokenizer.nextToken());

            Booking nextBooking = new Booking(user_id, room_Number, hotel_id, new Date(date_start), new Date(date_end));

            result.put(user_id, nextBooking);
        }
        return result;
    }

    @Override
    public void set(HashMap<Integer, Booking> hashMapBooking) throws IOException {
        File bookingFile = new File(PATH);

        try (FileWriter writer = new FileWriter(bookingFile)) {
            for (HashMap.Entry<Integer, Booking> currentEntery : hashMapBooking.entrySet()) {
                writer.write(
                        currentEntery.getValue().getId().toString() + SEPARATOR +
                                currentEntery.getValue().getUser_login().toString() + SEPARATOR +
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

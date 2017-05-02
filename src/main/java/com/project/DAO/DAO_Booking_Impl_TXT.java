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
public class DAO_Booking_Impl_TXT implements DAO<ArrayList<Booking>> {
    final static String PATH = "../Booking";
    final static char SEPARATOR = (char) 29;

    @Override
    public ArrayList<Booking> get() throws IOException, ClassNotFoundException {

//        Читаем файл построчно
        List<String> currentBooking = null;
        try {
            currentBooking = Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.getMessage();
        }
//        Описываем вид результата
        ArrayList<Booking> result = new ArrayList<Booking>();
//        бъем каждую cтроку на поля
        for (String str : currentBooking) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, SEPARATOR + "");
            Integer user_id = Integer.valueOf(stringTokenizer.nextToken());
            Integer room_Number = Integer.valueOf(stringTokenizer.nextToken());
            Integer date_start = Integer.valueOf(stringTokenizer.nextToken());
            Integer date_end = Integer.valueOf(stringTokenizer.nextToken());

            Booking nextBooking = new Booking(user_id, room_Number, new Date(date_start), new Date(date_end));

            result.add(nextBooking);
        }
        return result;
    }

    @Override
    public void set(ArrayList<Booking> arrayListBooking) throws IOException {
        File bookingFile = new File(PATH);

        try (FileWriter writer = new FileWriter(bookingFile)) {
            for (Booking currentEntery : arrayListBooking) {
                writer.write(
                        currentEntery.getUser_id().toString() + SEPARATOR +
                                currentEntery.getRoom_Number().toString() + SEPARATOR +
                                currentEntery.getDate_start().getTime() + SEPARATOR +
                                currentEntery.getDate_end().getTime());
                writer.write(new StringBuilder().append((char) 13).append((char) 10).toString()); // Конец строки
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

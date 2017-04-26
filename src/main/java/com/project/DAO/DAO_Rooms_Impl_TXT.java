package DAO;

import DAO.DAO;
import Entity.Hotel;
import Entity.Room;
import Entity.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by MYKOLA.GOROKHOV on 23.04.2017.
 */
public class DAO_Rooms_Impl_TXT implements DAO<HashMap<Integer, Room>> {
    final static String PATH = "../Rooms";
    final static char SEPARATOR = (char) 29;

    @Override
    public HashMap<Integer, Room> get() throws IOException {
//        читаем список отелей
        HashMap<Integer, Hotel> hotelsFromFile = new HashMap<Integer, Hotel>();
        hotelsFromFile = new DAO_Hotels_Impl_TXT().get();

//        читаем список Пользователей
        HashMap<String, User> usersFromFile = new HashMap<String, User>();
        usersFromFile = new DAO_Users_Impl_TXT().get();


//        Читаем файл построчно
        List<String> currentRoom = null;
        try {
            currentRoom = Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.getMessage();
        }
//        Описываем вид результата
        HashMap<Integer, Room> result = new HashMap<Integer, Room>();
//        бъем каждую cторку на поля
        for (String str : currentRoom) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, SEPARATOR + "");
            Integer hotelid = Integer.valueOf(stringTokenizer.nextToken());
            Integer roomid = Integer.valueOf(stringTokenizer.nextToken());
            Integer numberOfPerson = Integer.valueOf(stringTokenizer.nextToken());
            Integer price = Integer.valueOf(stringTokenizer.nextToken());

            if (stringTokenizer.hasMoreTokens()) {
                String userLogin = stringTokenizer.nextToken();
                Long bookingStartData = Long.valueOf(stringTokenizer.nextToken());
                Long bookingEndData = Long.valueOf(stringTokenizer.nextToken());

                User userForRoom = new User(usersFromFile.get(userLogin).getFirsName(), usersFromFile.get(userLogin).getLastName(),
                        usersFromFile.get(userLogin).getLogin(), usersFromFile.get(userLogin).getPassword());

                Room nextRoom = new Room(
                        new Hotel(hotelsFromFile.get(hotelid).getHotelName(), hotelsFromFile.get(hotelid).getCity()),
                        roomid, numberOfPerson, price
                        ,
                        userForRoom,
                        new Date(bookingStartData),
                        new Date(bookingEndData)
                );
                result.put(nextRoom.hashCode(), nextRoom);


            } else {
                Room nextRoom = new Room(
                        new Hotel(hotelsFromFile.get(hotelid).getHotelName(), hotelsFromFile.get(hotelid).getCity()),
                        roomid, numberOfPerson, price);

                result.put(nextRoom.hashCode(), nextRoom);
            }
        }


        return result;
    }

    @Override
    public void set(HashMap<Integer, Room> hashMapRooms) {
        File roomsFile = new File(PATH);

        try (FileWriter writer = new FileWriter(roomsFile)) {
            for (HashMap.Entry<Integer, Room> currentEntery : hashMapRooms.entrySet()) {
                writer.write(
                        currentEntery.getValue().getHotel().getId().toString() + SEPARATOR +
                                currentEntery.getValue().getRoomNumber().toString() + SEPARATOR +
                                currentEntery.getValue().getNumberOfperson().toString() + SEPARATOR +
                                currentEntery.getValue().getPrice().toString());

                if (currentEntery.getValue().getReservedForUser() != null) {
                    writer.write(SEPARATOR + currentEntery.getValue().getReservedForUser().getLogin() + SEPARATOR +
                            currentEntery.getValue().getDateOfBookingStart().getTime() + SEPARATOR +
                            currentEntery.getValue().getDateOfBookingFinish().getTime());
                }
                writer.write(new StringBuilder().append((char) 13).append((char) 10).toString()); // Конец строки

            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

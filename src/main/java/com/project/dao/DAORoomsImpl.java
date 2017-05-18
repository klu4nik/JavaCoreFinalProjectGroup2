package dao;

import entity.Hotel;
import entity.Room;
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
 *          Implementation of dao Interface for working with Rooms DB.
 *          DB stored in txt file (location of the file is described by constant "PATH")
 * @see DAO
 * @see DAOUsersImpl
 * @see DAOHotelsImpl
 */
public class DAORoomsImpl implements DAO<HashMap<Integer, Room>> {
    final static String PATH = "./ext/DB/Rooms";
    final static char SEPARATOR = (char) 29;

    /**
     * Used for read DBs from the files.
     *
     * @return HashMap<RoomID, Room>
     * @throws IOException
     */
    @Override
    public HashMap<Integer, Room> get() throws IOException {
//        читаем список отелей
        HashMap<Integer, Hotel> hotelsFromFile = new HashMap<>();
        try {
            hotelsFromFile = new DAOHotelsImpl().get();
        } catch (IOException e) {
            System.out.println("Impossible to read the file: " + PATH);
        }
//        читаем список Пользователей
        try {
            HashMap<Integer, User> usersFromFile = new DAOUsersImpl().get();
        } catch (IOException e) {
            System.out.println("Impossible to read the file: " + PATH);
        }
//        Читаем файл построчно
        List<String> currentRoom = null;
        try {
            currentRoom = Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.getMessage();
        }
//        Описываем вид результата
        HashMap<Integer, Room> result = new HashMap<>();
//        бъем каждую cтроку на поля
        for (String str : currentRoom) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, SEPARATOR + "");
            Integer hotelid = Integer.valueOf(stringTokenizer.nextToken());
            Integer roomid = Integer.valueOf(stringTokenizer.nextToken());
            Integer numberOfPerson = Integer.valueOf(stringTokenizer.nextToken());
            Integer price = Integer.valueOf(stringTokenizer.nextToken());

            Room nextRoom = new Room(
                    new Hotel(hotelsFromFile.get(hotelid).getHotelName(), hotelsFromFile.get(hotelid).getCity()),
                    roomid, numberOfPerson, price);
            result.put(nextRoom.hashCode(), nextRoom);
        }
        return result;
    }

    /**
     * Used for writing DB to the file.
     *
     * @param hashMapRooms
     * @see DAO#set(Object)
     */
    @Override
    public void set(HashMap<Integer, Room> hashMapRooms) {
        File roomsFile = new File(PATH);
        try {
            //проверяем, что если файл не существует то создаем его
            File file = new File(PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (FileWriter writer = new FileWriter(roomsFile)) {
            for (HashMap.Entry<Integer, Room> currentEntry : hashMapRooms.entrySet()) {
                writer.write(
                        currentEntry.getValue().getHotel().getId().toString() + SEPARATOR +
                                currentEntry.getValue().getRoomNumber().toString() + SEPARATOR +
                                currentEntry.getValue().getNumberOfPerson().toString() + SEPARATOR +
                                currentEntry.getValue().getPrice().toString());

                writer.write(new StringBuilder().append((char) 13).append((char) 10).toString()); // Конец строки

            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

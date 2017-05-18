package dao;

import entity.Hotel;

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
 *          Implementation of dao Interface for working with Hotels DB.
 *          DB stored in txt file (location of the file is described by constant "PATH")
 * @see DAO
 */
public class DAOHotelsImpl implements DAO<HashMap<Integer, Hotel>> {
    final static String PATH = "./ext/DB/Hotels";
    final static char SEPARATOR = (char) 29;

    /**
     * Used for read DB from the file.
     *
     * @return HashMap<HotelID, Hotel>
     * @throws IOException
     * @see DAO#get()
     */
    @Override
    public HashMap<Integer, Hotel> get() throws IOException {
//        Читаем файл построчно
        List<String> currentHotel = null;
        try {
            //проверяем, что если файл не существует то создаем его
            File file = new File(PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
        }
        try {
            currentHotel = Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Impossible to read the file: " + PATH);
        }
//        Описываем вид результата
        HashMap<Integer, Hotel> result = new HashMap<Integer, Hotel>();
//        бъем каждую cтроку на поля
        for (String str : currentHotel) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, SEPARATOR + "");
            String hotelName = stringTokenizer.nextToken();
            String cityHotel = stringTokenizer.nextToken();
            Hotel nextHotel = new Hotel(hotelName, cityHotel);
            result.put(nextHotel.getId(), nextHotel);
        }
        return result;
    }

    /**
     * Used for writing DB to the file.
     *
     * @@param hashMapHotels
     * @see DAO#set(Object)
     */
    @Override
    public void set(HashMap<Integer, Hotel> hashMapHotels) {
        File hotelsFile = new File(PATH);

        try (FileWriter writer = new FileWriter(hotelsFile)) {
            for (HashMap.Entry<Integer, Hotel> currentEntery : hashMapHotels.entrySet()) {
                writer.write(
                        currentEntery.getValue().getHotelName() + SEPARATOR +
                                currentEntery.getValue().getCity()
                                + (char) 13 + (char) 10 // Конец строки
                );
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
}

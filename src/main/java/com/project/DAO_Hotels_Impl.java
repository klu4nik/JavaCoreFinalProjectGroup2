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
 * Created by MYKOLA.GOROKHOV on 23.04.2017.
 */
public class DAO_Hotels_Impl implements DAO_Hotels_Interface {
    final static String PATH = "../Hotels";
    final static char SEPARATOR = (char) 29;


    @Override
    public HashMap<Integer, Hotel> getHotels() throws IOException {
        //        Читаем файл построчно
        List<String> currentHotel = null;
        try {
            currentHotel = Files.readAllLines(Paths.get(PATH), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.getMessage();
        }
//        Описываем вид результата
        HashMap<Integer, Hotel> result = new HashMap<Integer, Hotel>();
//        бъем каждую cторку на поля
        for (String str : currentHotel) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, SEPARATOR + "");
            if (!stringTokenizer.hasMoreTokens()) {
                throw new IOException("File have incorrect data");
            }
            String hotelName = stringTokenizer.nextToken();
            if (!stringTokenizer.hasMoreTokens()) {
                throw new IOException("File have incorrect data");
            }
            String cityHotel = stringTokenizer.nextToken();

            Hotel nextHotel = new Hotel(hotelName, cityHotel);
            result.put(nextHotel.getId(), nextHotel);
        }
        return result;


    }

    @Override
    public void setHotels(HashMap<Integer, Hotel> hashMapHotels) {
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

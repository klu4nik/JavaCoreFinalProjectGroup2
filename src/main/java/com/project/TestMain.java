import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MYKOLA.GOROKHOV on 20.04.2017.
 */
public class TestMain {
    final static String PATH = "test";

    public static void main(String[] args) throws NoSuchAlgorithmException {

        TestMain.fileGenerator();


    }


    public static void fileWriter() throws IOException {
        File newFile = new File(PATH);
        //  writer = null;

        try (FileWriter writer = new FileWriter(newFile)) {
            //   FileWriter writer = new FileWriter(newFile);
            writer.write("\u001B[33m 123" + (char) 29 + "abc");
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public static void fileGenerator() throws NoSuchAlgorithmException {
        // -------------USER-----------------------
        DAO_Users_Impl dui = new DAO_Users_Impl();
        HashMap hm = new HashMap<String, User>();
        for (int i = 1; i <= 100; i++) {
            User newUser = new User("First Name" + i, "Last Name" + i, "Login" + i, new PasswordHashGenerator().generate("login" + i));
            hm.put(newUser.getLogin(), newUser);

        }
        dui.setUsers(hm);
// ------------END-USER-----------------------
// ------------HOTEL-ROOM-----------------------
        DAO_Hotels_Impl dhi = new DAO_Hotels_Impl();
        DAO_Rooms_Impl dri = new DAO_Rooms_Impl();
        HashMap hot = new HashMap<Integer, Hotel>();
        HashMap roo = new HashMap<Integer, Room>();

        for (int i = 1; i <= 10; i++) {
            Hotel newHotel = new Hotel("Hotel" + i, "City" + i % 3);
            hot.put(newHotel.getId(), newHotel);
            for (int j = 1; j <= 10; j++) {
                Room newRoom = new Room(newHotel, j, 3, 100);
                roo.put(newRoom.hashCode(), newRoom);
            }
        }
        dhi.setHotels(hot);
        dri.setRooms(roo);

    }

}


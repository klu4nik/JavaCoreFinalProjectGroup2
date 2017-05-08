import UI.MainMenu;
import DAO.*;
import Entity.Hotel;
import Entity.Room;
import Entity.User;
import Util.PasswordHashGenerator;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Created by MYKOLA.GOROKHOV on 20.04.2017.
 */
public class TestMain {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {
//        создает файлы (надо будет убрать)
        TestMain.fileGenerator();


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//        Это едиственная строка, которая должна быть в psvm
        new MainMenu().run();
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }

    public static void fileGenerator() throws NoSuchAlgorithmException, IOException, ClassNotFoundException {
        // -------------USER-----------------------
        DAO_Users_Impl_TXT dui = new DAO_Users_Impl_TXT();
        HashMap hm = new HashMap<String, User>();
        for (int i = 1; i <= 100; i++) {
            User newUser = new User("First Name" + i, "Last Name" + i, "Login" + i, PasswordHashGenerator.generate(("pass" + i)));
            hm.put(newUser.getLogin(), newUser);
        }
        dui.set(hm);
// ------------END-USER-----------------------
// ------------HOTEL-ROOM-----------------------
        DAO_Hotels_Impl_TXT dhi = new DAO_Hotels_Impl_TXT();
        DAO_Rooms_Impl_TXT dri = new DAO_Rooms_Impl_TXT();
        HashMap hot = new HashMap<Integer, Hotel>();
        HashMap roo = new HashMap<Integer, Room>();

        for (int i = 1; i <= 10; i++) {
            Hotel newHotel = new Hotel("Hotel" + i, "City" + ((i % 3) + 1));
            hot.put(newHotel.getId(), newHotel);

            for (int j = 1; j <= 10; j++) {
                Room newRoom = new Room(newHotel, j, 3, 100);
                roo.put(newRoom.hashCode(), newRoom);
            }
        }

        dri.set(roo);
        dhi.set(hot);
        DAO_Users_Impl_TXT dui_TXT = new DAO_Users_Impl_TXT();
        DAO_Hotels_Impl_TXT dhi_TXT = new DAO_Hotels_Impl_TXT();
        DAO_Rooms_Impl_TXT dri_TXT = new DAO_Rooms_Impl_TXT();

        dui_TXT.set(dui.get());
        dhi_TXT.set(dhi.get());
        dri_TXT.set(dri.get());
    }
}


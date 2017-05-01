package com.project;

import com.project.API.API_Users;
import com.project.API.MainMenu;
import com.project.DAO.*;
import com.project.Entity.Hotel;
import com.project.Entity.Room;
import com.project.Entity.User;
//import com.project.Util.PasswordHashGenerator;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by MYKOLA.GOROKHOV on 20.04.2017.
 */
public class TestMain {
    final static String PATH = "test";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {

//        for (int i =0; i<100 ; i++){
//            System.out.println(new Util.PasswordHashGenerator().generate("log1in"));
//        }


//       TestMain.fileGenerator();
//
//        DAO_Users_Impl dui = new DAO_Users_Impl();
//        DAO_Hotels_Impl dhi = new DAO_Hotels_Impl();
//        DAO_Rooms_Impl dri = new DAO_Rooms_Impl();
//
//        DAO_Users_Impl_TXT dui_TXT = new DAO_Users_Impl_TXT();
//        DAO_Hotels_Impl_TXT dhi_TXT = new DAO_Hotels_Impl_TXT();
//        DAO_Rooms_Impl_TXT dri_TXT = new DAO_Rooms_Impl_TXT();
//
//        dui_TXT.set(dui.get());
//        dhi_TXT.set(dhi.get());
//        dri_TXT.set(dri.get());
//
//        System.out.println(dui_TXT.get());
//        System.out.println(dhi_TXT.get());
//        System.out.println(dri_TXT.get());
//
//
//        System.out.println(dui.get());
//        System.out.println(dhi.get());
//        System.out.println(dri.get());


        new MainMenu().run();
//        new API_Users().run();


    }

//    public static void fileGenerator() throws NoSuchAlgorithmException, IOException {
//        // -------------USER-----------------------
//        DAO_Users_Impl dui = new DAO_Users_Impl();
//        HashMap hm = new HashMap<String, User>();
//        User userforRoom = null;
//        for (int i = 1; i <= 100; i++) {
//            User newUser = new User("First Name" + i, "Last Name" + i, "Login" + i, PasswordHashGenerator.generate(("pass" + i)));
//            hm.put(newUser.getLogin(), newUser);
//            userforRoom = newUser;
//        }
//
//        dui.set(hm);
// ------------END-USER-----------------------
// ------------HOTEL-ROOM-----------------------
//        DAO_Hotels_Impl dhi = new DAO_Hotels_Impl();
//        DAO_Rooms_Impl dri = new DAO_Rooms_Impl();
//        HashMap hot = new HashMap<Integer, Hotel>();
//        HashMap roo = new HashMap<Integer, Room>();
//
//        for (int i = 1; i <= 10; i++) {
//            Hotel newHotel = new Hotel("Hotel" + i, "City" + ((i % 3) + 1));
//            hot.put(newHotel.getId(), newHotel);
//
//            for (int j = 1; j <= 10; j++) {
//                Room newRoom = new Room(newHotel, j, 3, 100);
//                roo.put(newRoom.hashCode(), newRoom);
//
////                пофиг, что выполняеться несколко раз
//                Room testRoom = new Room(newHotel, 6, 2, 200,
//                        userforRoom
//                        , new Date(15), new Date(50));
//
//                roo.put(testRoom.hashCode(), testRoom);
//
//            }
//        }
//
//        dri.set(roo);
//        dhi.set(hot);
//
//    }
}


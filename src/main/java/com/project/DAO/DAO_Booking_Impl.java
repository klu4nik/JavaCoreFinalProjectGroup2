package DAO;

import Entity.Booking;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Andrey Savchuk on 02/05/2017.
 */
public class DAO_Booking_Impl implements DAO<HashMap<Integer, Booking>>{
    public static final String PATH = "../JavaCoreFinalProjectGroup2/ext/DB/Hotels";

    public HashMap<Integer, Booking> get() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);


            HashMap<Integer, Booking> result = (HashMap<Integer, Booking>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
            return result;
        } catch (IOException e) {
            System.out.println("Impossible to read the file: " + PATH);
            return null;
        }

    }

    @Override
    public void set(HashMap<Integer, Booking> bookingHashMap) throws IOException {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH);

            ObjectOutputStream mapOutputStream = new ObjectOutputStream(fileOutputStream);

            mapOutputStream.writeObject(bookingHashMap);
            mapOutputStream.flush();

            mapOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Impossible to read the file: " + PATH);
        }
    }
}

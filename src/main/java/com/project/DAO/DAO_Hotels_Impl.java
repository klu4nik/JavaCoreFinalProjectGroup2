package DAO;

import Entity.Hotel;

import java.io.*;
import java.util.HashMap;

/**
 * Created by MYKOLA.GOROKHOV on 23.04.2017.
 */
public class DAO_Hotels_Impl implements DAO<HashMap<Integer, Hotel>> {
    final static String PATH = "../JavaCoreFinalProjectGroup2/src/main/java/com/project/DB/Hotels";

    @Override
    public HashMap<Integer, Hotel> get() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(PATH);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        HashMap<Integer, Hotel> result = (HashMap<Integer, Hotel>) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        return result;
    }

    @Override
    public void set(HashMap<Integer, Hotel> hashMapHotels) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(PATH);
        ObjectOutputStream mapOutputStream = new ObjectOutputStream(fileOutputStream);

        mapOutputStream.writeObject(hashMapHotels);
        mapOutputStream.flush();

        mapOutputStream.close();
        fileOutputStream.close();
    }
}

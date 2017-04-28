package DAO;


import Entity.Room;

import java.io.*;
import java.util.HashMap;

/**
 * Created by MYKOLA.GOROKHOV on 23.04.2017.
 */
public class DAO_Rooms_Impl implements DAO<HashMap<Integer, Room>> {
    final static String PATH = "../JavaCoreFinalProjectGroup2/ext/DB/Rooms";

    @Override
    public HashMap<Integer, Room> get() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(PATH);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        HashMap<Integer, Room> result = (HashMap<Integer, Room>) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        return result;
    }

    @Override
    public void set(HashMap<Integer, Room> hashMapRoom) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(PATH);
        ObjectOutputStream mapOutputStream = new ObjectOutputStream(fileOutputStream);

        mapOutputStream.writeObject(hashMapRoom);
        mapOutputStream.flush();

        mapOutputStream.close();
        fileOutputStream.close();
    }

}

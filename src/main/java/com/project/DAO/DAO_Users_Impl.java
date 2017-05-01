package DAO;

import Entity.User;

import java.io.*;
import java.util.HashMap;

/**
 * Created by MYKOLA.GOROKHOV on 22.04.2017.
 */
public class DAO_Users_Impl implements DAO<HashMap<String, User>> {
    final static String PATH = "../JavaCoreFinalProjectGroup2/ext/DB/Users";

    @Override
    public HashMap<String, User> get() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(PATH);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        HashMap<String, User> result = (HashMap<String, User>) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        return result;
    }

    @Override
    public void set(HashMap<String, User> hashMapUser) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(PATH);
        ObjectOutputStream mapOutputStream = new ObjectOutputStream(fileOutputStream);

        mapOutputStream.writeObject(hashMapUser);
        mapOutputStream.flush();

        mapOutputStream.close();
        fileOutputStream.close();
    }
}

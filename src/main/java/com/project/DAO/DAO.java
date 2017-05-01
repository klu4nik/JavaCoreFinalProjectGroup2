package DAO;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by MYKOLA.GOROKHOV on 26.04.2017.
 */
public interface DAO <T>{
    public T get() throws IOException, ClassNotFoundException;

    public void set(T hashMapHotels) throws IOException;

}

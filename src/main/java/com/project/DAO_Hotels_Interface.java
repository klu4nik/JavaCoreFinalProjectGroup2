package main.java.com.project;

import main.java.com.project.Hotel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MYKOLA.GOROKHOV on 22.04.2017.
 */
public interface DAO_Hotels_Interface {
    public HashMap<Integer, Hotel> getHotels() throws IOException;

    public void setHotels(HashMap<Integer, Hotel> hashMapHotels);

}

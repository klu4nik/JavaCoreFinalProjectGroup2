package main.java.com.project;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by MYKOLA.GOROKHOV on 22.04.2017.
 */
public interface DAO_Rooms_Interface {
    public HashMap<Integer, Room> getRooms() throws IOException;

    public void setRooms(HashMap<Integer, Room> hashMapRooms);
}

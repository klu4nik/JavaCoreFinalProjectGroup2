package Controller;

import DAO.DAO_Rooms_Impl_TXT;
import Entity.Hotel;
import Entity.Room;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MYKOLA.GOROKHOV on 30.04.2017.
 */
public class RoomsController {
    private HashMap<Integer, Room> rooms;
    private DAO_Rooms_Impl_TXT dri = new DAO_Rooms_Impl_TXT();

    public RoomsController() {
        try {
            rooms = dri.get();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean isRoomsDBisEmpty(){
        try {
            return dri.get().isEmpty();
        } catch (Exception e){
            return false;
        }
    }

    public void flush() {
        try {
            dri.set(rooms);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public List<Room> findRoomByHotel(Hotel hotelForRoom) {
        try {
            List<Room> result =
                    rooms.values().stream().filter(p -> p.getHotel().equals(hotelForRoom)).collect(Collectors.toList());
            return result;
        } catch (Exception e) {
            return null;
        }

    }

    public HashMap<Integer, Room> deleteRoomByHotel(Hotel hotel) {
        rooms.values().removeIf(p -> p.getHotel().equals(hotel));
        return rooms;
    }

    public HashMap<Integer, Room> deleteRoomByHotelAndNumber(Hotel hotel, Integer roomNumber) {
        rooms.values().removeIf(p -> p.getHotel().equals(hotel) && p.getRoomNumber() == roomNumber);
        return rooms;
    }

    public boolean RoomInHotelExist(Hotel hotel, Integer roomNumber) {

        if (rooms.values().stream().filter(p -> p.getHotel().equals(hotel) && p.getRoomNumber().equals(roomNumber)).count() != 0) {
            return true;
        }
        return false;
    }

    public HashMap<Integer, Room> addRoom(Room room) {
        rooms.put(room.hashCode(), room);
        return rooms;
    }

    public Room getRoomByHotelAndRoomNumber(Hotel hotel, Integer roomNumber) {
        return rooms.values().stream().filter(p -> p.getHotel().equals(hotel) && p.getRoomNumber().equals(roomNumber)).collect(Collectors.toList()).get(0);
    }

    public List<Room> getRoomsByHotelAndNumberOfPersons(Integer hotelID, Integer numberOfPersons) {
        return rooms.values().stream().filter(p -> p.getHotel().getId().equals(hotelID) && p.getNumberOfperson() > numberOfPersons).collect(Collectors.toList());
    }
}

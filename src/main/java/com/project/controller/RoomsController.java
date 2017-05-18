package controller;

import dao.DAORoomsImpl;
import entity.Hotel;
import entity.Room;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version final :)
 *          Class-controller. Provides functionality for working with the database of Rooms.
 * @see DAORoomsImpl
 */
public class RoomsController extends HotelsController {
    private HashMap<Integer, Room> rooms;
    private DAORoomsImpl dri = new DAORoomsImpl();

    /**
     * Read DB from file and put data to the local variable.
     * Is executed once when you create a new entity of class
     */
    public RoomsController() {
        try {
            rooms = dri.get();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Check Rooms DB
     *
     * @return false if DB is Empty
     */
    public boolean isRoomsDBisEmpty() {
        try {
            return dri.get().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Update file with DB
     */
    public void flush() {
        try {
            dri.set(rooms);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Check Rooms DB
     *
     * @param hotelForRoom which we are looking for
     * @return List of room in specified hotel
     */
    public List<Room> findRoomByHotel(Hotel hotelForRoom) {
        try {
            List<Room> result =
                    rooms.values().stream().filter(p -> p.getHotel().equals(hotelForRoom)).collect(Collectors.toList());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Remove Room in Hotel from the DB
     *
     * @param hotel where rooms should be deleted
     * @return updated DB
     */
    public HashMap<Integer, Room> deleteRoomByHotel(Hotel hotel) {
        rooms.values().removeIf(p -> p.getHotel().equals(hotel));
        return rooms;
    }

    /**
     * Remove Room with specified number and Hotel from the DB
     *
     * @param hotel      where rooms should be deleted
     * @param roomNumber number of room which should be deleted
     * @return updated DB
     */
    public HashMap<Integer, Room> deleteRoomByHotelAndNumber(Hotel hotel, Integer roomNumber) {
        rooms.values().removeIf(p -> p.getHotel().equals(hotel) && p.getRoomNumber() == roomNumber);
        return rooms;
    }

    /**
     * Check Rooms DB
     *
     * @param hotel      where rooms we are looking for
     * @param roomNumber number of room we are looking for
     * @return false if room not exist
     */
    public boolean RoomInHotelExist(Hotel hotel, Integer roomNumber) {

        if (rooms.values().stream().filter(p -> p.getHotel().equals(hotel) && p.getRoomNumber().equals(roomNumber)).count() != 0) {
            return true;
        }
        return false;
    }

    /**
     * Add Room to the DB
     *
     * @param room which should be added
     * @return updated DB
     */
    public HashMap<Integer, Room> addRoom(Room room) {
        rooms.put(room.hashCode(), room);
        return rooms;
    }

    /**
     * Find Room with specified number and Hotel in the DB
     *
     * @param hotel      where rooms we are looking for
     * @param roomNumber number of room we are looking for
     * @return Room with specified parameters
     */
    public Room getRoomByHotelAndRoomNumber(Hotel hotel, Integer roomNumber) {
        return rooms.values().stream().filter(p -> p.getHotel().equals(hotel) && p.getRoomNumber().equals(roomNumber)).collect(Collectors.toList()).get(0);
    }

    /**
     * Find Room with specified number of persons and Hotel in the DB
     *
     * @param hotelID         where rooms we are looking for
     * @param numberOfPersons we are looking for
     * @return List of Rooms with specified parameters
     */
    public List<Room> getRoomsByHotelAndNumberOfPersons(Integer hotelID, Integer numberOfPersons) {
        return rooms.values().stream().filter(p -> p.getHotel().getId().equals(hotelID) && p.getNumberOfPerson() >= numberOfPersons).collect(Collectors.toList());
    }
}

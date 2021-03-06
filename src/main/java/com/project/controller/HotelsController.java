package controller;

import dao.DAOHotelsImpl;
import entity.Hotel;

import java.util.HashMap;
import java.util.List;

import java.util.stream.Collectors;

/**
 * @version final :)
 *          Class-controller. Provides functionality for working with the database of Hotels.
 * @see DAOHotelsImpl
 */
public class HotelsController {
    private HashMap<Integer, Hotel> hotels;
    private DAOHotelsImpl dhi = new DAOHotelsImpl();

    /**
     * Read DB from file and put data to the local variable.
     * Is executed once when you create a new entity of class
     */
    public HotelsController() {
        try {
            hotels = dhi.get();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Check Hotels DB
     *
     * @return false if DB is Empty
     */
    public boolean isHotelsDBisEmpty() {
        try {
            return dhi.get().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Update file with DB
     */
    public void flush() {
        try {
            dhi.set(hotels);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Add Hotels to the DB
     *
     * @param hotel which should be added
     * @return updated DB
     */
    public HashMap<Integer, Hotel> addHotel(Hotel hotel) {
        hotels.put(hotel.getId(), hotel);
        return hotels;
    }

    /**
     * Check Hotels DB
     *
     * @param hotel which we are looking for
     * @return Hotel ID or 0 in case Hotel not found
     */
    public Integer findHotel(Hotel hotel) {
        if (hotels.containsValue(hotel)) {
            return hotel.getId();
        }
        return 0;
    }

    /**
     * Remove Hotels from the DB
     *
     * @param hotel which should be deleted
     * @return updated DB
     */
    public HashMap<Integer, Hotel> deleteHotel(Hotel hotel) {
        hotels.remove(hotel.getId());
        RoomsController roomsController = new RoomsController();
        roomsController.deleteRoomByHotel(hotel);
        roomsController.flush();
        return hotels;
    }

    /**
     * Check Hotels DB
     *
     * @param hotelName which we are looking for
     * @return List of Hotels with specified name
     */
    public List<Hotel> findHotelByName(String hotelName) {
        return hotels.values().stream().filter(p -> p.getHotelName().equals(hotelName)).collect(Collectors.toList());
    }

    /**
     * Check Hotels DB
     *
     * @param hotelCity which we are looking for
     * @return List of Hotels in specified city
     */
    public List<Hotel> findHotelByCity(String hotelCity) {
        return hotels.values().stream().filter(p -> p.getCity().equals(hotelCity)).collect(Collectors.toList());
    }

    /**
     * Check Hotels DB
     *
     * @param hotel_id which we are looking for
     * @return List of Hotels with specified id
     */
    public List<Hotel> findHotelById(Integer hotel_id) {
        return hotels.values().stream().filter(p -> p.getId().equals(hotel_id)).collect(Collectors.toList());

    }

    /**
     * Update data in Hotels DB
     *
     * @see HotelsController#deleteHotel(Hotel)
     * @see HotelsController#addHotel(Hotel)
     */
    public HashMap<Integer, Hotel> updateHotel(Hotel oldHotel, Hotel newHotel) {
        deleteHotel(oldHotel);
        addHotel(newHotel);
        return hotels;
    }


}

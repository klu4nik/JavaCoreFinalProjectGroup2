package Controller;

import DAO.DAO_Hotels_Impl_TXT;
import Entity.Hotel;

import java.util.HashMap;
import java.util.List;

import java.util.stream.Collectors;

/**
 * Created by MYKOLA.GOROKHOV on 29.04.2017.
 */
public class HotelsController {
    private HashMap<Integer, Hotel> hotels;
    private DAO_Hotels_Impl_TXT dhi = new DAO_Hotels_Impl_TXT();

    public HotelsController() {
        try {
            hotels = dhi.get();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void flush() {
        try {
            dhi.set(hotels);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public HashMap<Integer, Hotel> addHotel(Hotel hotel) {
        hotels.put(hotel.getId(), hotel);
        return hotels;
    }

    public Integer findHotel(Hotel hotel) {
        if (hotels.containsValue(hotel)) {
            return hotel.getId();
        }
        return 0;
    }

    public HashMap<Integer, Hotel> deleteHotel(Hotel hotel) {
        hotels.remove(hotel.getId());
        return hotels;
    }

    public List<Hotel> findHotelByName(String hotelName) {
        List<Hotel> hotelNames =
                hotels.values().stream().filter(p -> p.getHotelName().equals(hotelName)).collect(Collectors.toList());
        return hotelNames;
    }

    public List<Hotel> findHotelByCity(String hotelCity) {
        List<Hotel> hotelNames =
                hotels.values().stream().filter(p -> p.getCity().equals(hotelCity)).collect(Collectors.toList());
        return hotelNames;
    }

    public HashMap<Integer, Hotel> updateHotel(Hotel oldHotel, Hotel newHotel) {
        deleteHotel(oldHotel);
        addHotel(newHotel);
        return hotels;
    }
}

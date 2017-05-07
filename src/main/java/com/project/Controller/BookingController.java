package Controller;

import DAO.DAO_Booking_Impl_TXT;
import DAO.DAO_Rooms_Impl_TXT;
import Entity.Booking;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Klu4nik on 03/05/2017.
 */
public class BookingController {
    private HashMap<Integer, Booking> booking;
    private DAO_Booking_Impl_TXT dbi = new DAO_Booking_Impl_TXT();
    private DAO_Rooms_Impl_TXT dri = new DAO_Rooms_Impl_TXT();

    public BookingController() {
        try {
            booking = dbi.get();
        } catch (Exception e) {
            e.getMessage();
        }
    }



    public void flush() {
        try {
            dbi.set(booking);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public HashMap<Integer, Booking> addBook(Booking newBook) {
        booking.put(newBook.getUser_login(), newBook);
        return booking;
    }

    public Integer findBook(Booking book) {
        if (booking.containsValue(book)) {
            return book.getId();
        }
        return 0;
    }

    public HashMap<Integer, Booking> deleteBooking(Booking book) {
        booking.remove(book.getId());
        flush();
        return booking;
    }

    public List<Booking> findBookingByUserLogin(String username) {
        List<Booking> books =
                booking.values().stream().filter(p -> p.getUser_login().equals(username)).collect(Collectors.toList());
        return books;
    }

    public List<Booking> findBooksByHotel(Integer hotelID) {

        List<Booking> books =
                booking.values().stream().filter(p -> p.getHotel_id().equals(hotelID)).collect(Collectors.toList());
        return books;
    }

    public List<Booking> findBookingByRoomInHotel(Integer roomNumber, Integer hotelID) {

        List<Booking> books =
                booking.values().stream().filter(p -> p.getRoom_Number().equals(roomNumber)).filter(p -> p.getHotel_id().equals(hotelID)).collect(Collectors.toList());
        return books;
    }

    public HashMap<Integer, Booking> updateHotel(Booking oldBooking, Booking newBooking) {
        deleteBooking(oldBooking);
        addBook(newBooking);
        return booking;
    }


}

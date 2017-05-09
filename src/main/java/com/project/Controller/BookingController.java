package Controller;

import DAO.DAO_Booking_Impl_TXT;
import Entity.Booking;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version final :)
 *          Class-Controller. Provides functionality for working with the database of Booking.
 * @see DAO_Booking_Impl_TXT
 */
public class BookingController {
    private HashMap<Integer, Booking> booking;
    private DAO_Booking_Impl_TXT dbi = new DAO_Booking_Impl_TXT();

    /**
     * Read DB from file and put data to the local variable.
     * Is executed once when you create a new entity of class
     */
    public BookingController() {
        try {
            booking = dbi.get();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Update file with DB
     */
    public void flush() {
        try {
            dbi.set(booking);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Add new Booking to the DB
     *
     * @param newBook which should be added
     * @return updated DB
     */
    public HashMap<Integer, Booking> addBook(Booking newBook) {
        booking.put(newBook.getId(), newBook);
        return booking;
    }

    /**
     * Check Booking DB
     *
     * @param book which we are looking for
     * @return id of specified booking
     */
    public Integer findBook(Booking book) {
        try {
            if (booking.containsValue(book)) {
                System.out.println(book.toString());
                return book.getId();
            }
        } catch (Exception e) {
            return 1;
        }
        return 0;
    }

    /**
     * Remove Booking from the DB
     *
     * @param book which should be deleted
     * @return updated DB
     */
    public HashMap<Integer, Booking> deleteBooking(Booking book) {
        booking.values().removeIf(p -> p.equals(book));
        flush();
        return booking;
    }

    /**
     * Check Booking DB
     *
     * @param userID which we are looking for
     * @return List of Booking with specified User
     */
    public List<Booking> findBookingByUserID(Integer userID) {
        List<Booking> books =
                booking.values().stream().filter(p -> p.getUser_id().equals(userID)).collect(Collectors.toList());
        return books;
    }

    /**
     * Check Booking DB
     *
     * @param hotelID which we are looking for
     * @return List of Booking with specified Hotel
     */
    public List<Booking> findBooksByHotel(Integer hotelID) {

        List<Booking> books =
                booking.values().stream().filter(p -> p.getHotel_id().equals(hotelID)).collect(Collectors.toList());
        return books;
    }

    /**
     * Check Booking DB
     *
     * @param hotelID    which we are looking for
     * @param roomNumber which we are looking for
     * @return List of Booking with specified parameters
     */
    public List<Booking> findBookingByRoomInHotel(Integer roomNumber, Integer hotelID) {
        List<Booking> books =
                booking.values().stream().filter(p -> p.getHotel_id().equals(hotelID) && p.getRoom_Number().equals(roomNumber)).collect(Collectors.toList());
        return books;
    }

    /**
     * Update data in Booking DB
     *
     * @see BookingController#deleteBooking(Booking)
     * @see BookingController#addBook(Booking)
     */
    public HashMap<Integer, Booking> updateBooking(Booking oldBooking, Booking newBooking) {
        deleteBooking(oldBooking);
        addBook(newBooking);
        return booking;
    }


}

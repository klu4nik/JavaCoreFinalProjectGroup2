package Controller;

import DAO.DAO_Booking_Impl_TXT;
import Entity.Booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Andrii Koval on 03.05.2017.
 */
public class BookingController {
    private ArrayList<Booking> bookings;
    private DAO_Booking_Impl_TXT dui = new DAO_Booking_Impl_TXT();

    public BookingController() {
        try {
            bookings = dui.get();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Booking findBookingByDateAndRoom(String nameHotel, Integer roomNumber, Date dateStart, Date dateEnd){
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getHotel_Name().equals(nameHotel) && bookings.get(i).getRoom_Number().equals(roomNumber) && bookings.get(i).getDate_start().getTime() < dateStart.getTime() && bookings.get(i).getDate_end().getTime() > dateStart.getTime()  && bookings.get(i).getDate_start().getTime() < dateEnd.getTime() && bookings.get(i).getDate_end().getTime() > dateEnd.getTime()){
                return bookings.get(i);
            }
        }
        return null;
    }

    public Booking findBookingByUserAndRoom(String userLogin, String nameHotel, Integer roomNumber){
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getUser_login().equals(userLogin) && bookings.get(i).getHotel_Name().equals(nameHotel) && bookings.get(i).getRoom_Number().equals(roomNumber)){
                return bookings.get(i);
            }
        }
        return null;
    }

    public ArrayList<Booking> bookingForUser(Booking booking){
        bookings.add(booking);
        return bookings;
    }

    public ArrayList<Booking> deleteBookingByUserAndRoom(String userLogin, Integer roomNumber){
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getUser_login().equals(userLogin) && bookings.get(i).getRoom_Number().equals(roomNumber)){
                bookings.remove(i);
            }
        }
        return bookings;
    }

    public void flush() {
        try {
            dui.set(bookings);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

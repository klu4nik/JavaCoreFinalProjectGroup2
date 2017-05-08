package API;

import Controller.BookingController;
import Controller.HotelsController;
import Controller.RoomsController;
import Controller.UserController;
import Entity.Booking;
import Entity.Hotel;
import Entity.Room;
import Entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Savchuk Andrey on 05/05/2017.
 */
public class API_Impl {
    BookingController bookingController = new BookingController();
    HotelsController hotelsController = new HotelsController();
    UserController userController = new UserController();
    RoomsController roomsController = new RoomsController();
    private HashMap<Integer, Room> rooms;


    public boolean isAllDBPresented() {
        return !hotelsController.isHotelsDBisEmpty() && !userController.isUsersDBisEmpty() && !roomsController.isRoomsDBisEmpty();
    }

    public String checkLoginIsPresented(String login) {
        boolean state = false;
        if (!login.equals("") && userController.findUserByLogin(login) != null) {
            return login;
        } else {
            return null;
        }

    }

    public Integer findBook(Booking newBook) {
        return bookingController.findBook(newBook);
    }

    public List<Hotel> findHotelByName(String hotel) {
        return hotelsController.findHotelByName(hotel);
    }


    public List<Room> findFreeRoomsForDatesForPersonNumber(Integer hotelID, Integer numberOfPersons, Date startDate, Date endDate) {
        //List<Room> foundRooms = roomsController.findRoomByHotel(hotelsController.findHotelById(hotelID).get(0));
        //Формируем  выборку комнат подходящих в заданном отеле
        List<Room> foundRooms = roomsController.getRoomsByHotelAndNumberOfPersons(hotelID, numberOfPersons);
        Date bookingStartDate;
        Date bookingEndDate;
        //Из выборки  удаляются комнаты  забронированные другими пользователями  на эти  даты
        for (Room tempRoom : foundRooms) {
            for (Booking tempBook : bookingController.findBookingByRoomInHotel(tempRoom.getRoomNumber(), hotelID)) {
                bookingStartDate = tempBook.getDate_start();
                bookingEndDate = tempBook.getDate_end();
                if (tempBook.getRoom_Number().equals(tempRoom.getRoomNumber()) &&
                        tempBook.getHotel_id().equals(tempRoom.getHotel().getId())) {

                    if (!(bookingStartDate.before(startDate) && bookingEndDate.before(startDate)) ||
                            !(bookingStartDate.after(endDate) && (bookingEndDate.after(endDate)) ||
                                    !(bookingStartDate.before(bookingStartDate) && bookingEndDate.after(endDate)))) {
                        foundRooms.remove(tempRoom);
                    }
                }
            }
        }
        return foundRooms;

    }

    public User findUserByLogin(String login) {
        return userController.findUserByLogin(login);
    }

    public void addBook(Booking book) {
        bookingController.addBook(book);
    }

    public List<Booking> findBooksByHotel(Integer id_hotel) {
        try {
            return bookingController.findBooksByHotel(id_hotel);
        } catch (Exception e) {
            return null;
        }

    }

    public Date convertStringToDate(String stringDate) {
        SimpleDateFormat simpleFormat2 = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return simpleFormat2.parse(stringDate);
        } catch (Exception e) {
            return null;
        }

    }

    public String showAdaptedContentFromBooking(Booking book) {
        try {
            return book.getUser_login() + " забронировал комнату №" +
                    book.getRoom_Number().toString() + " в отеле " +
                    hotelsController.findHotelById(book.getHotel_id()).get(0).getHotelName() + " c " +
                    book.getDate_start().toString() + " по " + book.getDate_end().toString();
        } catch (Exception e0) {
            return "Такая бронь не найдена";
        }
    }

    public List<Room> findRoomByHotel(Hotel hotel) {
        return roomsController.findRoomByHotel(hotel);
    }

    public void flushHotel() {
        hotelsController.flush();
    }

    public void flushBooking() {
        bookingController.flush();
    }

    public void flushUser() {
        userController.flush();
    }

    public void flushRoom() {
        roomsController.flush();
    }

    public List<Hotel> findHotelById(Integer hotel_id) {
        return hotelsController.findHotelById(hotel_id);
    }

    public List<Booking> findBookingByLogin(String login) {
        try {
            return bookingController.findBookingByUserLogin(login);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method prints List<Booking>  in the usefull format
     *
     * @param booking
     */
    public void printBooks(List<Booking> booking) {

        try {
            for (Booking tempBook : booking
                    ) {
                System.out.println(showAdaptedContentFromBooking(tempBook));
            }
        } catch (Exception e) {
        }
    }


}

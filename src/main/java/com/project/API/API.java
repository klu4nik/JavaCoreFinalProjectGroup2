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
public class API {
    BookingController bookingController = new BookingController();
    HotelsController hotelsController = new HotelsController();
    UserController userController = new UserController();
    RoomsController roomsController = new RoomsController();
    private HashMap<Integer, Room> rooms;


    public boolean isAllDBPresented() {
        try {
            return !hotelsController.isHotelsDBisEmpty() && !userController.isUsersDBisEmpty() && !roomsController.isRoomsDBisEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public String checkLoginIsPresented(String login) {
        try {
            if (!login.equals("") && userController.findUserByLogin(login) != null) {
                return login;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    public Integer findBook(Booking newBook) {
        try {
            return bookingController.findBook(newBook);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Hotel> findHotelByName(String hotel) {
        try {
            return hotelsController.findHotelByName(hotel);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Methos search fre rooms in hotel for sended number of persons and for sended dates
     *
     * @param hotelID
     * @param numberOfPersons
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Room> findFreeRoomsForDatesForPersonNumber(Integer hotelID, Integer numberOfPersons, Date startDate, Date endDate) {
        //Формируем  выборку комнат подходящих в заданном отеле
        try {
            List<Room> foundRooms = roomsController.getRoomsByHotelAndNumberOfPersons(hotelID, numberOfPersons);
            Date bookingStartDate;
            Date bookingEndDate;
            //Из выборки  удаляются комнаты  забронированные другими пользователями  на эти  даты
            if (foundRooms.size() > 0) {
                for (Room tempRoom : foundRooms) {
                    List<Booking> foundBooks = bookingController.findBookingByRoomInHotel(tempRoom.getRoomNumber(), hotelID);
                    if (foundBooks.size() > 0) {
                        for (Booking tempBook : foundBooks) {
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
                }
                return foundRooms;
            }
        } catch (Exception e) {
            return null;
        }
        return null;


    }

    /**
     * Method search users by login
     *
     * @param login
     * @return
     */
    public User findUserByLogin(String login) {
        try {
            return userController.findUserByLogin(login);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * method add book into the DB
     *
     * @param book
     */
    public void addBook(Booking book) {
        bookingController.addBook(book);
    }

    /**
     * method retirns  list of books founded in sended hotel
     *
     * @param id_hotel
     * @return
     */
    public List<Booking> findBooksByHotel(Integer id_hotel) {
        try {
            return bookingController.findBooksByHotel(id_hotel);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Method converts string dates "MM/dd/yyyy" to Date
     *
     * @param stringDate
     * @return
     */
    public Date convertStringToDate(String stringDate) {
        SimpleDateFormat simpleFormat2 = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return simpleFormat2.parse(stringDate);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Method  convert books to more comfort formate
     *
     * @param book
     * @return
     */
    public String showAdaptedContentFromBooking(Booking book) {
        try {
            return userController.findUserById(book.getUser_id()).getLogin() + " забронировал комнату №" +
                    book.getRoom_Number().toString() + " в отеле " +
                    hotelsController.findHotelById(book.getHotel_id()).get(0).getHotelName() + " c " +
                    book.getDate_start().toString() + " по " + book.getDate_end().toString();
        } catch (Exception e) {
            return "Ошибка поиска";
        }
    }

    /**
     * Method returns rooms from hotel
     *
     * @param hotel
     * @return
     */
    public List<Room> findRoomByHotel(Hotel hotel) {
        return roomsController.findRoomByHotel(hotel);
    }

    /**
     * Method sends changes  to the Hotel_db
     */
    public void flushHotel() {
        hotelsController.flush();
    }

    /**
     * Method sends changes  to the Booking_db
     */
    public void flushBooking() {
        bookingController.flush();
    }

    /**
     * Method sends changes  to the User_db
     */
    public void flushUser() {
        userController.flush();
    }


    /**
     * Method sends changes  to the Room_db
     */
    public void flushRoom() {
        roomsController.flush();
    }

    /**
     * Method search hotels with id
     *
     * @param hotel_id
     * @return
     */
    public List<Hotel> findHotelById(Integer hotel_id) {
        return hotelsController.findHotelById(hotel_id);
    }

    /**
     * Method returns books for user login
     *
     * @param login
     * @return
     */
    public List<Booking> findBookingByLogin(String login) {
        try {
            Integer id = userController.findUserByLogin(login).getId();
            return bookingController.findBookingByUserID(id);
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

    public void removeBook(Booking booking) {
        try {
            bookingController.deleteBooking(booking);
            flushBooking();
        } catch (Exception e) {
            System.out.println("Невозможно удалить бронь");
        }

    }


}

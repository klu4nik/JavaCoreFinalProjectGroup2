package API;

import Controller.BookingController;
import Controller.UserController;
import Entity.Booking;
import Entity.User;
import Util.PasswordHashGenerator;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Andrii Koval on 03.05.2017.
 */
public class API_Bookings {

    private final static String ITEM_1 = "1";
    private final static String ITEM_2 = "2";
    private final static String EXIT = "q";

    private BookingController bookingController = new BookingController();
    private UserController userController = new UserController();

    public void run() throws NoSuchAlgorithmException, IOException, ClassNotFoundException {
        String choice;
        do {
            drawMainMenu();
            //-----------------
            Scanner scanner = new Scanner(System.in);
            choice = String.valueOf(scanner.next().toLowerCase().charAt(0));
            scanner.nextLine();
            //-----------------

            switch (choice) {
                case ITEM_1:
                    cls();
                    bookingRoom();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case ITEM_2:
                    cls();
                    cancelBookingRoom();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case EXIT:
                    System.out.println("Exiting . . .");
                    bookingController.flush();
                    break;
                default:
                    break;
            }

        } while (!choice.equals(EXIT));
    }

    private void cancelBookingRoom() throws NoSuchAlgorithmException {
        System.out.println("+-----------------------------------------+");
        System.out.println("|     Cancellation of room reservation    |");
        System.out.println("+-----------------------------------------+");
        System.out.println();
        System.out.println("Введите Login пользователя для отмены резервирования: ");

        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        while (userController.findUserByLogin(login) == null && !login.equals("")) {

            System.out.println("Такой пользователь не зарегистрирован ...");
            System.out.println("Попробуйте еще раз.\n");
            System.out.println("Введите Login пользователя для отмены резервирования: ");
            login = scanner.nextLine();
        }

        if (!login.equals("")) {
            System.out.println();
            System.out.println("Введите название отеля: ");
            String hotelName = scanner.nextLine();
            System.out.println();
            System.out.println("Введите номер комнаты: ");
            Integer roomNumber = Integer.valueOf(scanner.nextLine());

            if(bookingController.findBookingByUserAndRoom(login, hotelName, roomNumber) == null) {
                System.out.println("Такой брони не существует ...");
                bookingController.flush();
            } else {
                System.out.println("Бронь снимается . . . ");
                bookingController.deleteBookingByUserAndRoom(login, roomNumber);
            }

        }
    }

    private void bookingRoom() throws NoSuchAlgorithmException {

        System.out.println("+-----------------------------------------+");
        System.out.println("|       Booking Room By User Login        |");
        System.out.println("+-----------------------------------------+");
        System.out.println();
        System.out.println("Введите Login пользователя для резервирования комнаты: ");

        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd / MM / yyyy");
        Date dateS = null;
        Date dateE = null;

        while (userController.findUserByLogin(login) == null && !login.equals("")) {

            System.out.println("Такой пользователь не зарегистрирован ...");
            System.out.println("Попробуйте еще раз.\n");
            System.out.println("Введите Login пользователя для резервирования комнаты: ");
            login = scanner.nextLine();
        }

        if (!login.equals("")) {
            System.out.println();
            System.out.println("Введите название отеля: ");
            String hotelName = scanner.nextLine();
            System.out.println();
            System.out.println("Введите номер комнаты: ");
            Integer roomNumber = Integer.valueOf(scanner.nextLine());
            System.out.println();
            System.out.println("Введите дату начала бронирования в формате дд/мм/гггг: ");
            String dateStart = scanner.nextLine();
            try{
                Date dateStartDate = sdf.parse(dateStart);
                dateS = dateStartDate;
            } catch (ParseException e) {
                System.out.println("Wrong date of start!");
            }

            System.out.println();
            System.out.println("Введите дату окончания бронирования в формате дд/мм/гггг: ");
            String dateEnd = scanner.nextLine();
            try{
                Date dateEndDate = sdf.parse(dateEnd);
                dateE = dateEndDate;
            } catch (ParseException e) {
                System.out.println("Wrong date of end!");
            }

            if(bookingController.findBookingByDateAndRoom(hotelName, roomNumber, dateS, dateE) != null) {
                System.out.println("Комната занята в этот период ...");
                bookingController.flush();
            } else {
                System.out.println("Комната бронируется . . . ");
                bookingController.bookingForUser(new Booking(login, hotelName, roomNumber, dateS, dateE));
            }

        }

    }

    private void drawMainMenu() {
        cls();
        System.out.println("+----------------------------------------------+");
        System.out.println("|                     BOOKING MENU             |");
        System.out.println("+----------------------------------------------+");
        System.out.println("|" + ITEM_1 + ". Бронирование комнаты на имя пользователя |");
        System.out.println("|" + ITEM_2 + ". Отмена бронирования комнаты              |");
        System.out.println("|                                              |");
        System.out.println("|" + EXIT + ". В предыдущее меню                          |");
        System.out.println("+----------------------------------------------+");
    }

    private void cls() {
//        System.out.println("*********************************");
        for (int i = 1; i <= 300; i++) {
            System.out.println();
        }
    }
}

package API;

import Controller.BookingController;
import Controller.HotelsController;
import Controller.UserController;
import DAO.DAO_Hotels_Impl_TXT;
import DAO.DAO_Rooms_Impl_TXT;
import DAO.DAO_Users_Impl_TXT;
import Entity.Hotel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Klu4nik on 03/05/2017.
 */
public class API_Booking {
    private final static String ITEM_1 = "1";
    private final static String ITEM_2 = "2";
    private final static String ITEM_3 = "3";
    private final static String ITEM_4 = "4";
    private final static String ITEM_5 = "5";
    private final static String EXIT = "q";

    BookingController bookingController = new BookingController();
    HotelsController hotelsController = new HotelsController();
    UserController userController = new UserController();
    DAO_Rooms_Impl_TXT dri = new DAO_Rooms_Impl_TXT();
    DAO_Users_Impl_TXT dui = new DAO_Users_Impl_TXT();
    DAO_Hotels_Impl_TXT dhi = new DAO_Hotels_Impl_TXT();

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
                    AddBookingMenu();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case ITEM_2:
                    cls();
                    /*editBookingMenu();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();*/
                    break;
                case ITEM_3:
                    cls();
                    DeleteBookingMenu();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case ITEM_4:
                    cls();
                    findBookingByHotelName();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case ITEM_5:
                    cls();
                    //findHotelByCity();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case EXIT:
                    System.out.println("Exiting . . .");
                    hotelsController.flush();
                    break;
                default:
                    break;
            }

        } while (!choice.equals(EXIT));
    }


    private void findBookingByHotelName() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|            FIND BOOKING MENU            |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|          FIND BOOKING BY NAME           |");
        System.out.println("+-----------------------------------------+\n");

        System.out.println("Введите название отеля: ");

        Scanner scanner = new Scanner(System.in);
        String hotelName = scanner.nextLine();
        if (!hotelName.equals("")) {
            System.out.println("Найденые бронирования: ");
            List<Hotel> foundHotels = hotelsController.findHotelByName(hotelName);
            for (Hotel hotel : foundHotels) {
                System.out.println(bookingController.findBooksByHotel(hotel.getId()));
            }
        }
    }
//Нужно доработать
    private void DeleteBookingMenu() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|          DELETE BOOKING MENU            |");
        System.out.println("+-----------------------------------------+\n");

        System.out.println("Введите название, удаляемого отеля: ");

        Scanner scanner = new Scanner(System.in);

        String hotelName = scanner.nextLine();

    }


    private void AddBookingMenu() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|           ADD BOOKING MENU              |");
        System.out.println("+-----------------------------------------+\n");
//        private Integer id;
//        private Integer user_login;
//        private Integer room_Number;
//        private Integer hotel_id;
//        private Date date_start;
//        private Date date_end;
        System.out.println("Введите логин пользователя: ");

        Scanner scanner = new Scanner(System.in);
        String login;
        String hotelName;
        try{
            if (!dhi.get().isEmpty()  && !dri.get().isEmpty() && !dui.get().isEmpty()){

                    login = scanner.nextLine();
                    if (!login.equals("") && !userController.findUserByLogin(login).equals(null)) {

                        System.out.println("Введите название отеля: ");
                        hotelName = scanner.nextLine();
                    } else {
                        drawMainMenu();
                        System.out.println("Такого пользователя не существует");
                    }


            }
        } catch (IOException e){};



    }

    private void drawMainMenu() {
        cls();
        System.out.println("+-----------------------------------------+");
        System.out.println("|               BOOKING MENU              |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|" + ITEM_1 + ". Добавить бронь                        |");
        System.out.println("|" + ITEM_2 + ". Редактировать бронирование            |");
        System.out.println("|" + ITEM_3 + ". Удалить бронь                         |");
        System.out.println("|" + ITEM_4 + ". Поиск брони по имени                  |");
        System.out.println("|" + ITEM_5 + ". Поиск брони по пользователю           |");
        System.out.println("|                                         |");
        System.out.println("|" + EXIT + ". В предыдущее меню                     |");
        System.out.println("+-----------------------------------------+");
    }

    // Можно вынести в отдельную утилиту???
    private void cls() {
//        System.out.println("*********************************");
        for (int i = 1; i <= 300; i++) {
            System.out.println();
        }
    }


}

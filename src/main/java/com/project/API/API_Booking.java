package API;

import Controller.BookingController;
import Controller.HotelsController;
import Controller.RoomsController;
import Controller.UserController;
import DAO.DAO_Hotels_Impl_TXT;
import DAO.DAO_Rooms_Impl_TXT;
import DAO.DAO_Users_Impl_TXT;
import Entity.Hotel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
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
    RoomsController roomsController = new RoomsController();
    DAO_Rooms_Impl_TXT dri = new DAO_Rooms_Impl_TXT();
    DAO_Users_Impl_TXT dui = new DAO_Users_Impl_TXT();
    DAO_Hotels_Impl_TXT dhi = new DAO_Hotels_Impl_TXT();

    public void run() throws NoSuchAlgorithmException, IOException, ClassNotFoundException {
        String choice;
        do {
            drawMainMenu();
            //-----------------
            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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


        Scanner scanner = new Scanner(System.in);
        boolean state = false;
        String login;

        String hotelName;
        Integer room_Number;
        Date date_start;
        Date date_end;
        try {
            if (!dhi.get().isEmpty() && !dri.get().isEmpty() && !dui.get().isEmpty()) {
                login = inputLogin();
                //hotelName = toString(inputHotelName());
                //room_Number =
            } else {
                drawAskMenu("Одна из баз пуста. Необходимо заполнить все базы");
                String choice = String.valueOf(scanner.next().toLowerCase().charAt(0));
                switch (choice) {
                    case ITEM_1:
                        state = false;
                        break;
                    case EXIT:
                        state = true;
                        drawMainMenu();
                        break;
                }


            }
        } catch (IOException e) {
        }



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

    private void drawAskMenu(String message) {
        cls();
        System.out.println(message);
        System.out.println("|" + ITEM_1 + ". Повторить попытку                     |");
        System.out.println("|" + EXIT + ". В главное меню                        |");

    }

    private String inputLogin(){
        Scanner scanner = new Scanner(System.in);
        boolean state = false;
        String login;
        do {
            System.out.println("Введите логин пользователя: ");
            login = scanner.nextLine();
            if (!login.equals("") && userController.findUserByLogin(login) != null) {
                state = true;
            } else {
                boolean state1 = true;
                do {
                    drawAskMenu("Такого пользователя не существует");
                    String choice = String.valueOf(scanner.nextLine().toLowerCase().charAt(0));
                    switch (choice) {
                        case ITEM_1:
                            System.out.println(12);
                            state1 = false;
                            state = false;
                            cls();
                            break;
                        case EXIT:
                            state1 = true;
                            state = true;
                            drawMainMenu();
                            break;
                    }

                } while (state1);
            }

        } while (!state);
        return login;
    }

    private String getHotelIDByName(){
        Scanner scanner = new Scanner(System.in);
        boolean state = false;
        String hotel;
        do {
            System.out.println("Введите логин пользователя: ");
            hotel = scanner.nextLine();
            if (!hotel.equals("") && hotelsController.findHotelByName(hotel) != null) {
                state = true;
            } else {
                boolean state1 = true;
                do {
                    drawAskMenu("Такого отеля не существует");
                    String choice = String.valueOf(scanner.nextLine().toLowerCase().charAt(0));
                    switch (choice) {
                        case ITEM_1:
                            System.out.println(12);
                            state1 = false;
                            state = false;
                            cls();
                            break;
                        case EXIT:
                            state1 = true;
                            state = true;
                            drawMainMenu();
                            break;
                    }

                } while (state1);
            }

        } while (!state);

        for (int i = 0; i < hotelsController.findHotelByName(hotel).size(); i++) {

        }

        return hotel;
    }

    private String inputRoomNumber(String hotelName){
        Scanner scanner = new Scanner(System.in);
        boolean state = false;
        String room;
        do {
            System.out.println("Введите логин пользователя: ");
            room = scanner.nextLine();
            int hotelsNumber = hotelsController.findHotelByName(hotelName).size();
            for (int i = 0; i < hotelsNumber; i++) {
                if (roomsController.findRoomByHotel(hotelsController.findHotelByName(hotelName).get(i)) != null) state = true;
            }

            //Поменять индекс в  этой строчке
            if (!room.equals("") && roomsController.findRoomByHotel(hotelsController.findHotelByName(hotelName).get(0)) != null) {
                state = true;
            } else {
                boolean state1 = true;
                do {
                    drawAskMenu("Такого отеля не существует");
                    String choice = String.valueOf(scanner.nextLine().toLowerCase().charAt(0));
                    switch (choice) {
                        case ITEM_1:
                            System.out.println(12);
                            state1 = false;
                            state = false;
                            cls();
                            break;
                        case EXIT:
                            state1 = true;
                            state = true;
                            drawMainMenu();
                            break;
                    }

                } while (state1);
            }

        } while (!state);

        return room;
    }


}

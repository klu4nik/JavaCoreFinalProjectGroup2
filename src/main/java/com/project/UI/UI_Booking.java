package UI;

import API.API_Impl;
import Entity.Booking;
import Entity.Hotel;
import Entity.Room;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Klu4nik on 03/05/2017.
 */
public class UI_Booking {
    private final static String ITEM_1 = "1";
    private final static String ITEM_2 = "2";
    private final static String ITEM_3 = "3";
    private final static String ITEM_4 = "4";
    private final static String ITEM_5 = "5";
    private final static String EXIT = "q";

    API_Impl apiImpl = new API_Impl();


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
                    break;
                case ITEM_5:
                    cls();
                    drawSearchMenuByLogin();
                    break;
                case EXIT:
                    System.out.println("Exiting . . .");
                    apiImpl.flushBooking();
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
        Integer hotelId = getHotelIDByName();

        apiImpl.printBooks(apiImpl.findBooksByHotel(hotelId));


        Scanner scanner = new Scanner(System.in);
        System.out.println("Нажмите Enter ...");
        scanner.nextLine();
    }


    private void drawSearchMenuByLogin() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|            FIND BOOKING MENU            |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|          FIND BOOKING BY Login          |");
        System.out.println("+-----------------------------------------+\n");
        String login = inputLogin();
        apiImpl.printBooks(apiImpl.findBookingByLogin(login));
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Нажмите Enter ...");
        scanner.nextLine();
    }

    //Нужно доработать

    private void updateBookingMenu() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|          UPDATE BOOKING MENU            |");
        System.out.println("+-----------------------------------------+\n");

        System.out.println("Введите логин пользователя: ");

        Scanner scanner = new Scanner(System.in);
        String login = inputLogin();
        List<Booking> foundBooks = apiImpl.findBookingByLogin(login);

        if (foundBooks != null) {
            System.out.println("Выберите бронирование:");

            for (int i = 0; i < foundBooks.size(); i++) {
                System.out.println(i + ": " + foundBooks.get(i));

            }
            boolean state = false;
            do {
                String choice = String.valueOf(scanner.nextLine().toLowerCase());
                try {
                    Integer choiceInt = Integer.parseInt(choice);
                    if (choiceInt <= foundBooks.size() && choiceInt >= 0) {
                        state = true;
                        System.out.println("Бронь " + apiImpl.showAdaptedContentFromBooking(foundBooks.get(Integer.parseInt(choice))) + " удалена");
                        apiImpl.removeBook(foundBooks.get(Integer.parseInt(choice)));
                        apiImpl.flushBooking();
                        new UI_Booking().AddBookingMenu();

                    } else System.out.println("Введите корректный номер в списке");
                } catch (Exception e) {
                    System.out.println("Введите корректное число.");
                }
            } while (!state);
        } else {
            System.out.println("На выбранные даты нет свободных номеров:");
        }


    }

    private void DeleteBookingMenu() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|          DELETE BOOKING MENU            |");
        System.out.println("+-----------------------------------------+\n");

        System.out.println("Введите логин пользователя: ");

        Scanner scanner = new Scanner(System.in);
        String login = inputLogin();
        List<Booking> foundBooks = apiImpl.findBookingByLogin(login);

        if (foundBooks != null) {
            System.out.println("Выберите бронирование:");

            for (int i = 0; i < foundBooks.size(); i++) {
                System.out.println(i + ": " + foundBooks.get(i));

            }
            boolean state = false;
            do {
                String choice = String.valueOf(scanner.nextLine().toLowerCase());
                try {
                    Integer choiceInt = Integer.parseInt(choice);
                    if (choiceInt <= foundBooks.size() && choiceInt >= 0) {
                        state = true;
                        System.out.println("Бронь " + apiImpl.showAdaptedContentFromBooking(foundBooks.get(Integer.parseInt(choice))) + " удалена");
                        apiImpl.removeBook(foundBooks.get(Integer.parseInt(choice)));
                        apiImpl.flushBooking();

                    } else System.out.println("Введите корректный номер в списке");
                } catch (Exception e) {
                    System.out.println("Введите корректное число.");
                }
            } while (!state);
        } else {
            System.out.println("На выбранные даты нет свободных номеров:");
        }


    }


    private void AddBookingMenu() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|           ADD BOOKING MENU              |");
        System.out.println("+-----------------------------------------+\n");

        Scanner scanner = new Scanner(System.in);
        boolean state = false;
        String login;
        Integer user_id;
        Integer hotelID;
        Integer numberOfPersons;

        String hotelName;
        Integer room_Number;
        Date[] datesBooking = new Date[2];


        if (apiImpl.isAllDBPresented()) {
            login = inputLogin();
            user_id = apiImpl.findUserByLogin(login).getId();
            hotelID = getHotelIDByName();
            datesBooking = setStartEndDate();
            numberOfPersons = getNumberOfPersons();
            room_Number =
                    chooseRoomNumber(apiImpl.findFreeRoomsForDatesForPersonNumber(hotelID, numberOfPersons, datesBooking[0], datesBooking[1]));
            if (room_Number != -1) {
                Booking newBook = new Booking(user_id, room_Number, hotelID, datesBooking[0], datesBooking[1]);
                if (apiImpl.findBook(newBook).equals(0)) {
                    System.out.println("Бронирование добавлено");
                    apiImpl.addBook(newBook);
                    apiImpl.flushBooking();
                    System.out.println();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                } else {
                    System.out.println("\n\nТакой заказ уже есть . . .");
                    AddBookingMenu();
                }
            } else {
                System.out.println("В выбранном отеле нет номеров. создайте другой заказ.");
                try {
                    run();
                } catch (Exception e) {
                }
            }
        } else {
            drawAskMenu("Одна из баз пуста. Необходимо заполнить все базы");
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
        System.out.println("|" + ITEM_4 + ". Поиск брони по имени отеля            |");
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

    private boolean drawAskMenu(String message) {
        cls();
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        System.out.println("|" + ITEM_1 + ". Повторить попытку                     |");
        System.out.println("|" + EXIT + ". В главное меню                        |");
        String choice = String.valueOf(scanner.nextLine().toLowerCase());
        switch (choice) {
            case ITEM_1:
                cls();
                return true;
            case EXIT:
                new UI_Booking().drawMainMenu();
                break;
        }
        return false;
    }


    private String inputLogin() {
        Scanner scanner = new Scanner(System.in);
        boolean state = false;
        String login;
        do {
            System.out.println("Введите логин пользователя: ");
            login = scanner.nextLine();
            if (apiImpl.checkLoginIsPresented(login) != null) {
                state = true;
            } else {
                state = false;
                drawAskMenu("Такого пользователя не существует");
            }
        } while (!state);
        return login;
    }

    private Integer getHotelIDByName() {
        Scanner scanner = new Scanner(System.in);
        boolean state = false;
        String hotel;
        do {
            System.out.println("Введите название отеля: ");
            hotel = scanner.nextLine();
            if (!hotel.equals("") && apiImpl.findHotelByName(hotel).size() != 0) {
                state = true;
            } else {
                state = false;
                drawAskMenu("Такого отеля не существует");
            }
        } while (!state);

        List<Hotel> foundHotels = apiImpl.findHotelByName(hotel);

        int numberOFHotelsWithName = foundHotels.size();
        if (numberOFHotelsWithName > 1) {
            cls();
            state = false;
            System.out.println("Найдено несколько отелей с таким именем. Выберите, пожалуйста, один из списка");
            for (int i = 0; i < numberOFHotelsWithName; i++) {
                System.out.println(i + "." + foundHotels.get(i).toString());
            }
            do {
                String choice = String.valueOf(scanner.nextLine().toLowerCase());
                Integer choiceInt = Integer.parseInt(choice);
                if (choiceInt <= numberOFHotelsWithName && choiceInt > 0) {
                    state = true;
                    return foundHotels.get(Integer.parseInt(choice)).getId();
                } else {

                    System.out.println("Введите корректный номер в списке");
                }

            } while (!state);

        } else if (numberOFHotelsWithName > 0) {
            return foundHotels.get(0).getId();
        }
        return null;
    }


    private Integer chooseRoomNumber(List<Room> foundRooms) {
        Scanner scanner = new Scanner(System.in);
        boolean state = false;
        System.out.println(foundRooms);
        if (foundRooms != null) {
            System.out.println("Выберите комнату:");

            for (int i = 0; i < foundRooms.size(); i++) {
                System.out.println(i + ": " + foundRooms.get(i));

            }
            do {
                String choice = String.valueOf(scanner.nextLine().toLowerCase());
                try {
                    Integer choiceInt = Integer.parseInt(choice);
                    if (choiceInt <= foundRooms.size() && choiceInt >= 0) {
                        state = true;
                        return foundRooms.get(Integer.parseInt(choice)).getRoomNumber();
                    } else System.out.println("Введите корректный номер в списке");
                } catch (Exception e) {
                    System.out.println("Введите корректное число.");
                }
            } while (!state);
        } else {
            System.out.println("На выбранные даты нет свободных номеров:");
        }
        return -1;
    }

    public Integer getNumberOfPersons() {
        System.out.println("Введите количество гостей:");
        Scanner scanner = new Scanner(System.in);

        boolean state = false;
        String text;
        do {
            text = String.valueOf(scanner.nextLine().toLowerCase());
            if (Integer.parseInt(text) > 0) {
                state = true;
                return Integer.parseInt(text);
            } else System.out.println("Неверный ввод!");

        } while (!state);
        return -1;
    }

    public Date[] setStartEndDate() {
        Date startDateBooking;
        Date endDateBooking;
        String date;
        Scanner scanner = new Scanner(System.in);

        boolean state = false;
        boolean state1 = false;

        do {
            System.out.println("Введите  дату заезда в формате MM/dd/yyyy");

            date = scanner.nextLine();
            if (apiImpl.convertStringToDate(date) == null) {
                drawAskMenu("Дата введена некоректно:");
            } else {
                state = true;
            }


        } while (!state);

        startDateBooking = apiImpl.convertStringToDate(date);

        state = false;
        state1 = false;

        do {
            System.out.println("Введите  дату выезда в формате MM/dd/yyyy");

            date = scanner.nextLine();
            if (apiImpl.convertStringToDate(date) == null || apiImpl.convertStringToDate(date).before(startDateBooking)) {
                drawAskMenu("Дата введена некоректно:");
            } else {
                state = true;
            }
        } while (!state);
        endDateBooking = apiImpl.convertStringToDate(date);
        Date[] datesBooking = {startDateBooking, endDateBooking};
        return datesBooking;
    }
}


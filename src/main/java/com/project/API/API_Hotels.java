package API;

import Controller.HotelsController;
import Entity.Hotel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by MYKOLA.GOROKHOV on 29.04.2017.
 */
public class API_Hotels {
    private final static String ITEM_1 = "1";
    private final static String ITEM_2 = "2";
    private final static String ITEM_3 = "3";
    private final static String ITEM_4 = "4";
    private final static String ITEM_5 = "5";
    private final static String EXIT = "q";

    private HotelsController hotelsController = new HotelsController();

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
                    AddHotelMenu();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case ITEM_2:
                    cls();
                    editHotelMenu();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case ITEM_3:
                    cls();
                    DeleteHotelMenu();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case ITEM_4:
                    cls();
                    findHotelByName();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case ITEM_5:
                    cls();
                    findHotelByCity();
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

    private void editHotelMenu() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|             EDIT HOTEL MENU             |");
        System.out.println("+-----------------------------------------+\n");

        System.out.println("Введите название, редактируемого отеля: ");

        Scanner scanner = new Scanner(System.in);
        String oldHotelName = scanner.nextLine();

        String oldHotelCity = "";
        if (!oldHotelName.equals("")) {
            if (hotelsController.findHotelByName(oldHotelName).size() != 0) {
                System.out.println("Есть такие варианты:\n" + hotelsController.findHotelByName(oldHotelName));
                System.out.println("\nВведите город, редактируемого отеля: ");
                oldHotelCity = scanner.nextLine();
            } else {
                System.out.println("\n\nТакого отеля нет в БД . . .");
                editHotelMenu();
            }
        }

        if (!hotelsController.findHotel(new Hotel(oldHotelName, oldHotelCity)).equals(0)) {
            System.out.println("\nВведите новое название (Enter - оствыить старое) :");
            String newHotelName = scanner.nextLine();
            if (newHotelName.equals("")) {
                newHotelName = oldHotelName;
            }
            System.out.println("Новое название отеля: " + newHotelName);

            System.out.println("\nВведите новый город (Enter - оствыить старый) :");
            String newHotelCity = scanner.nextLine();
            if (newHotelCity.equals("")) {
                newHotelCity = oldHotelCity;
            }
            System.out.println("Новый город отеля: " + newHotelCity);

            hotelsController.updateHotel(new Hotel(oldHotelName, oldHotelCity),
                    new Hotel(newHotelName, newHotelCity));
        } else {
            if (!oldHotelCity.equals("")) {
                System.out.println("\n\nТакого отеля нет в БД . . .");
                editHotelMenu();
            }
        }
    }

    private void findHotelByCity() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|             FIND HOTEL MENU             |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|           FIND HOTEL BY CITY            |");
        System.out.println("+-----------------------------------------+\n");

        System.out.println("Введите название города: ");

        Scanner scanner = new Scanner(System.in);
        String hotelCity = scanner.nextLine();
        if (!hotelCity.equals("")) {
            System.out.println("Найденые отели: ");
            System.out.println(hotelsController.findHotelByCity(hotelCity));
        }
    }

    private void findHotelByName() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|             FIND HOTEL MENU             |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|           FIND HOTEL BY NAME            |");
        System.out.println("+-----------------------------------------+\n");

        System.out.println("Введите название отеля: ");

        Scanner scanner = new Scanner(System.in);
        String hotelName = scanner.nextLine();
        if (!hotelName.equals("")) {
            System.out.println("Найденые отели: ");
            System.out.println(hotelsController.findHotelByName(hotelName));
        }
    }

    private void DeleteHotelMenu() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|           DELETE HOTEL MENU             |");
        System.out.println("+-----------------------------------------+\n");

        System.out.println("Введите название, удаляемого отеля: ");

        Scanner scanner = new Scanner(System.in);

        String hotelName = scanner.nextLine();
        String hotelCity = "";
        if (!hotelName.equals("")) {
            System.out.println("Введите город, удаляемого отеля: ");
            hotelCity = scanner.nextLine();
        }

        if (!hotelsController.findHotel(new Hotel(hotelName, hotelCity)).equals(0)) {
            System.out.println("Отель удален");
            hotelsController.deleteHotel(new Hotel(hotelName, hotelCity));
        } else {
            if (!hotelCity.equals("")) {
                System.out.println("\n\nТакого отеля нет в БД . . .");
                DeleteHotelMenu();
            }
        }
    }

    private void AddHotelMenu() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|            ADD HOTEL MENU               |");
        System.out.println("+-----------------------------------------+\n");

        System.out.println("Введите название, для нового отеля: ");

        Scanner scanner = new Scanner(System.in);

        String hotelName = scanner.nextLine();
        String hotelCity = "";
        if (!hotelName.equals("")) {
            System.out.println("Введите город, для нового отеля: ");
            hotelCity = scanner.nextLine();
        }

        if (hotelsController.findHotel(new Hotel(hotelName, hotelCity)).equals(0)) {
            if (!hotelCity.equals("")) {
                System.out.println("Отель добавлен");
                hotelsController.addHotel(new Hotel(hotelName, hotelCity));
            }
        } else {
            System.out.println("\n\nТакой отель уже есть . . .");
            AddHotelMenu();
        }
    }

    private void drawMainMenu() {
        cls();
        System.out.println("+-----------------------------------------+");
        System.out.println("|               HOTEL MENU                |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|" + ITEM_1 + ". Добавить отель                        |");
        System.out.println("|" + ITEM_2 + ". Редактировать данные отеля            |");
        System.out.println("|" + ITEM_3 + ". Удалить отель                         |");
        System.out.println("|" + ITEM_4 + ". Поиск отеля по имени                  |");
        System.out.println("|" + ITEM_5 + ". Поиск отеля по городу                 |");
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

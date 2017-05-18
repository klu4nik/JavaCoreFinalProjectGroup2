package ui;

import controller.RoomsController;
import entity.Hotel;
import entity.Room;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by MYKOLA.GOROKHOV on 30.04.2017.
 */
public class UIRooms {
    private final static String ITEM_1 = "1";
    private final static String ITEM_2 = "2";
    private final static String ITEM_3 = "3";
    private final static String ITEM_4 = "4";
    private final static String EXIT = "q";

    private RoomsController roomsController = new RoomsController();
    private Hotel hotelForRoom = null;

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
                    addRoomHeaderDraw();
                    addRoomMenu();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case ITEM_2:
                    cls();
                    editRoomHeaderDraw();
                    editRoomMenu();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case ITEM_3:
                    cls();
                    deleteRoomByHotelHeaderDraw();
                    DeleteRoomMenu();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case ITEM_4:
                    cls();
                    findRoomByHotelHeaderDraw();
                    findRoomByHotel();
                    System.out.println("Нажмите Enter ...");
                    scanner.nextLine();
                    break;
                case EXIT:
                    System.out.println("Exiting . . .");
                    roomsController.flush();
                    break;
                default:
                    break;
            }
        } while (!choice.equals(EXIT));
    }

    private void editRoomHeaderDraw() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|             EDIT ROOM MENU              |");
        System.out.println("+-----------------------------------------+\n");
    }

    private void editRoomMenu() {
        findRoomByHotel();

        if (hotelForRoom != null && roomsController.findRoomByHotel(hotelForRoom).size() != 0) {
            Integer roomNumberInteger = -1;
            Integer newNumberOfPersonInteger = -1;
            Integer newPriceInteger = -1;

            System.out.println("Введите номер комнаты, которую надо редактировать :");
            Scanner scanner = new Scanner(System.in);
            String roomNumber = scanner.nextLine();
            if (!roomNumber.equals("")) {
                try {
                    roomNumberInteger = Integer.parseInt(roomNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Недопустимое значение...");
                }
                if (roomsController.RoomInHotelExist(hotelForRoom, roomNumberInteger)) {
                    System.out.println("Номер комнаты : " + roomNumber);
                    System.out.println("Введите колличество мест (Enter оставить старое):");
                    String newNumberOfPerson = scanner.nextLine();
                    if (!newNumberOfPerson.equals("")) {
                        try {
                            newNumberOfPersonInteger = Integer.parseInt(newNumberOfPerson);
                        } catch (NumberFormatException e) {
                            System.out.println("Недопустимое значение...");
                            newNumberOfPersonInteger = roomsController.getRoomByHotelAndRoomNumber(hotelForRoom, roomNumberInteger).getNumberOfPerson();
                        }
                    } else {
                        newNumberOfPersonInteger = roomsController.getRoomByHotelAndRoomNumber(hotelForRoom, roomNumberInteger).getNumberOfPerson();
                    }
                    System.out.println("Колличество мест : " + newNumberOfPersonInteger);

                    System.out.println("Введите цену (Enter оставить старую):");
                    String newPrice = scanner.nextLine();
                    if (!newPrice.equals("")) {
                        try {
                            newPriceInteger = Integer.parseInt(newPrice);
                        } catch (NumberFormatException e) {
                            System.out.println("Недопустимое значение...");
                            newPriceInteger = roomsController.getRoomByHotelAndRoomNumber(hotelForRoom, roomNumberInteger).getPrice();
                        }
                    } else {
                        newPriceInteger = roomsController.getRoomByHotelAndRoomNumber(hotelForRoom, roomNumberInteger).getPrice();
                    }
                    System.out.println("Цена : " + newPriceInteger);
                }
                roomsController.deleteRoomByHotelAndNumber(hotelForRoom, roomNumberInteger);
                roomsController.addRoom(new Room(hotelForRoom, roomNumberInteger, newNumberOfPersonInteger, newPriceInteger));
                System.out.println("Данные комнаты обновлены");

            } else {
                System.out.println("Такой комнаты нет");
            }
        }
    }

    private void addRoomHeaderDraw() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|              ADD ROOM MENU              |");
        System.out.println("+-----------------------------------------+\n");
    }

    private void addRoomMenu() {
        findRoomByHotel();
        if (hotelForRoom != null) {
            Integer roomNumberInteger;
            Integer numberOfPersonInteger;
            Integer priceInteger;

            System.out.println("Введите номер комнаты, которую надо добавить :");
            Scanner scanner = new Scanner(System.in);
            String roomNumber = scanner.nextLine();
            try {
                roomNumberInteger = Integer.parseInt(roomNumber);
            } catch (NumberFormatException e) {
                System.out.println("Недопустимое значение...");
                roomNumberInteger = -1;
            }

            if (roomsController.RoomInHotelExist(hotelForRoom, roomNumberInteger)) {
                System.out.println("Такая комната уже есть");
            } else {

                if (roomNumberInteger != -1 && !roomNumber.equals("")) {

                    System.out.println("Введите колличество мест :");
                    String numberOfPerson = scanner.nextLine();

                    try {
                        numberOfPersonInteger = Integer.parseInt(numberOfPerson);
                    } catch (NumberFormatException e) {
                        System.out.println("Недопустимое значение...");
                        numberOfPersonInteger = -1;
                    }

                    if (numberOfPersonInteger > 0 && !numberOfPerson.equals("")) {

                        System.out.println("Введите цену :");
                        String price = scanner.nextLine();

                        try {
                            priceInteger = Integer.parseInt(price);
                        } catch (NumberFormatException e) {
                            System.out.println("Недопустимое значение...");
                            priceInteger = -1;
                        }

                        if (!roomsController.RoomInHotelExist(hotelForRoom, roomNumberInteger)
                                && numberOfPersonInteger > 0
                                && priceInteger >= 0
                                ) {
                            System.out.println("комната добавлена");
                            roomsController.addRoom(new Room(hotelForRoom, roomNumberInteger, numberOfPersonInteger, priceInteger));
                        } else {
                            System.out.println("Такая комната уже есть");
                        }
                    }
                }
            }
        }
    }

    private void deleteRoomByHotelHeaderDraw() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|            DELETE ROOM MENU             |");
        System.out.println("+-----------------------------------------+\n");
    }

    private void DeleteRoomMenu() {
        findRoomByHotel();

        System.out.println("Выберете номер комнаты, которую надо удалить :");

        Scanner scanner = new Scanner(System.in);
        String roomNumber = scanner.nextLine();

        if (!roomNumber.equals("")) {
            Integer roomNumberInteger = 0;
            try {
                roomNumberInteger = Integer.parseInt(roomNumber);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }

            if (roomsController.RoomInHotelExist(hotelForRoom, roomNumberInteger)) {
                roomsController.deleteRoomByHotelAndNumber(hotelForRoom, roomNumberInteger);
                System.out.println("Комната удалена");
            } else {
                System.out.println("Такой комнаты нет");
            }
        }
    }

    private void findRoomByHotelHeaderDraw() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|              FIND ROOM MENU             |");
        System.out.println("+-----------------------------------------+\n");
    }

    private void findRoomByHotel() {
        System.out.println("Введите название отеля: ");

        Scanner scanner = new Scanner(System.in);
        String hotelCity;
        hotelForRoom = null;
        String hotelName = scanner.nextLine();

        if (!hotelName.equals("")) {

            if (roomsController.findHotelByName(hotelName).size() > 0) {

                if (roomsController.findHotelByName(hotelName).size() == 1) {
                    hotelCity = roomsController.findHotelByName(hotelName).get(0).getCity();
                } else {
                    System.out.println("Есть такие варианты : ");
                    System.out.println(roomsController.findHotelByName(hotelName));
                    System.out.println("\nВ каком городе отель?");
                    hotelCity = scanner.nextLine();
                }

                if (roomsController.findHotel(new Hotel(hotelName, hotelCity)) == 0) {
                    System.out.println("нет такого отеля");
                } else {

                    hotelForRoom = new Hotel(hotelName, hotelCity);
                    System.out.println("Найденые комнаты:");
                    System.out.println(roomsController.findRoomByHotel(hotelForRoom));
                }
            }
        }
    }

    private void drawMainMenu() {
        cls();
        System.out.println("+-----------------------------------------+");
        System.out.println("|                ROOM MENU                |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|" + ITEM_1 + ". Добавить комнату в отель              |");
        System.out.println("|" + ITEM_2 + ". Редактировать данные комнаты          |");
        System.out.println("|" + ITEM_3 + ". Удалить комнату из отеля              |");
        System.out.println("|" + ITEM_4 + ". Поиск комнаты по отелю                |");
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

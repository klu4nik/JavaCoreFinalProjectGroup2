package API;

import Controller.HotelsController;
import Controller.RoomsController;
import Entity.Hotel;
import Entity.Room;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by MYKOLA.GOROKHOV on 30.04.2017.
 */
public class API_Rooms {
    private final static String ITEM_1 = "1";
    private final static String ITEM_2 = "2";
    private final static String ITEM_3 = "3";
    private final static String ITEM_4 = "4";
    private final static String EXIT = "q";

    private RoomsController roomsController = new RoomsController();
    private HotelsController hotelsController = new HotelsController();
    private Hotel hotelForRoom = null;

    public void run() throws NoSuchAlgorithmException, IOException, ClassNotFoundException {
        String choice;
        do {
            drawMainMenu();
            //-----------------
            Scanner scanner = new Scanner(System.in);
            choice = String.valueOf(scanner.next().toLowerCase().charAt(0));
            //-----------------
            switch (choice) {
                case ITEM_1:
                    cls();
                    addRoomHederDraw();
                    addRoomMenu();
                    break;
                case ITEM_2:
                    cls();
                    editRoomHederDraw();
                    editRoomMenu();
                    break;
                case ITEM_3:
                    cls();
                    deleteRoomByHotelHederDraw();
                    DeleteRoomMenu();
                    break;
                case ITEM_4:
                    cls();
                    findRoomByHotelHederDraw();
                    findRoomByHotel();
                    break;
                case EXIT:
                    System.out.println("Exiting . . .");
                    roomsController.flush();
                    break;
                default:
                    System.out.println("Wrong Choice");
                    break;
            }

        } while (!choice.equals(EXIT));
    }
    private void editRoomHederDraw() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|             EDIT ROOM MENU              |");
        System.out.println("+-----------------------------------------+\n");
    }

    private void editRoomMenu() {
        findRoomByHotel();
        Integer roomNumberInteger = -1;
//        Integer oldNumberOfPersonInteger;
//        Integer oldPriceInteger;

        Integer newNumberOfPersonInteger;
        Integer newPriceInteger;


        System.out.println("Введите номер комнаты, которую надо редактировать :");
        Scanner scanner = new Scanner(System.in);
        String RoomNumber = scanner.nextLine();
        try {
            roomNumberInteger = Integer.parseInt(RoomNumber);
        } catch (NumberFormatException e) {
            System.err.println("Недопустимое значение...");
        }
        if (roomsController.RoomInHotelExist(hotelForRoom, roomNumberInteger)) {

            System.out.println("Введите колличество мест (Enter оставить старое):");
            String newNumberOfPerson = scanner.nextLine();

            try {
                newNumberOfPersonInteger = Integer.parseInt(newNumberOfPerson);
            } catch (NumberFormatException e) {
                System.err.println("Недопустимое значение...");
               if(newNumberOfPerson.equals("")){
                   newNumberOfPersonInteger = roomsController.getRoomByHotelAndRoomNumber(hotelForRoom,roomNumberInteger).getNumberOfperson();}
            }





            System.out.println("Введите цену (Enter оставить старую):");
            String newPrice = scanner.nextLine();

            try {
                newPriceInteger = Integer.parseInt(newPrice);
            } catch (NumberFormatException e) {
                System.err.println("Недопустимое значение...");
                if(newPrice.equals("")){
                    newPriceInteger = roomsController.getRoomByHotelAndRoomNumber(hotelForRoom,roomNumberInteger).getPrice();}
            }





        } else {
            System.out.println("Такой комнаты нет");
        }



    }

    private void addRoomHederDraw() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|              ADD ROOM MENU              |");
        System.out.println("+-----------------------------------------+\n");
    }

    private void addRoomMenu() {
        findRoomByHotel();

        Integer roomNumberInteger;
        Integer numberOfPersonInteger;
        Integer priceInteger;

        System.out.println("Введите номер комнаты, которую надо добавить :");
        Scanner scanner = new Scanner(System.in);
        String roomNumber = scanner.nextLine();
        try {
            roomNumberInteger = Integer.parseInt(roomNumber);
        } catch (NumberFormatException e) {
            System.err.println("Недопустимое значение...");
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
                    System.err.println("Недопустимое значение...");
                    numberOfPersonInteger = -1;
                }

                if (numberOfPersonInteger > 0 && !numberOfPerson.equals("")) {

                    System.out.println("Введите цену :");
                    String price = scanner.nextLine();

                    try {
                        priceInteger = Integer.parseInt(price);
                    } catch (NumberFormatException e) {
                        System.err.println("Недопустимое значение...");
                        priceInteger = -1;
                    }
                    if (priceInteger >= 0 && !price.equals("")) {

                    }
                    if (!roomsController.RoomInHotelExist(hotelForRoom, roomNumberInteger) &&
                            !numberOfPersonInteger.equals(-1) &&
                            priceInteger.equals(-1)
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

    private void deleteRoomByHotelHederDraw() {
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

            }

            if (roomsController.RoomInHotelExist(hotelForRoom, roomNumberInteger)) {
                roomsController.deleteRoomByHotelAndNumber(hotelForRoom, roomNumberInteger);
            } else {
                System.out.println("Такой комнаты нет");
            }
        }
        System.out.println("Для продолжения нажмите Enter");
        scanner.nextLine();

    }


    private void findRoomByHotelHederDraw() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|              FIND ROOM MENU             |");
        System.out.println("+-----------------------------------------+\n");
    }

    private void findRoomByHotel() {
        System.out.println("Введите название отеля: ");

        Scanner scanner = new Scanner(System.in);
        String hotelCity = null;
        hotelForRoom = null;
        String hotelName = scanner.nextLine();


        if (!hotelName.equals("")) {

            if (hotelsController.findHotelByName(hotelName).size() > 0) {

                if (hotelsController.findHotelByName(hotelName).size() == 1) {
                    hotelCity = hotelsController.findHotelByName(hotelName).get(0).getCity();
                } else {
                    System.out.println("В каком городе отель?");
                    hotelCity = scanner.nextLine();
                }

                if (hotelsController.findHotel(new Hotel(hotelName, hotelCity)) == 0) {
                    System.out.println("нет такого отеля");
                } else {

                    hotelForRoom = new Hotel(hotelName, hotelCity);
                    System.out.println("Найденые комнаты:");
                    System.out.println(roomsController.findRoomByHotel(hotelForRoom));
                }
            } else {
                System.out.println("нет такого отеля");
            }

        }
        System.out.println("Для продолжения нажмите Enter");
        scanner.nextLine();

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

    private void cls() {
        for (int i = 1; i <= 300; i++) {
            System.out.println();
        }
    }
}

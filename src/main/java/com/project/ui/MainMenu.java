package ui;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by MYKOLA.GOROKHOV on 28.04.2017.
 */
public class MainMenu {
    private final static String ITEM_1 = "1";
    private final static String ITEM_2 = "2";
    private final static String ITEM_3 = "3";
    private final static String ITEM_4 = "4";
    private final static String EXIT = "q";

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
                    new UIUsers().run();
                    break;
                case ITEM_2:
                    new UIHotels().run();
                    break;
                case ITEM_3:
                    new UIRooms().run();
                    break;
                case ITEM_4:
                    new UIBooking().run();
                    break;
                case EXIT:
                    cls();
                    System.out.println("Exiting . . .");

                    break;
                default:
                    cls();
                    break;
            }

        } while (!choice.equals(EXIT));

    }

    private void drawMainMenu() {
        cls();
        System.out.println("+-----------------------------------------+");
        System.out.println("|                MAIN MENU                |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|" + ITEM_1 + ". Управление Пользователями             |");
        System.out.println("|" + ITEM_2 + ". Управление Отелями                    |");
        System.out.println("|" + ITEM_3 + ". Управление Комнатами                  |");
        System.out.println("|" + ITEM_4 + ". Управление Бронированием              |");
        System.out.println("|                                         |");
        System.out.println("|" + EXIT + ". ВЫХОД                                 |");
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

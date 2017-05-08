import UI.MainMenu;
import Util.Filegenerator;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by MYKOLA.GOROKHOV on 20.04.2017.
 */
public class TestMain {
    private final static String ITEM_1 = "1";
    private final static String ITEM_2 = "2";


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//        Это едиственная строка, которая должна быть в psvm
        new TestMain().run();
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }

    public void run() throws NoSuchAlgorithmException, IOException, ClassNotFoundException {
        String choice;
        do {
            drawMenu();
            //-----------------
            Scanner scanner = new Scanner(System.in);
            choice = String.valueOf(scanner.next().toLowerCase());
            //-----------------


            switch (choice) {
                case ITEM_1:
                    Filegenerator.fileGenerator();
                    new MainMenu().run();
                    break;
                case ITEM_2:
                    new MainMenu().run();
                    break;
                default:
                    break;
            }
        } while (choice.equals(ITEM_1) || choice.equals(ITEM_2));

    }

    private void drawMenu() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|Создать файлы с тестовыми  данными ?                  |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|" + ITEM_1 + ". Да                                    |");
        System.out.println("|" + ITEM_2 + ". Нет                                   |");

        System.out.println("+-----------------------------------------+");
    }
}


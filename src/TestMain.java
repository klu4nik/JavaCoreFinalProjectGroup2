import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by MYKOLA.GOROKHOV on 20.04.2017.
 */
public class TestMain {
    final static String PATH = "test";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        System.out.println("\u001B[33m 123" + (char) 29 + "abc");
        //TestMain.fileWriter();
        System.out.println(new User("123","123","123","123").toString());
        System.out.println(new Hotel("N","k"));
        System.out.println(new Room(new Hotel("a","a") , 3, 100));



    }


    public static void fileWriter() throws IOException {
        File newFile = new File(PATH);
        //  writer = null;

        try (FileWriter writer = new FileWriter(newFile)) {
            //   FileWriter writer = new FileWriter(newFile);
            writer.write("\u001B[33m 123" + (char) 29 + "abc");
        } catch (Exception e) {
            e.getMessage();
        }

    }

}


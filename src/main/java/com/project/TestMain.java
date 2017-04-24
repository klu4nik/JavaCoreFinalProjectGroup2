import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MYKOLA.GOROKHOV on 20.04.2017.
 */
public class TestMain {
    final static String PATH = "test";

    public static void main(String[] args) throws NoSuchAlgorithmException{
//        System.out.println("\u001B[33m 123" + (char) 29 + "abc");
//        TestMain.fileWriter();
//        System.out.println(new User("123","123","123","123").toString());
//        System.out.println(new Hotel("N","k"));
//        System.out.println(new Room(new Hotel("a","a") , 3, 100));


DAO_Users_Impl nn = new DAO_Users_Impl();

        HashMap hm =new HashMap<String,User>();
        hm.put("aaa", new User("a1a","a1a", "a1a", new PasswordHashGenerator().generate("A1A")));
        hm.put("bbb", new User("a2a","a2a", "a2a", new PasswordHashGenerator().generate("B2B")));
        hm.put("ccc", new User("a3a","a3a", "a3a", new PasswordHashGenerator().generate("A1A")));
        hm.put("ddd", new User("a4a","a4a", "a4a", new PasswordHashGenerator().generate("B2B")));

        hm.put("ogin", new User("2","3", "ogin", "123"));
        User userForRoom = new User("FN","LN", "log", new PasswordHashGenerator().generate("123"));

        hm.put(userForRoom.getLogin(),userForRoom);


nn.setUsers(hm);
//        try {
//            System.out.println(nn.getUsers().toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        DAO_Hotels_Impl dhi = new DAO_Hotels_Impl();
//        HashMap<Integer, Hotel> hm1 = new HashMap<Integer, Hotel>();
        Hotel h1 = new Hotel("qqq","AA");
        Hotel h2 = new Hotel("qq1","AA");
        Hotel h3 = new Hotel("qq2","AA");
        Hotel h4 = new Hotel("qq3","AA");
        Hotel h5 = new Hotel("qq4","AA");
        Hotel h6 = new Hotel("qqq","AA1");
//        hm1.put(h1.getId(),h1);
//        hm1.put(h2.getId(),h2);
//        hm1.put(h3.getId(),h3);
//        hm1.put(h4.getId(),h4);
//        hm1.put(h5.getId(),h5);
//        hm1.put(h6.getId(),h6);
//
//        dhi.setHotels(hm1);
//
//        try {
//            System.out.println(dhi.getHotels().toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        HashMap<Integer, Room> hm2 = new HashMap<Integer, Room>();
        Room room1 = new Room(h1,1, 3, 300);
        Room room2 = new Room(h1, 2,3, 100);
        Room room3 = new Room(h1, 3,3, 300);


        Room room4 = new Room(h1, 15, 3, 100, userForRoom, new Date(),new Date());
        hm2.put(room1.hashCode(),room1);
        hm2.put(room2.hashCode(),room2);
        hm2.put(room3.hashCode(),room3);
        hm2.put(room4.hashCode(),room4);
//        System.out.println(room4);

        DAO_Rooms_Impl dri = new DAO_Rooms_Impl();

        dri.setRooms(hm2);
        try {
            System.out.println(dri.getRooms().entrySet().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


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


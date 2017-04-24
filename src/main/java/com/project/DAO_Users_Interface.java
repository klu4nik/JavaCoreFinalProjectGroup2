import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MYKOLA.GOROKHOV on 22.04.2017.
 */
public interface DAO_Users_Interface {
    public HashMap<String,User> getUsers() throws IOException, NoSuchAlgorithmException;
    public void setUsers(HashMap<String,User> hashMapUsers);
}

package data;

import java.util.Iterator;
import java.util.List;

public class UserDataHandler extends DataHandler {
    private static final String USER_FILE = "src/main/resources/users.txt";

    public static List<String> getAllUsers() {
        return readFile(USER_FILE);
    }

    public static void addUser(String userLine) {
        List<String> users = getAllUsers();
        users.add(userLine);
        writeFile(USER_FILE, users);
    }

    public static void deleteUser(String username) {
        List<String> users = getAllUsers();
        Iterator<String> iterator = users.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.contains(username)) {
                iterator.remove();
            }
        }
        writeFile(USER_FILE, users);
    }
}

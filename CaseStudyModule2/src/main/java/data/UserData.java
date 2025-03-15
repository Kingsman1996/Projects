package data;

import user.User;

public class UserData extends Data {
    public static void addUser(User user) {
        appendToFile(USER_FILE, user.toString());
    }
}

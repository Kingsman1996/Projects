package data;

import user.User;

public class UserData extends Data {
    public static void saveUser(User user) {
        Data.appendToFile(USER_FILE, user.toString());
    }
}

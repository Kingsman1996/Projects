package useraction;

import data.Data;
import message.UserMessage;
import user.Customer;
import user.User;
import validate.Validator;

import java.util.List;
import java.util.Scanner;

public class UserAction {
    protected static final Scanner scanner = new Scanner(System.in);
    private static final String filePath = Data.getUserFile();
    private static final List<String> users = Data.readFile(filePath);

    public static void register() {
        String username = inputUsername();
        while (isExistUserName(username)) {
            UserMessage.userNameExisted();
            username = inputUsername();
        }
        String password = inputPassword();
        UserMessage.registerSuccess();
        Data.appendToFile(filePath, username + "," + password + ",false");
        UserMessage.loginSuccess();
        action(username);
    }

    public static void login() {
        String username = inputUsername();
        while (!isExistUserName(username)) {
            UserMessage.wrongUserName();
            username = inputUsername();
        }
        String password = inputPassword();
        while (!isExistUser(username, password)) {
            UserMessage.wrongPassword();
            password = inputPassword();
        }
        UserMessage.loginSuccess();
        UserMessage.welcome(username);
        action(username);
    }

    protected static String inputUsername() {
        UserMessage.enterUserName();
        String userName = scanner.nextLine();
        while (!Validator.isValidUserName(userName)) {
            UserMessage.inValidUserName();
            userName = scanner.nextLine();
        }
        return userName;
    }

    protected static String inputPassword() {
        UserMessage.enterPassword();
        String password = scanner.nextLine();
        while (!Validator.isValidPassword(password)) {
            UserMessage.inValidPassword();
            password = scanner.nextLine();
        }
        return password;
    }

    protected static boolean isExistUser(String inputUsername, String inputPassword) {
        for (String user : users) {
            String userName = user.split(",")[0];
            String password = user.split(",")[1];
            if (userName.equals(inputUsername) && password.equals(inputPassword)) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isExistUserName(String inputUsername) {
        for (String user : users) {
            String userName = user.split(",")[0];
            if (userName.equals(inputUsername)) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isAdmin(String inputUsername) {
        for (String user : users) {
            String userName = user.split(",")[0];
            String isAdmin = user.split(",")[2];
            if (userName.equals(inputUsername) && isAdmin.equals("true")) {
                return true;
            }
        }
        return false;
    }

    public static void action(String username) {
        if (isAdmin(username)) {
            AdminAction.action();
        } else {
            CustomerAction.action();
        }
    }
}

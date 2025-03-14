package actors.user;

import data.Data;
import message.UserMessage;
import validate.Validator;

import java.util.List;
import java.util.Scanner;

public class UserAction {
    protected static final Scanner scanner = new Scanner(System.in);
    protected static final List<String> productList = Data.readFile(Data.getProductFile());

    public static String inputProductName() {
        UserMessage.enterProductName();
        while (true) {
            int choice = Validator.inputValidInt();
            switch (choice) {
                case 1:
                    return "Fried Rice";
                case 2:
                    return "Milk Tea";
                case 3:
                    return "Pizza";
                case 4:
                    return "Sting";
                case 0:
                    return null;
                default:
                    UserMessage.invalidChoice();
            }
        }
    }

    public static String inputProductPrice() {
        UserMessage.enterProductPrice();
        String productPrice;
        while (true) {
            productPrice = scanner.nextLine().trim();
            if (!Validator.isValidProductPrice(productPrice)) {
                UserMessage.invalidPrice();
            } else {
                break;
            }
        }
        return productPrice;
    }

    public static String inputProductSize() {
        UserMessage.enterProductSize();
        while (true) {
            int choice = Validator.inputValidInt();
            switch (choice) {
                case 1:
                    return "Small";
                case 2:
                    return "Medium";
                case 3:
                    return "Large";
                case 4:
                    return "";
                case 0:
                    return null;
                default:
                    UserMessage.invalidChoice();
            }
        }
    }

    public static void register() {
        String username = inputUsername();
        String password = inputPassword();
        while (isExistUser(username, password)) {
            UserMessage.userNameExisted();
            username = inputUsername();
            password = inputPassword();
        }
        UserMessage.registerSuccess();
        Data.appendToFile(Data.getUserFile(), username + "," + password + ",false");
    }

    public static void login() {
        String inputUsername = inputUsername();
        String inputPassword = inputPassword();
        if (!isExistUser(inputUsername, inputPassword)) {
            UserMessage.wrongUser();
        } else {
            UserMessage.welcome(inputUsername);
            if (isAdmin(inputUsername)) {
                AdminAction.handle();
            } else {
                CustomerAction.handle();
            }
        }
    }

    public static String inputUsername() {
        UserMessage.enterUserName();
        String userName = scanner.nextLine();
        while (!Validator.isValidUserName(userName)) {
            UserMessage.inValidUserName();
            userName = scanner.nextLine();
        }
        return userName;
    }

    public static String inputPassword() {
        UserMessage.enterPassword();
        String password = scanner.nextLine();
        while (!Validator.isValidPassword(password)) {
            UserMessage.inValidPassword();
            password = scanner.nextLine();
        }
        return password;
    }

    public static boolean isExistUser(String inputUsername, String inputPassword) {
        for (String user : Data.readFile(Data.getUserFile())) {
            String userName = user.split(",")[0];
            String password = user.split(",")[1];
            if (userName.equals(inputUsername) && password.equals(inputPassword)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAdmin(String inputUsername) {
        for (String user : Data.readFile(Data.getUserFile())) {
            String userName = user.split(",")[0];
            String isAdmin = user.split(",")[2];
            if (userName.equals(inputUsername) && isAdmin.equals("true")) {
                return true;
            }
        }
        return false;
    }
}

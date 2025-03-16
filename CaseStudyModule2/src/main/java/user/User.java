package user;

import data.Data;
import data.UserData;
import message.UserMessage;
import validate.Validator;

import java.util.List;
import java.util.Scanner;

public abstract class User {
    private String username;
    private String password;
    private boolean admin = false;
    protected static final Scanner scanner = new Scanner(System.in);
    private static final String filePath = Data.getUserFile();
    private static final List<String> users = Data.readFile(filePath);

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.admin = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public static void register() {
        String username = inputUsername();
        while (isExistUserName(username)) {
            UserMessage.userNameExisted();
            username = inputUsername();
        }
        String password = inputPassword();
        UserMessage.registerSuccess();
        UserData.saveUser(new Customer(username, password));
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

    protected static boolean isAdmin(String username) {
        for (String user : users) {
            String userName = user.split(",")[0];
            String isAdmin = user.split(",")[2];
            if (userName.equals(username) && isAdmin.equals("true")) {
                return true;
            }
        }
        return false;
    }

    public static void action(String username) {
        if (isAdmin(username)) {
            Admin.action();
        } else {
            Customer.action();
        }
    }

    @Override
    public String toString() {
        return username + "," + password + "," + admin;
    }
}
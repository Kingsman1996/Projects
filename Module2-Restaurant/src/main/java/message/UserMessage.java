package message;

public class UserMessage {
    public static void systemTitle() {
        System.out.println("==== RESTAURANT MANAGEMENT SYSTEM ====");
        System.out.println("Welcome, guest!");
        whatToDo();
        System.out.println("1. Login");
        System.out.println("2. Register");
        exit();
        enterChoice();
    }

    public static void enterUserName() {
        System.out.println("Enter user name: ");
    }

    public static void userNameExisted() {
        System.out.println("Username existed, try another one!");
    }

    public static void enterPassword() {
        System.out.println("Enter password: ");
    }

    public static void inValidUserName() {
        System.out.println("Invalid user name, try again");
    }

    public static void inValidPassword() {
        System.out.println("Invalid password, try again");
    }

    public static void wrongUserName() {
        System.out.println("Wrong username, try again");
    }

    public static void wrongPassword() {
        System.out.println("Wrong password,try again");
    }

    public static void registerSuccess() {
        System.out.println("Register successful!");
    }

    public static void loginSuccess() {
        System.out.println("Login successful!");
    }

    public static void welcome(String username) {
        System.out.println("Welcome, " + username + "!");
    }

    public static void enterChoice() {
        System.out.println("Enter your choice.");
    }

    public static void exit() {
        System.out.println("0. Exit");
    }

    public static void back() {
        System.out.println("Press any key to back to the main menu...");
    }

    public static void invalidChoice() {
        System.out.println("Invalid choice, Enter again.");
    }

    public static void whatToDo() {
        System.out.println("What would you like to do?");
    }
}

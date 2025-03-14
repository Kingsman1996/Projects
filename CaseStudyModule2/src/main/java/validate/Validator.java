package validate;

import data.ProductData;

import java.util.Scanner;

public class Validator {
    private static Scanner scanner = new Scanner(System.in);
    private static final String PRODUCT_PRICE_REGEX = "^\\d+(\\.\\d+)?$";
    private static final String USERNAME_REGEX = "^[A-Za-z0-9]+$";
    private static final String PASSWORD_REGEX = "^[A-Za-z0-9]+$";

    public static boolean isValidProductName(String name) {
        for (String item : ProductData.readProductName()) {
            if (item.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidProductPrice(String price) {
        return price.matches(PRODUCT_PRICE_REGEX);
    }

    public static boolean isValidUserName(String userName) {
        return userName.matches(USERNAME_REGEX);
    }

    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public static int inputValidInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.next();
        }
        int value = scanner.nextInt();
        return value;
    }
}

package actors.user.classses;

import data.ProductDataHandler;
import message.ProductMessage;
import message.UserMessage;
import validate.Validator;

import java.util.List;
import java.util.Scanner;

public class UserActionHandler {
    protected static final Scanner scanner = new Scanner(System.in);
    protected static final List<String> productList = ProductDataHandler.readProducts();

    public static String inputProductName() {
        UserMessage.enterProductName();
        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine();
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
        String productPrice;
        while (true) {
            UserMessage.enterProductPrice();
            productPrice = scanner.nextLine().trim();
            if (!Validator.isValidProductPrice(productPrice)) {
                ProductMessage.invalidProductPrice();
            } else {
                break;
            }
        }
        return productPrice;
    }

    public static String inputProductSize() {
        UserMessage.enterProductSize();
        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    return "Small";
                case 2:
                    return "Medium";
                case 3:
                    return "Large";
                case 0:
                    return null;
                default:
                    UserMessage.invalidChoice();
            }
        }
    }

    public static String inputProductIce() {
        ProductMessage.hasIceOrNot();
        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    return "With ice";
                case 2:
                    return "Without ice";
                case 0:
                    return null;
                default:
                    UserMessage.invalidChoice();
            }
        }
    }

    public static int findProductIndex(String searchName, String searchPrice, String currentSize, String hasIce) {
        for (int i = 0; i < productList.size(); i++) {
            String[] productInfo = productList.get(i).split(", ");

            boolean nameMatches = productInfo[0].equals(searchName);
            boolean priceMatches = productInfo[1].equals(searchPrice);
            boolean sizeMatches = (currentSize.isEmpty() && productInfo.length == 2) ||
                    (productInfo.length > 2 && productInfo[2].equals(currentSize));
            boolean iceMatches = true;
            if (searchName.equals("Milk Tea")) {
                iceMatches = (productInfo.length > 3 && productInfo[3].equals(hasIce));
            }
            if (nameMatches && priceMatches && sizeMatches && iceMatches) {
                return i;
            }
        }
        return -1;
    }
}

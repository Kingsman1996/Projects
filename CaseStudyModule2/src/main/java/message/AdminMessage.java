package message;

import data.Data;

import java.util.List;

import static message.UserMessage.exit;
import static message.UserMessage.whatToDo;

public class AdminMessage {

    public static void choices() {
        whatToDo();
        System.out.println("1. View order list");
        System.out.println("2. View product list");
        System.out.println("3. Add product");
        System.out.println("4. Edit product");
        System.out.println("5. Delete product");
        exit();
    }

    public static void chooseAllProduct() {
        System.out.println("Choose product: ");
        System.out.println("1. Fried Rice");
        System.out.println("2. Milk Tea");
        System.out.println("3. Pizza");
        System.out.println("4. Sting");
    }

    public static void chooseFix() {
        System.out.println("Choose product: ");
        System.out.println("1. Milk Tea");
        System.out.println("2. Pizza");
    }

    public static void chooseSize() {
        System.out.println("Choose size: ");
        System.out.println("1. Small");
        System.out.println("2. Medium");
        System.out.println("3. Large");
    }
    public static void showOrder() {
        List<String> orders = Data.readFile(Data.getOrderFile());
        if (orders.isEmpty()) {
            System.out.println("No order found!");
        } else {
            System.out.println("=== ORDER LIST === ");
            for (String order : orders) {
                System.out.println(order);
            }
        }
    }
}

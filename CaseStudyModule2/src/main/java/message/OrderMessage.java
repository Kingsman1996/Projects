package message;

import data.OrderDataHandler;
import actors.product.classes.Product;

import java.util.List;

public class OrderMessage {
    public static void alertOrderStart(int id) {
        System.out.println("Processing order, ID: " + id);
    }

    public static void alertOrderEnd(int id) {
        System.out.println("Order " + id + " completed.");
    }

    public static void alertPreparingProduct(Product product) {
        System.out.println("Preparing " + product.getName() + "...");
    }

    public static void alertReadyProduct(Product product) {
        System.out.println(product.getName() + " is ready.");
    }

    public static void showOrder() {
        System.out.println("=== Order List ===");
        List<String> orders = OrderDataHandler.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (String order : orders) {
                System.out.println(order);
            }
        }
        UserMessage.back();
    }
}


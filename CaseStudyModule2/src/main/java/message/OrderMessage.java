package message;

import data.Data;

import java.util.List;

public class OrderMessage {
    public static void alertStart(int id) {
        System.out.println("Processing order, ID: " + id);
    }

    public static void alertEnd(int id) {
        System.out.println("Order " + id + " completed.");
    }

    public static void show() {
        System.out.println("=== Order List ===");
        List<String> orders = Data.readFile(Data.getOrderFile());
        if (orders.isEmpty()) {
            System.out.println("No order found.");
        } else {
            for (String order : orders) {
                System.out.println(order);
            }
        }
    }

    public static void notFound(int id) {
        System.out.println("Order " + id + " not found.");
    }

    public static void added(int id) {
        System.out.println("Order " + id + " added successfully.");
    }
    public static void removed(int id) {
        System.out.println("Order " + id + " removed successfully.");
    }
}


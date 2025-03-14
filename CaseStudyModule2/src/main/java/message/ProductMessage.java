package message;

import actors.product.classes.Product;
import data.Data;

public class ProductMessage {
    public static void added(String name) {
        System.out.println("Product " + name + " is added to menu.");
    }

    public static void fixed() {
        System.out.println("Product information is updated.");
    }

    public static void deleted() {
        System.out.println("Product is deleted!");
    }

    public static void showList() {
        System.out.println("===== PRODUCT LIST =====");
        for (String product : Data.readFile(Data.getProductFile())) {
            System.out.println(product);
        }
        System.out.println();
    }

    public static void isEmpty() {
        System.out.println("Product list is empty.");
    }

    public static void preparing(Product product) {
        System.out.println("Preparing " + product.getName() + "...");
    }

    public static void ready(Product product) {
        System.out.println(product.getName() + " is ready.");
    }
}

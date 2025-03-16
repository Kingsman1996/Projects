package message;

import product.Product;

import java.util.List;

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

    public static void showList(List<Product> list) {
        System.out.println("===== PRODUCT LIST =====");
        if (list.isEmpty()) {
            isEmpty();
            return;
        }
        for (Product product : list) {
            System.out.println(product.toString());
        }
    }

    public static void isEmpty() {
        System.out.println("Product list is empty!");
    }

    public static void nothingToFix() {
        System.out.println("No resizeable product found!");
    }

    public static void notFound() {
        System.out.println("Product not found!");
    }

    public static void preparing(Product product) {
        System.out.println("Preparing " + product.getName() + "...");
    }

    public static void ready(Product product) {
        System.out.println(product.getName() + " is ready.");
    }
}

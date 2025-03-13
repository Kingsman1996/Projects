package message;

import data.ProductDataHandler;

public class ProductMessage {
    public static void addedDone(String name) {
        System.out.println("Product " + name + " is added to menu.");
    }

    public static void addedFailed() {
        System.out.println("Failed to add product.");
    }

    public static void fixedSuccess() {
        System.out.println("Product information has been updated.");
    }

    public static void deletedSuccess() {
        System.out.println("Product is deleted!");
    }

    public static void showProductList() {
        System.out.println("===== PRODUCT LIST =====");
        for (String product : ProductDataHandler.readProducts()) {
            System.out.println(product);
        }
        System.out.println();
        UserMessage.back();
    }

    public static void showProductNames() {
        System.out.println("=== Product Names ===");
        System.out.println(ProductDataHandler.readProductName());
    }

    public static void isEmpty() {
        System.out.println("Product list is empty.");
    }

    public static void notFound() {
        System.out.println("Product not found.");
    }

    public static void invalidProductPrice() {
        System.out.println("Invalid product price, enter again.");
    }

    public static void hasIceOrNot(){
        System.out.println("With ice? ");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }
}

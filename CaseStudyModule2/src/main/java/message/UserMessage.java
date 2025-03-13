package message;

import actors.user.classses.User;
import data.ProductDataHandler;

import java.util.List;

public class UserMessage {
    public static void systemTitle() {
        System.out.println("==== RESTAURANT MANAGEMENT SYSTEM ====");
    }

    public static void welcome(User user) {
        System.out.println("Welcome, " + user.getUsername() + "!");
    }

    public static void makeChoice() {
        System.out.println("Enter your choice.");
    }

    public static void back() {
        System.out.println("Press any key to return to the previous menu...");
    }

    public static void exit() {
        System.out.println("0. Exit");
    }

    public static void invalidChoice() {
        System.out.println("Invalid choice, Enter again.");
    }

    public static void invalidPrice() {
        System.out.println("Invalid price! Enter again.");
    }

    public static void adminChoices() {
        System.out.println("1. View order list");
        System.out.println("2. View product list");
        System.out.println("3. Add product");
        System.out.println("4. Edit product");
        System.out.println("5. Delete product");
        exit();
    }

    public static void customerChoices() {
        System.out.println("Enter your choice: ");
        System.out.println("1. View menu");
        System.out.println("2. Add product to cart");
        System.out.println("3. Remove product from cart");
        System.out.println("4. Confirm order");
        exit();
    }

    public static void enterProductName() {
        System.out.println("Choose product name: ");
        System.out.println("1. Fried Rice");
        System.out.println("2. Milk Tea");
        System.out.println("3. Pizza");
        System.out.println("4. Sting");
        exit();
    }

    public static void enterProductPrice() {
        System.out.print("Enter product price: ");
    }

    public static void enterProductSize() {
        System.out.println("Choose product size: ");
        System.out.println("1. Small");
        System.out.println("2. Medium");
        System.out.println("3. Large");
        exit();
    }

    public static void confirmDelete() {
        System.out.println("Sure to delete this product?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        exit();
    }

    public static void emptyCart() {
        System.out.println("Your cart is empty.");
    }

    public static void showCart() {
        System.out.println("Your cart items:");
        List<String> cartItems = ProductDataHandler.readCart();
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println((i + 1) + ". " + cartItems.get(i));
        }
    }

    public static void confirmOrder() {
        System.out.println("Enter your choice: ");
        System.out.println("1. Confirm order");
        System.out.println("2. Cancel order");
    }
    public static void orderConfirmed() {
        System.out.println("Order confirmed! Thank you.");
    }
    public static void deleteCartItem() {
        System.out.println("Enter product line or 0 to exit: ");
    }

    public static void cartItemIsRemoved() {
        System.out.println("Item removed from cart.");
    }

    public static void addProductToCart() {
        System.out.println("Product added to your cart.");
    }
}

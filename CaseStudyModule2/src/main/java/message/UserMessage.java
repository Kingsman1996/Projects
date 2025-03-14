package message;

import actors.product.classes.Product;
import actors.user.User;

import java.util.List;

public class UserMessage {
    public static void systemTitle() {
        System.out.println("==== RESTAURANT MANAGEMENT SYSTEM ====");
        System.out.println("Welcome, guest!");
        System.out.println("Do you want to login or create a new account?");
        System.out.println("1. Login");
        System.out.println("2. Register");
        exit();
        enterChoice();
    }

    public static void enterUserName() {
        System.out.println("Enter user name: ");
    }
    public static void userNameExisted(){
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

    public static void wrongUser() {
        System.out.println("Wrong username or password");
    }
    public static void registerSuccess() {
        System.out.println("Register successful");
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

    public static void invalidChoice() {
        System.out.println("Invalid choice, Enter again.");
    }

    public static void invalidPrice() {
        System.out.println("Invalid price! Enter again.");
    }

    public static void whatToDo() {
        System.out.println("What would you like to do?");
    }

    public static void adminChoices() {
        whatToDo();
        System.out.println("1. View order list");
        System.out.println("2. View product list");
        System.out.println("3. Add product");
        System.out.println("4. Edit product");
        System.out.println("5. Delete product");
        exit();
    }

    public static void customerChoices() {
        whatToDo();
        System.out.println("1. View menu");
        System.out.println("2. Add product to cart");
        System.out.println("3. Remove product from cart");
        System.out.println("4. Show cart");
        System.out.println("5. Check out");
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
        System.out.println("4. No size");
        exit();
    }

    public static void emptyCart() {
        System.out.println("Your cart is empty.");
    }

    public static void orderConfirmed() {
        System.out.println("Order confirmed! Thank you.");
    }

    public static void itemRemoved() {
        System.out.println("Item removed from cart.");
    }

    public static void enterProductNumber() {
        System.out.print("\nEnter the product number: ");
    }

    public static void addedToCart(Product product) {
        System.out.println("Added to cart: " + product.getName());
    }

    public static void totalCartPrice(List<Product> products) {
        int total = 0;
        System.out.println("Your order:");
        for (Product product : products) {
            System.out.println("-> " + product.getName() + " - " + product.getPrice() + " VND");
            total += product.getPrice();
        }
        System.out.println("Total amount: " + total + " VND");
    }

    public static void showCart(List<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("YOUR CART:");
        for (int i = 0; i < cart.size(); i++) {
            System.out.println((i + 1) + ". " + cart.get(i));
        }
    }
}

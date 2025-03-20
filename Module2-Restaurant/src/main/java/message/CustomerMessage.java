package message;

import product.Product;

import java.util.List;

import static message.UserMessage.exit;
import static message.UserMessage.whatToDo;

public class CustomerMessage {

    public static void choices() {
        whatToDo();
        System.out.println("1. View menu");
        System.out.println("2. Add product to cart");
        System.out.println("3. Remove product from cart");
        System.out.println("4. Show cart");
        System.out.println("5. Check out");
        exit();
    }

    public static void itemRemoved() {
        System.out.println("Item removed from cart.");
    }

    public static void emptyCart() {
        System.out.println("Your cart is empty.");
    }

    public static void totalPrice(List<Product> cart) {
        int total = 0;
        System.out.println("Your order:");
        for (Product item : cart) {
            System.out.println("-> " + item.getName() + " - " + item.getPrice() + " VND");
            total += item.getPrice();
        }
        System.out.println("Total amount: " + total + " VND");
    }

    public static void addedToCart(Product product) {
        System.out.println("Added to cart 1 " + product.getName());
    }

    public static void chooseProduct() {
        System.out.print("Enter product number: ");
    }

    public static void showCart(List<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("YOUR CART ITEMS:");
        for (int i = 0; i < cart.size(); i++) {
            System.out.println((i + 1) + ". " + cart.get(i));
        }
    }

    public static void confirmed() {
        System.out.println("Order confirmed!");
    }

    public static void delivering() {
        System.out.println("Your order is being delivered, thank you!");
    }
}

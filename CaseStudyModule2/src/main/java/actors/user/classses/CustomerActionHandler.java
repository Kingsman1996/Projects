package actors.user.classses;

import data.DataHandler;
import data.OrderDataHandler;
import data.ProductDataHandler;
import message.ProductMessage;
import message.UserMessage;

import java.util.ArrayList;
import java.util.List;

public class CustomerActionHandler extends UserActionHandler {
    private static final String CART_FILE = "src/main/resources/cart.txt";

    public static void addToCart() {
        String productName = inputProductName();
        if (productName == null) return;

        String productPrice = inputProductPrice();
        String productSize = "";
        String hasIce = "";

        if (productName.equals("Pizza") || productName.equals("Milk Tea")) {
            productSize = inputProductSize();
            if (productSize == null) return;
        }
        if (productName.equals("Milk Tea")) {
            hasIce = inputProductIce();
            if (hasIce == null) return;
        }

        String cartItem = productName + ", " + productPrice;
        if (!productSize.isEmpty()) cartItem += ", " + productSize;
        if (!hasIce.isEmpty()) cartItem += ", " + hasIce;
        UserMessage.addProductToCart();
        ProductDataHandler.appendToFile(CART_FILE, cartItem);
    }

    public static void removeFromCart() {
        List<String> cartItems = ProductDataHandler.readCart();
        if (cartItems.isEmpty()) {
            UserMessage.emptyCart();
            return;
        }
        UserMessage.showCart();
        UserMessage.deleteCartItem();
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= cartItems.size()) {
            cartItems.remove(choice - 1);
            ProductDataHandler.writeFile(CART_FILE, cartItems);
            UserMessage.cartItemIsRemoved();
        } else if (choice > cartItems.size()) {
            UserMessage.invalidChoice();
        }
    }

    public static void confirmOrder() {
        List<String> cartItems = ProductDataHandler.readCart();
        if (cartItems.isEmpty()) {
            UserMessage.emptyCart();
            return;
        }
        UserMessage.showCart();
        UserMessage.confirmOrder();
        int confirm = scanner.nextInt();
        scanner.nextLine();

        if (confirm == 1) {
            UserMessage.orderConfirmed();
            DataHandler.appendToFile(OrderDataHandler.getOrderFile(), String.join("|", cartItems));
            DataHandler.writeFile(OrderDataHandler.getCartFile(), new ArrayList<>());
        }
    }

    public static void handleAction(int action) {
        while (action != 0) {
            switch (action) {
                case 1:
                    ProductMessage.showProductList();
                    scanner.nextLine();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    removeFromCart();
                    break;
                case 4:
                    confirmOrder();
                    break;
                case 0:
                    break;
                default:
                    UserMessage.invalidChoice();
            }
            UserMessage.customerChoices();
            action = scanner.nextInt();
            scanner.nextLine();
        }
    }
}

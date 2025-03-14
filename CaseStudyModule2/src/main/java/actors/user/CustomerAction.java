package actors.user;

import actors.order.Order;
import actors.order.OrderProcessor;
import actors.product.ProductManager;
import actors.product.classes.*;
import data.Data;
import message.ProductMessage;
import message.UserMessage;

import java.util.List;

public class CustomerAction extends UserAction {
    private static ProductManager productManager = new ProductManager();
    private static OrderProcessor orderProcessor = new OrderProcessor(null);
    private static Order cart = new Order();

    public static void addToCart() {
        UserMessage.enterProductNumber();
        int index = scanner.nextInt();
        scanner.nextLine();

        List<Product> products = productManager.getAll();
        if (index < 1 || index > products.size()) {
            UserMessage.invalidChoice();
            return;
        }
        Product selectedProduct = products.get(index - 1);
        cart.add(selectedProduct);
        UserMessage.addedToCart(selectedProduct);
    }

    public static void removeFromCart() {
        if (cart.getProducts().isEmpty()){
            UserMessage.emptyCart();
            return;
        }
        UserMessage.enterProductNumber();
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > cart.getProducts().size()) {
            UserMessage.invalidChoice();
            return;
        }
        cart.getProducts().remove(index - 1);
        UserMessage.itemRemoved();
    }

    public static void checkout() {
        if (cart.getProducts().isEmpty()) {
            UserMessage.emptyCart();
            return;
        }
        UserMessage.totalCartPrice(cart.getProducts());
        Data.writeFile(Data.getOrderFile(), productManager.productListToString(cart.getProducts()));
        UserMessage.orderConfirmed();
        orderProcessor.processCart(0, cart.getProducts());
        cart = new Order();
    }

    public static void handle() {
        int action;
        while (true) {
            UserMessage.customerChoices();
            action = scanner.nextInt();
            scanner.nextLine();
            if (action == 0) {
                break;
            }
            switch (action) {
                case 1:
                    ProductMessage.showList();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    removeFromCart();
                    break;
                case 4:
                    UserMessage.showCart(cart.getProducts());
                    break;
                case 5:
                    checkout();
                    break;
                default:
                    UserMessage.invalidChoice();
            }
        }
    }
}

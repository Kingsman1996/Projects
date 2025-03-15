package useraction;

import order.Order;
import order.OrderProcessor;
import product.ProductManager;
import data.Data;
import message.CustomerMessage;
import message.ProductMessage;
import message.UserMessage;
import product.Product;
import validate.Validator;

import java.util.List;

public class CustomerAction extends UserAction {
    private static final ProductManager productManager = new ProductManager();
    private static Order cart = new Order();

    public static void addToCart() {
        ProductMessage.showList(Data.readFile(Data.getProductFile()));
        CustomerMessage.chooseProduct();
        int index = Validator.inputValidInt();

        List<Product> products = productManager.getAll();
        while (index < 1 || index > products.size()) {
            UserMessage.invalidChoice();
            index = Validator.inputValidInt();
        }
        Product selectedProduct = products.get(index - 1);
        cart.add(selectedProduct);
        CustomerMessage.addedToCart(selectedProduct);
    }

    public static void removeFromCart() {
        if (cart.getProducts().isEmpty()) {
            CustomerMessage.emptyCart();
            return;
        }
        CustomerMessage.chooseProduct();
        int index = Validator.inputValidInt();

        while (index < 1 || index > cart.getProducts().size()) {
            UserMessage.invalidChoice();
            index = Validator.inputValidInt();
        }
        cart.getProducts().remove(index - 1);
        CustomerMessage.itemRemoved();
    }

    public static void checkout() {
        if (cart.getProducts().isEmpty()) {
            CustomerMessage.emptyCart();
            return;
        }
        CustomerMessage.totalPrice(cart.getProducts());
        Data.writeFile(Data.getOrderFile(), productManager.toStringList(cart.getProducts()));
        CustomerMessage.confirmed();
        for (Product product : cart.getProducts()) {
            ProductMessage.preparing(product);
        }
        OrderProcessor.processCart(0, cart.getProducts());
        cart = new Order();
        CustomerMessage.delivering();
    }

    public static void action() {
        int action;
        while (true) {
            CustomerMessage.choices();
            action = Validator.inputValidInt();

            while (action < 0 || action > 5) {
                UserMessage.invalidChoice();
                action = Validator.inputValidInt();
            }

            switch (action) {
                case 1:
                    ProductMessage.showList(Data.readFile(Data.getProductFile()));
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    removeFromCart();
                    break;
                case 4:
                    CustomerMessage.showCart(cart.getProducts());
                    break;
                case 5:
                    checkout();
                    break;
                case 0:
                    return;
            }
        }
    }
}

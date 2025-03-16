package user;

import data.Data;
import message.*;
import order.Order;
import order.OrderProcessor;
import product.Product;
import product.ProductManager;
import validate.Validator;

import java.util.List;

public class Customer extends User {
    public Customer() {
    }

    public Customer(String username, String password) {
        super(username, password);
    }
    private static List<Product> productList = new ProductManager().getAll();
    private static Order cart = new Order();

    public static void addToCart() {
        ProductMessage.showList(productList);
        CustomerMessage.chooseProduct();
        int index = Validator.inputValidInt();

        while (index < 1 || index > productList.size()) {
            UserMessage.invalidChoice();
            index = Validator.inputValidInt();
        }
        Product selectedProduct = productList.get(index - 1);
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
        cart.remove(cart.getProduct(index - 1));
        CustomerMessage.itemRemoved();
    }

    public static void checkout() {
        if (cart.getProducts().isEmpty()) {
            CustomerMessage.emptyCart();
            return;
        }
        CustomerMessage.totalPrice(cart.getProducts());
        Data.writeFile(Data.getOrderFile(), new ProductManager().toStringList(cart.getProducts()));
        CustomerMessage.confirmed();
        for (Product product : cart.getProducts()) {
            ProductMessage.preparing(product);
        }
        OrderProcessor.process(cart.getProducts());
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
                    ProductMessage.showList(productList);
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

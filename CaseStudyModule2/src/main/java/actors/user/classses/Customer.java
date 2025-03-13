package actors.user.classses;

import actors.user.interfaces.ViewProductable;
import message.ProductMessage;
import actors.order.Order;
import actors.product.ProductManager;

public class Customer extends User implements ViewProductable {
    private Order order;
    private ProductManager productManager;

    public Customer(String username, String password) {
        super(username, password);
        this.productManager = new ProductManager();
    }

    @Override
    public void viewProducts() {
        ProductMessage.showProductList();
    }
}

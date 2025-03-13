package actors.user.classses;

import actors.product.ProductManager;
import actors.user.interfaces.*;

public class Admin extends User implements AddProductable, ViewProductable, ViewOrderable {
    private ProductManager productManager;

    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
        this.admin = true;
        this.productManager = new ProductManager();
    }

    @Override
    public void viewOrder() {
        AdminActionHandler.handleAction(1);
    }

    @Override
    public void viewProducts() {
        AdminActionHandler.handleAction(2);
    }

    @Override
    public void addProduct() {
        AdminActionHandler.handleAction(3);
    }

    public void editProduct() {
        AdminActionHandler.handleAction(4);
    }

    public void deleteProduct() {
        AdminActionHandler.handleAction(5);
    }
}

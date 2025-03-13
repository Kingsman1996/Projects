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
        AdminActionHandler.handleFirstAction(1);
    }

    @Override
    public void viewProducts() {
        AdminActionHandler.handleFirstAction(2);
    }

    @Override
    public void addProduct() {
        AdminActionHandler.handleFirstAction(3);
    }

    public void editProduct() {
        AdminActionHandler.handleFirstAction(4);
    }

    public void deleteProduct() {
        AdminActionHandler.handleFirstAction(5);
    }
}

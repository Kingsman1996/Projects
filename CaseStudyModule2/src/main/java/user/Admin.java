package user;

import product.ProductManager;

public class Admin extends User {
    private ProductManager productManager;

    public Admin() {
    }

    public Admin(String username, String password) {
        super(username, password);
        this.admin = true;
        this.productManager = new ProductManager();
    }
}

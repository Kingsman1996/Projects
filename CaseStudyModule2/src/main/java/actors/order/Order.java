package actors.order;

import actors.product.classes.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private List<Product> products;

    public Order(int id) {
        this.id = id;
        this.products = new ArrayList<>();
    }

    public void add(Product product) {
        products.add(product);
    }

    public void remove(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id=").append(id).append(", Products=");
        for (int i = 0; i < products.size(); i++) {
            sb.append(products.get(i));
            if (i < products.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}

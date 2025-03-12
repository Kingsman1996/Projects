package order;

import product.classes.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Order: ");
        for (Product item : products) {
            builder.append(item.toString());
        }
        return builder.toString();
    }
}

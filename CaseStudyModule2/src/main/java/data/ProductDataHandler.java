package data;

import java.util.*;

public class ProductDataHandler extends DataHandler {
    private static final String PRODUCT_FILE = "src/main/resources/products.txt";
    private static final String CART_FILE = "src/main/resources/cart.txt";

    public static List<String> readProducts() {
        return readFile(PRODUCT_FILE);
    }

    public static List<String> readCart() {
        return readFile(CART_FILE);
    }

    public static Set<String> readProductName() {
        List<String> products = readProducts();
        Set<String> productNames = new TreeSet<>();

        for (String product : products) {
            productNames.add(product.split(",")[0].trim());
        }
        return productNames;
    }

    public static String getProductFile() {
        return PRODUCT_FILE;
    }
}

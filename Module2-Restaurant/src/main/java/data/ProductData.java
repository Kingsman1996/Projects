package data;

import message.ProductMessage;
import product.*;

import java.util.*;

public class ProductData extends Data {
    public static Set<String> readProductName() {
        List<String> productLines = readFile(PRODUCT_FILE);
        Set<String> productNames = new TreeSet<>();

        for (String line : productLines) {
            productNames.add(line.split(",")[0].trim());
        }
        return productNames;
    }

    public static void saveProduct(Product product) {
        appendToFile(PRODUCT_FILE, product.toString());
        ProductMessage.added(product.getName());
    }
}
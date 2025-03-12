package data;

import java.util.Iterator;
import java.util.List;

public class ProductDataHandler extends DataHandler {
    private static final String PRODUCT_FILE = "src/main/resources/products.txt";

    public static List<String> getAllProducts() {
        return readFile(PRODUCT_FILE);
    }

    public static void addProduct(String productLine) {
        List<String> products = getAllProducts();
        products.add(productLine);
        writeFile(PRODUCT_FILE, products);
    }

    public static void deleteProduct(String productName) {
        List<String> products = getAllProducts();
        Iterator<String> iterator = products.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.contains(productName)) {
                iterator.remove();
            }
        }
        writeFile(PRODUCT_FILE, products);
    }

    public static String getProductFile() {
        return PRODUCT_FILE;
    }
}

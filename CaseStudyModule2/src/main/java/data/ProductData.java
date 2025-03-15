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

    public static Product convertToObject(String productLine) {
        if (productLine == null || productLine.isEmpty()) {
            return null;
        }
        String[] parts = productLine.split(",");
        String name = parts[0].trim();
        switch (name) {
            case "Pizza":
            case "Milk Tea":
                String size = parts[2].trim();
                if (name.equals("Milk Tea")) {
                    return new MilkTea(size);
                } else {
                    return new Pizza(size);
                }
            case "Sting":
                return new Sting();
            case "Fried Rice":
                return new FriedRice();
        }
        return null;
    }

    public static String getProduct(String productName, String productSize) {
        String found = null;
        for (int i = 0; i < readFile(PRODUCT_FILE).size(); i++) {
            String[] parts = readFile(PRODUCT_FILE).get(i).split(",");
            boolean nameMatches = parts[0].trim().equals(productName);
            if (nameMatches) {
                if (parts.length > 2) {
                    if (parts[2].trim().equals(productSize)) {
                        found = parts[0] + ", " + parts[1] + ", " + parts[2];
                        break;
                    }
                } else {
                    found = parts[0] + ", " + parts[1];
                    break;
                }
            }
        }
        return found;
    }
}
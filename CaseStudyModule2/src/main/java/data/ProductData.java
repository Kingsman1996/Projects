package data;

import actors.product.classes.*;
import message.ProductMessage;
import validate.Validator;

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
    }

    public static Product convertToObject(String productLine) {
        if (productLine == null || productLine.isEmpty()) {
            return null;
        }
        String[] productData = productLine.split(",");
        String productName = productData[0].trim();
        String productPrice = productData[1].trim();
        if (productData.length > 2) {
            String productSize = productData[2].trim();
            switch (productName) {
                case "Pizza":
                    return new Pizza(productName, Integer.parseInt(productPrice), productSize);
                case "Milk Tea":
                    return new MilkTea(productName, Integer.parseInt(productPrice), productSize);
            }
        } else {
            switch (productName) {
                case "Sting":
                    return new Sting(productName, Integer.parseInt(productPrice));
                case "Fried Rice":
                    return new FriedRice(productName, Integer.parseInt(productPrice));
            }
        }
        return null;
    }

    public static String findProductInFile(String productName, String productPrice, String productSize) {
        List<String> found = new ArrayList<>();

        for (int i = 0; i < Data.readFile(PRODUCT_FILE).size(); i++) {
            String[] productInfo = Data.readFile(PRODUCT_FILE).get(i).split(",");
            boolean nameMatches = productInfo[0].trim().equals(productName);
            boolean priceMatches = productInfo[1].trim().equals(productPrice);
            if (nameMatches && priceMatches) {
                found.add(productInfo[0]);
                found.add(productInfo[1]);
                if (productInfo.length > 2 && productInfo[2].trim().equals(productSize)) {
                    found.add(productInfo[2]);
                    break;
                }
            }
        }
        return String.join(",", found);
    }
}

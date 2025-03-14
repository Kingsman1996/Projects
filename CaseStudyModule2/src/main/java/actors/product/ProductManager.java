package actors.product;

import actors.product.classes.*;
import data.Data;
import data.ProductData;
import message.ProductMessage;
import validate.Validator;

import java.util.ArrayList;
import java.util.List;

import static data.Data.*;

public class ProductManager {
    private List<Product> productList;
    private final String filePath;

    public ProductManager() {
        productList = new ArrayList<>();
        filePath = ProductData.getProductFile();
        loadFile();
    }

    public void loadFile() {
        for (String line : readFile(filePath)) {
            String[] parts = line.split(",");
            String name = parts[0].trim();
            int price = Integer.parseInt(parts[1].trim());
            if (parts.length == 2) {
                switch (name) {
                    case "Fried Rice":
                        productList.add(new FriedRice(name, price));
                        break;
                    case "Sting":
                        productList.add(new Sting(name, price));
                        break;
                }
            }
            if (parts.length == 3) {
                String size = parts[2].trim();
                switch (name) {
                    case "Milk Tea":
                        productList.add(new MilkTea(name, price, size));
                        break;
                    case "Pizza":
                        productList.add(new Pizza(name, price, size));
                        break;
                }
            }
        }
    }

    public void add(Product product) {
        productList.add(product);
        ProductData.saveProduct(product);
        ProductMessage.added(product.getName());
    }

    public void remove(String name) {
        List<Product> found = getProductByName(name);
        productList.removeAll(found);
        saveChanges();
        ProductMessage.deleted();
    }

    public List<Product> getProductByName(String searchName) {
        List<Product> found = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(searchName)) {
                found.add(product);
            }
        }
        return found;
    }

    public List<Product> getAll() {
        return productList;
    }

    public void fixPrice(String searchName, String newPrice) {
        for (Product product : productList) {
            if (product.getName().equals(searchName)) {
                product.setPrice(Integer.parseInt(newPrice));
            }
        }
        saveChanges();
    }

    public List<String> productListToString(List<Product> input) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            output.add(input.get(i).toString());
        }
        return output;
    }

    public void saveChanges() {
        writeFile(filePath, productListToString(productList));
    }
}

package actors.product;

import actors.product.classes.*;
import data.DataHandler;
import data.ProductDataHandler;
import message.ProductMessage;
import validate.Validator;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> productList;
    private final String filePath;

    public ProductManager() {
        productList = new ArrayList<>();
        filePath = ProductDataHandler.getProductFile();
        loadFile();
    }

    public void loadFile() {
        for (String line : ProductDataHandler.readProducts()) {
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
                        productList.add(new MilkTea(name,price,size));
                        break;
                    case "Pizza":
                        productList.add(new Pizza(name,price,size));
                        break;
                }
            }
        }
    }

    public void add(Product product) {
        productList.add(product);
        DataHandler.appendToFile(filePath, product.toString());
        ProductMessage.addedDone(product.getName());
    }

    public void remove(String name) {
        List<Product> toRemove = getProductByName(name);
        productList.removeAll(toRemove);
        saveChanges();
        ProductMessage.deletedSuccess();
    }

    public List<Product> getProductByName(String searchName) {
        List<Product> found = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(searchName.toLowerCase())) {
                found.add(product);
            }
        }
        return found;
    }

    public List<Product> getAll() {
        return productList;
    }

    public void updateProductPrice(String searchName, String newPrice) {
        if (!Validator.isValidProductPrice(newPrice)) {
            return;
        }
        for (Product product : getProductByName(searchName)) {
            product.setPrice(Integer.parseInt(newPrice));
        }
        saveChanges();
        ProductMessage.fixedSuccess();
    }

    public List<String> productListToString() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            list.add(productList.get(i).toString());
        }
        return list;
    }

    public void saveChanges() {
        DataHandler.writeFile(filePath, productListToString());
    }
}

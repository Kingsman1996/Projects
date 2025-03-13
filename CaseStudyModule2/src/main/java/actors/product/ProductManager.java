package actors.product;

import actors.product.classes.*;
import data.ProductDataHandler;
import message.ProductMessage;
import validate.Validator;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> productList;

    public ProductManager() {
        productList = new ArrayList<>();
        loadProductsFromFile();
    }

    public void loadProductsFromFile() {
        List<String> lines = ProductDataHandler.readProducts();
        for (String line : lines) {
            Product product = createProduct(line.split(",")[0].trim(), line.split(",")[1].trim());
            productList.add(product);
        }
    }

    public void add(Product product) {
        productList.add(product);
        ProductDataHandler.appendToFile(ProductDataHandler.getProductFile(), product.toString());
        ProductMessage.addedDone(product.getName());
    }

    public int findIndex(String searchName) {
        int index = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equals(searchName)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Product getProductByName(String searchName) {
        Product product = null;
        int index = findIndex(searchName);
        if (index != -1) {
            product = productList.get(index);
        }
        return product;
    }

    public List<Product> getProductList() {
        return productList;
    }


    public void remove(String searchName) {
        int index = findIndex(searchName);
        if (index == -1) {
            ProductMessage.notFound();
            return;
        }
        productList.remove(index);
        saveChanges();
        ProductMessage.deletedSuccess();
    }

    public List<String> productListToString() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            list.add(productList.get(i).toString());
        }
        return list;
    }

    public void updateProductPrice(String searchName, String newPrice) {
        int index = findIndex(searchName);
        if (index == -1) {
            ProductMessage.notFound();
            return;
        }
        if (!Validator.isValidProductPrice(newPrice)) {
            return;
        }
        productList.set(index, createProduct(searchName, newPrice));
        saveChanges();
        ProductMessage.fixedSuccess();
    }

    public void saveChanges() {
        ProductDataHandler.writeFile(ProductDataHandler.getProductFile(), productListToString());
    }

    public Product createProduct(String name, String price) {
        int integerPrice = Integer.parseInt(price);
        switch (name.toLowerCase()) {
            case "fried rice":
                return new FriedRice(integerPrice);
            case "milk tea":
                return new MilkTea(integerPrice);
            case "pizza":
                return new Pizza(integerPrice);
            case "sting":
                return new Sting(integerPrice);
            default:
                return null;
        }
    }
}

package product;

import data.Data;
import data.ProductData;
import message.ProductMessage;

import java.util.ArrayList;
import java.util.List;

import static data.Data.*;

public class ProductManager {
    private List<Product> productList;
    private String filePath = Data.getProductFile();

    public ProductManager() {
        productList = new ArrayList<>();
        loadFile();
    }

    public void loadFile() {
        for (String line : readFile(filePath)) {
            String[] parts = line.split(",");
            String name = parts[0].trim();
            if (parts.length == 2) {
                switch (name) {
                    case "Fried Rice":
                        productList.add(new FriedRice());
                        break;
                    case "Sting":
                        productList.add(new Sting());
                        break;
                }
            }
            if (parts.length == 3) {
                String size = parts[2].trim();
                switch (name) {
                    case "Milk Tea":
                        productList.add(new MilkTea(size));
                        break;
                    case "Pizza":
                        productList.add(new Pizza(size));
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

    public void remove(String name, String size) {
        List<Product> found = getProductByName(name);
        if (found.isEmpty()) {
            return;
        }
        boolean foundSize = false;
        for (Product product : found) {
            if (product instanceof Sizeable) {
                if (((Sizeable) product).getSize().equals(size)) {
                    foundSize = true;
                    productList.remove(product);
                    ProductMessage.deleted();
                }
            } else {
                productList.remove(product);
                ProductMessage.deleted();
            }
        }
        saveChanges();
        if (!foundSize) {
            ProductMessage.notFound();
        }
    }

    public List<Product> getProductByName(String searchName) {
        List<Product> found = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(searchName)) {
                found.add(product);
            }
        }
        if (found.isEmpty()) {
            ProductMessage.notFound();
        }
        return found;
    }

    public List<Product> getAll() {
        return productList;
    }

    public void fixSize(String searchName, String newSize) {
        for (Product product : productList) {
            if (product.getName().equals(searchName)) {
                ((Sizeable) product).setSize(newSize);
            }
        }
        if (!productList.isEmpty()) {
            saveChanges();
            ProductMessage.fixed();
        }else {
            ProductMessage.notFound();
        }
    }

    public List<String> toStringList(List<Product> input) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            output.add(input.get(i).toString());
        }
        return output;
    }

    public void saveChanges() {
        writeFile(filePath, toStringList(productList));
    }

    @Override
    public String toString() {
        return productList.toString();
    }
}

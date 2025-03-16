package useraction;

import product.ProductManager;
import data.*;
import message.*;
import product.*;
import validate.Validator;

import java.util.List;

public class AdminAction extends UserAction {
    private static ProductManager productManager = new ProductManager();
    private static List<Product> productList = productManager.getAll();

    public static void action() {
        int action;
        while (true) {
            AdminMessage.choices();
            action = Validator.inputValidInt();
            switch (action) {
                case 1:
                    AdminMessage.showOrder();
                    UserMessage.back();
                    scanner.nextLine();
                    break;
                case 2:
                    ProductMessage.showList(productList);
                    UserMessage.back();
                    scanner.nextLine();
                    break;
                case 3:
                    String name = inputAllName();
                    String size;
                    switch (name) {
                        case "Pizza":
                        case "Milk Tea":
                            size = inputSize();
                            if (name.equals("Milk Tea")) {
                                productList.add(new MilkTea(size));
                                ProductData.saveProduct(new MilkTea(size));
                            } else {
                                productList.add(new Pizza(size));
                                ProductData.saveProduct(new Pizza(size));
                            }
                            break;
                        case "Sting":
                            productList.add(new Sting());
                            ProductData.saveProduct(new Sting());
                            break;
                        case "Fried Rice":
                            productList.add(new FriedRice());
                            ProductData.saveProduct(new FriedRice());
                            break;
                    }
                    break;
                case 4:
                    if (productList.isEmpty()) {
                        ProductMessage.isEmpty();
                        break;
                    }
                    if (productManager.getProductByName("Pizza", productList).isEmpty()
                            || productManager.getProductByName("Milk Tea", productList).isEmpty()) {
                        break;
                    }
                    name = inputFixName();
                    size = inputSize();
                    productManager.fixSize(name, productList, size);
                    Data.writeFile(Data.getProductFile(), productManager.toStringList(productList));
                    break;
                case 5:
                    if (productList.isEmpty()) {
                        ProductMessage.isEmpty();
                        break;
                    }
                    name = inputAllName();
                    if (!productManager.getProductByName(name, productList).isEmpty()) {
                        size = "";
                        switch (name) {
                            case "Pizza":
                            case "Milk Tea":
                                size = inputSize();
                        }
                        productManager.remove(name, productList, size);
                        Data.writeFile(Data.getProductFile(), productManager.toStringList(productList));
                    }
                    break;
                case 0:
                    return;
                default:
                    UserMessage.invalidChoice();
            }
        }
    }

    public static String inputAllName() {
        AdminMessage.chooseAllProduct();
        while (true) {
            int choice = Validator.inputValidInt();
            switch (choice) {
                case 1:
                    return "Fried Rice";
                case 2:
                    return "Milk Tea";
                case 3:
                    return "Pizza";
                case 4:
                    return "Sting";
                default:
                    UserMessage.invalidChoice();
            }
        }
    }

    public static String inputFixName() {
        AdminMessage.chooseFix();
        while (true) {
            int choice = Validator.inputValidInt();
            switch (choice) {
                case 1:
                    return "Milk Tea";
                case 2:
                    return "Pizza";
                default:
                    UserMessage.invalidChoice();
            }
        }
    }

    public static String inputSize() {
        AdminMessage.chooseSize();
        while (true) {
            int choice = Validator.inputValidInt();
            switch (choice) {
                case 1:
                    return "Small";
                case 2:
                    return "Medium";
                case 3:
                    return "Large";
                default:
                    UserMessage.invalidChoice();
            }
        }
    }
}

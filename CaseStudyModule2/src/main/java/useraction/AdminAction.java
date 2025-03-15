package useraction;

import product.ProductManager;
import data.*;
import message.*;
import product.*;
import validate.Validator;

import java.util.List;

public class AdminAction extends UserAction {
    private static final ProductManager productManager = new ProductManager();
    private static List<String> productList = Data.readFile(Data.getProductFile());

    public static void action() {
        int action;
        while (true) {
            productList = Data.readFile(Data.getProductFile());
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
                                ProductData.saveProduct(new MilkTea(size));
                            } else {
                                ProductData.saveProduct(new Pizza(size));
                            }
                            break;
                        case "Sting":
                            ProductData.saveProduct(new Sting());
                            break;
                        case "Fried Rice":
                            ProductData.saveProduct(new FriedRice());
                            break;
                    }
                    break;
                case 4:
                    if (productList.isEmpty()) {
                        ProductMessage.isEmpty();
                        break;
                    }
                    name = inputFixName();
                    if (!productManager.getProductByName(name).isEmpty()) {
                        size = inputSize();
                        productManager.fixSize(name, size);
                    }
                    break;
                case 5:
                    if (productList.isEmpty()) {
                        ProductMessage.isEmpty();
                        break;
                    }
                    name = inputAllName();
                    if (!productManager.getProductByName(name).isEmpty()) {
                        size = "";
                        switch (name) {
                            case "Pizza":
                            case "Milk Tea":
                                size = inputSize();
                        }
                        productManager.remove(name, size);
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

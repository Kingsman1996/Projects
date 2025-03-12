package user;

import data.ProductDataHandler;
import message.OrderMessage;
import message.ProductMessage;
import message.UserMessage;

import java.util.List;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
        admin = true;
    }

    @Override
    public void makeChoice() {
        int choice;
        do {
            UserMessage.showAdminChoices();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    OrderMessage.showOrderList();
                    break;
                case 2:
                    ProductMessage.showProductList();
                    break;
                case 3:
                    addProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5: // xóa
//                    System.out.println("Đang xóa sản phẩm...");
                    break;
                case 0:
                    UserMessage.exit();
                    break;
                default:
                    UserMessage.invalidChoice();
            }
        } while (choice != 0);
    }

    public void addProduct() {
        while (true) {
            ProductMessage.showProductList();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) {
                break;
            }
            String productType;
            switch (choice) {
                case 1:
                    productType = "Pizza";
                    break;
                case 2:
                    productType = "Fried Rice";
                    break;
                case 3:
                    productType = "Milk Tea";
                    break;
                case 4:
                    productType = "Sting";
                    break;
                default:
                    UserMessage.invalidChoice();
                    continue;
            }
            UserMessage.insertProductName();
            String productName = scanner.nextLine().trim();

            UserMessage.insertProductPrice();
            double price;
            while (true) {
                try {
                    price = Double.parseDouble(scanner.nextLine().trim());
                    if (price < 0) {
                        UserMessage.invalidPrice();
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    UserMessage.invalidPrice();
                }
            }

            String newProduct = productType + productName + "," + price;
            ProductDataHandler.addProduct(newProduct);
        }
    }

    public void updateProduct() {
        List<String> productList = ProductDataHandler.getAllProducts();
        if (productList.size() == 0) {
            ProductMessage.isEmpty();
            return;
        }
        ProductMessage.showProductList();
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 0) {
                    return;
                }
                if (choice < 1 || choice > productList.size()) {
                    UserMessage.invalidChoice();
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                UserMessage.invalidChoice();
            }
        }
        String selectedProduct = productList.get(choice - 1);
        String[] productData = selectedProduct.split(",");
        String productName = productData[0];
        double productPrice = Double.parseDouble(productData[1]);
        UserMessage.propertyChoice();
        int nextChoice = scanner.nextInt();
        scanner.nextLine();
        switch (nextChoice) {
            case 1:
                UserMessage.insertProductName();
                productName = scanner.nextLine().trim();
                break;
            case 2:
                UserMessage.insertProductPrice();
                while (true) {
                    try {
                        productPrice = Double.parseDouble(scanner.nextLine().trim());
                        if (productPrice < 0) {
                            UserMessage.invalidPrice();
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        UserMessage.invalidPrice();
                    }
                }
                break;
            case 0:
                UserMessage.back();
                return;
            default:
                UserMessage.invalidChoice();
                return;
        }
        String newProduct = productName + "," + productPrice;
        productList.set(choice - 1, newProduct);
        ProductDataHandler.writeFile(ProductDataHandler.getProductFile(), productList);
        ProductMessage.fixedSuccess();
    }

    private void deleteProduct() {
        List<String> products = ProductDataHandler.getAllProducts();

        if (products.isEmpty()) {
            ProductMessage.isEmpty();
            return;
        }
        UserMessage.insertProductName();
        String deleteProductName = scanner.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < products.size(); i++) {
            String foundName = products.get(i).split(",")[0];

            if (foundName.equals(deleteProductName)) {
                products.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            ProductDataHandler.writeFile(ProductDataHandler.getProductFile(), products);
        } else {
            ProductMessage.notFound();
        }
    }

}

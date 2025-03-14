package actors.user;

import actors.product.ProductManager;
import data.ProductData;
import message.OrderMessage;
import message.ProductMessage;
import message.UserMessage;
import validate.Validator;

public class AdminAction extends UserAction {
    private static ProductManager productManager = new ProductManager();

    public static void handle() {
        int action;

        while (true) {
            UserMessage.adminChoices();
            action = Validator.inputValidInt();

            if (action == 0) {
                break;
            }

            switch (action) {
                case 1:
                    OrderMessage.show();
                    break;
                case 2:
                    ProductMessage.showList();
                    break;
                case 3:
                    String productName = inputProductName();
                    if (productName == null) {
                        break;
                    }
                    String productPrice = inputProductPrice();
                    String productSize = "";

                    if (productName.equals("Pizza") || productName.equals("Milk Tea")) {
                        productSize = inputProductSize();
                        if (productSize == null || productSize.equals("")) {
                            productSize = "Medium";
                        }
                    }
                    StringBuilder productData = new StringBuilder(productName + ", " + productPrice);
                    if (!productSize.isEmpty()) {
                        productData.append(", ").append(productSize);
                    }
                    ProductData.appendToFile(ProductData.getProductFile(), productData.toString());
                    ProductMessage.added(productName);
                    break;
                case 4:
                    while (true) {
                        String searchName = inputProductName();
                        if (searchName == null) {
                            break;
                        }
                        String searchPrice = inputProductPrice();
                        if (searchPrice == null) {
                            break;
                        }
                        productManager.fixPrice(searchName, searchPrice);
                        ProductMessage.fixed();
                        break;
                    }
                    break;
                case 5:
                    while (true) {
                        String searchName = inputProductName();
                        if (searchName == null) {
                            break;
                        }
                        productManager.remove(searchName);
                        break;
                    }
                    break;
                default:
                    UserMessage.invalidChoice();
            }
        }
    }
}

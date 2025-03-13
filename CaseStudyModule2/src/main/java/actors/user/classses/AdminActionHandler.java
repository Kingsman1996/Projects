package actors.user.classses;

import data.ProductDataHandler;
import message.OrderMessage;
import message.ProductMessage;
import message.UserMessage;

public class AdminActionHandler extends UserActionHandler {
    public static void handleFirstAction(int action) {
        while (action != 0) {
            switch (action) {
                case 1:
                    OrderMessage.showOrder();
                    scanner.nextLine();
                    break;
                case 2:
                    ProductMessage.showProductList();
                    scanner.nextLine();
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
                        if (productSize == null) {
                            break;
                        }
                    }
                    String hasIce = "";
                    if (productName.equals("Milk Tea")) {
                        hasIce = inputProductIce();
                        if (hasIce == null) {
                            break;
                        }
                    }
                    StringBuilder productData = new StringBuilder(productName + ", " + productPrice);
                    if (!productSize.isEmpty()) {
                        productData.append(", ").append(productSize);
                    }
                    if (!hasIce.isEmpty()) {
                        productData.append(", ").append(hasIce);
                    }
                    ProductDataHandler.appendToFile(ProductDataHandler.getProductFile(), productData.toString());
                    ProductMessage.addedDone(productName);
                    break;
                case 4:
                    while (true) {
                        String searchName = inputProductName();
                        if (searchName == null) {
                            return;
                        }

                        String searchPrice = inputProductPrice();
                        String currentSize = "";
                        hasIce = "";
                        if (searchName.equals("Pizza") || searchName.equals("Milk Tea")) {
                            currentSize = inputProductSize();
                            if (currentSize == null) {
                                return;
                            }
                        }
                        if (searchName.equals("Milk Tea")) {
                            hasIce = inputProductIce();
                            if (hasIce == null) {
                                return;
                            }
                        }
                        int productIndex = findProductIndex(searchName, searchPrice, currentSize, hasIce);
                        if (productIndex != -1) {
                            String[] productInfo = productList.get(productIndex).split(", ");

                            String newPrice = inputProductPrice();
                            productInfo[1] = newPrice;

                            StringBuilder updatedProduct = new StringBuilder(productInfo[0] + ", " + productInfo[1]);
                            for (int j = 2; j < productInfo.length; j++) {
                                updatedProduct.append(", ").append(productInfo[j]);
                            }

                            productList.set(productIndex, updatedProduct.toString());
                            ProductDataHandler.writeFile(ProductDataHandler.getProductFile(), productList);
                            ProductMessage.fixedSuccess();
                            return;
                        } else {
                            ProductMessage.notFound();
                        }
                    }
                case 5:
                    while (true) {
                        String searchName = inputProductName();
                        if (searchName == null) {
                            return;
                        }

                        String searchPrice = inputProductPrice();
                        String currentSize = "";
                        if (searchName.equals("Pizza") || searchName.equals("Milk Tea")) {
                            currentSize = inputProductSize();
                            if (currentSize == null) {
                                return;
                            }
                        }
                        hasIce = "";
                        if (searchName.equals("Milk Tea")) {
                            hasIce = inputProductIce();
                            if (hasIce == null) {
                                return;
                            }
                        }

                        int productIndex = findProductIndex(searchName, searchPrice, currentSize, hasIce);

                        if (productIndex != -1) {
                            UserMessage.confirmDelete();
                            int confirm = scanner.nextInt();
                            scanner.nextLine();
                            if (confirm == 1) {
                                productList.remove(productIndex);
                                ProductMessage.deletedSuccess();
                                ProductDataHandler.writeFile(ProductDataHandler.getProductFile(), productList);
                            }
                            return;
                        } else {
                            ProductMessage.notFound();
                        }
                    }
                case 0:
                    break;
                default:
                    UserMessage.invalidChoice();
            }
            UserMessage.adminChoices();
            action = scanner.nextInt();
            scanner.nextLine();
        }
    }
}

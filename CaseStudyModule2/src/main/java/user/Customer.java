package user;

import message.UserMessage;
import order.Order;

public class Customer extends User {
    private Order order = new Order();

    public Customer(String username, String password) {
        super(username, password);
    }

    @Override
    public void makeChoice() {
        int choice;
        do {
            UserMessage.showCustomerChoices();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    UserMessage.showProductList();
                    break;
                case 2: //thanh toán
//                    System.out.println(" Thanh toán đơn hàng...");
                    break;
                case 0:
                    UserMessage.exit();
                    break;
                default:
                    UserMessage.invalidChoice();
            }
        } while (choice != 0);
    }
}

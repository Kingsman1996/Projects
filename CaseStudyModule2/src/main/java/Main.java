import actors.order.Order;
import actors.order.OrderManager;
import actors.order.OrderProcessor;
import actors.product.classes.FriedRice;
import actors.product.classes.MilkTea;
import actors.product.classes.Pizza;
import actors.product.classes.Sting;
import actors.user.classses.Customer;
import actors.user.classses.CustomerActionHandler;
import data.OrderDataHandler;
import message.OrderMessage;
import message.UserMessage;
import actors.user.classses.Admin;
import actors.user.classses.UserActionHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Admin admin = new Admin("admin1", "password");
//        Customer customer = new Customer("customer1", "password");
//        Scanner scanner = new Scanner(System.in);
//        int choice;
//        UserMessage.customerChoices();
//        choice = scanner.nextInt();
//        scanner.nextLine();
//        CustomerActionHandler.handleAction(choice);

        Order order = new Order();
        order.add(new Pizza(50000));
        order.add(new FriedRice(40000));
//        OrderDataHandler.saveOrder(order);
        Order order2 = new Order();
        order2.add(new Sting(20000));
        order2.add(new MilkTea(35000));
//        OrderDataHandler.saveOrder(order2);
        OrderManager orderManager = new OrderManager();
        orderManager.addOrder(order);
        orderManager.addOrder(order2);
//        System.out.println(orderManager.getAllOrders());

        OrderProcessor orderProcessor = new OrderProcessor(orderManager);
//        orderProcessor.processOne(1);
        orderProcessor.processAll();
    }
}

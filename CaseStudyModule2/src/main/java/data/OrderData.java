package data;

import actors.order.Order;
import actors.order.OrderManager;

public class OrderData extends Data {
    public static void saveOrder(Order order) {
        appendToFile(ORDER_FILE, order.toString());
    }
    public static void update(OrderManager orderManager) {
        writeFile(ORDER_FILE,orderManager.toStringList());
    }
}

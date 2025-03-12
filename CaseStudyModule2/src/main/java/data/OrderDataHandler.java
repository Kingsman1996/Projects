package data;

import java.util.Iterator;
import java.util.List;

public class OrderDataHandler extends DataHandler {
    private static final String ORDER_FILE = "src/main/resources/orders.txt";

    public static List<String> getAllOrders() {
        return readFile(ORDER_FILE);
    }

    public static void addOrder(String orderLine) {
        List<String> orders = getAllOrders();
        orders.add(orderLine);
        writeFile(ORDER_FILE, orders);
    }

    public static void deleteOrder(String orderId) {
        List<String> orders = getAllOrders();
        Iterator<String> iterator = orders.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.startsWith(orderId + ",")) {
                iterator.remove();
            }
        }
        writeFile(ORDER_FILE, orders);
    }
}

package data;

import actors.order.Order;

import java.util.Iterator;
import java.util.List;

public class OrderDataHandler extends DataHandler {
    private static final String ORDER_FILE = "src/main/resources/orders.txt";
    private static final String CART_FILE = "src/main/resources/cart.txt";

    public static List<String> getAllOrders() {
        return readFile(ORDER_FILE);
    }

    public static void saveOrder(Order order) {
        appendToFile(ORDER_FILE, order.toString());
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
    public static String getOrderFile(){
        return ORDER_FILE;
    }
    public static String getCartFile(){
        return CART_FILE;
    }
}

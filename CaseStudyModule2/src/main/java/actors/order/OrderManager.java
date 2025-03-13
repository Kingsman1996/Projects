package actors.order;

import java.util.*;

public class OrderManager {
    private Map<Integer, Order> orders;
    private int orderId;

    public OrderManager() {
        this.orders = new HashMap<>();
        this.orderId = 1;
    }

    public void addOrder(Order order) {
        orders.put(orderId, order);
        orderId++;
    }

    public void removeOrder(int id) {
        orders.remove(id);
    }

    public Map<Integer, Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(int id) {
        Order order = null;
        for (Integer key : orders.keySet()) {
            if (key.intValue() == id) {
                order = orders.get(key);
                break;
            }
        }
        if (order == null) {
            throw new NoSuchElementException("Không có đơn hàng với ID: " + id);
        }
        return order;
    }

    public List<Integer> getAllOrderIds() {
        return new ArrayList<>(orders.keySet());
    }

    @Override
    public String toString() {
        return orders.toString();
    }
}

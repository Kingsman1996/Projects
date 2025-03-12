package order;

import java.util.*;

public class OrderManager {
    private Map<Integer, Order> orders;

    public OrderManager() {
        this.orders = new HashMap<>();
    }

    public void addOrder(int id, Order order) {
        orders.put(id, order);
    }

    public void removeOrder(int id) {
        orders.remove(id);
    }

    public Map<Integer, Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(int id) {
        Order order = orders.get(id);
        if (order == null) {
            throw new NoSuchElementException("Không có đơn hàng với ID: " + id);
        }
        return order;
    }
    public List<Integer> getAllOrderIds() {
        return new ArrayList<>(orders.keySet());
    }
}

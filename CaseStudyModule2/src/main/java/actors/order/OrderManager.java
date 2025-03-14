package actors.order;

import data.OrderData;
import message.OrderMessage;

import java.util.*;

public class OrderManager {
    private Map<Integer, Order> orders;
    private int orderId;

    public OrderManager() {
        this.orders = new HashMap<>();
        this.orderId = 1;
    }

    public void add(Order order) {
        orders.put(orderId, order);
        OrderData.saveOrder(order);
        OrderMessage.added(orderId);
        orderId++;
    }

    public void remove(int id) {
        if (orders.containsKey(id)) {
            orders.remove(id);
            OrderMessage.removed(id);
            OrderData.update(this);
        }
    }

    public Map<Integer, Order> getAll() {
        return orders;
    }

    public Order getOrder(int id) {
        Order order = null;
        for (Integer key : orders.keySet()) {
            if (key.intValue() == id) {
                order = orders.get(key);
                break;
            }
        }
        if (order == null) {
            OrderMessage.notFound(id);
        }
        return order;
    }

    public List<String> toStringList() {
        List<String> list = new ArrayList<>();
        for (Order order : orders.values()) {
            list.add(order.toString());
        }
        return list;
    }

    public List<Integer> getAllId() {
        return new ArrayList<>(orders.keySet());
    }

    @Override
    public String toString() {
        return orders.toString();
    }
}

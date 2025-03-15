package user;

import order.Order;

public class Customer extends User {
    private Order order;

    public Customer(String username, String password) {
        super(username, password);
        this.order = new Order();
    }

    public Order getOrder() {
        return order;
    }

    public void placeOrder(Order order) {
        this.order = order;
    }
}

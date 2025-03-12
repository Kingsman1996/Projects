package order;

import message.OrderMessage;
import product.classes.Product;
import thread.ProductPreparation;
import thread.ThreadManager;

import java.util.*;

public class OrderProcessor {
    private OrderManager orderManager;
    private ThreadManager threadManager;

    public OrderProcessor() {
        this.threadManager = new ThreadManager();
        this.orderManager = new OrderManager();
    }

    public void processOne(int id) {
        Order order = orderManager.getOrderById(id);
        OrderMessage.alertOrderStart(id);
        List<Runnable> productTasks = new ArrayList<>();
        for (Product product : order.getProducts()) {
            productTasks.add(new ProductPreparation(product));
        }
        ThreadManager threadManager = new ThreadManager();
        threadManager.startThreads(productTasks);
        threadManager.waitThreads();
        OrderMessage.alertOrderEnd(id);
        orderManager.removeOrder(id);
    }

    public void processAll() {
        List<Integer> ids = orderManager.getAllOrderIds();
        for (Integer id : ids) {
            processOne(id);
        }
    }
}

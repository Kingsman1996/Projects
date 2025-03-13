package actors.order;

import message.OrderMessage;
import actors.product.classes.Product;
import thread.ProductPreparation;
import thread.ThreadManager;

import java.util.*;

public class OrderProcessor {
    private OrderManager orderManager;
    private ThreadManager threadManager;

    public OrderProcessor(OrderManager orderManager) {
        this.threadManager = new ThreadManager();
        this.orderManager = orderManager;
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

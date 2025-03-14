package actors.order;

import message.OrderMessage;
import actors.product.classes.Product;
import message.UserMessage;
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

    public void processCart(int id, List<Product> cart) {
        if (cart.isEmpty()) {
            UserMessage.emptyCart();
            return;
        }
        OrderMessage.alertStart(id);
        List<Runnable> productTasks = new ArrayList<>();
        for (Product product : cart) {
            productTasks.add(new ProductPreparation(product));
        }
        ThreadManager threadManager = new ThreadManager();
        threadManager.startThreads(productTasks);
        threadManager.waitThreads();
        OrderMessage.alertEnd(id);
    }
}

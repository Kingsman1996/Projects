package order;

import message.CustomerMessage;
import product.Product;
import thread.ProductPreparation;
import thread.ThreadManager;

import java.util.*;

public class OrderProcessor {
    public static void process(List<Product> cart) {
        if (cart.isEmpty()) {
            CustomerMessage.emptyCart();
            return;
        }
        List<Runnable> productTasks = new ArrayList<>();
        for (Product product : cart) {
            productTasks.add(new ProductPreparation(product));
        }
        ThreadManager threadManager = new ThreadManager();
        threadManager.startThreads(productTasks);
        threadManager.waitThreads();
    }
}

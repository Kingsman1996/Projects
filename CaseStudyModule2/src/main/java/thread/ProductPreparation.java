package thread;

import message.OrderMessage;
import actors.product.classes.Product;
import actors.product.interfaces.Preparable;

public class ProductPreparation implements Runnable {
    private Product product;

    public ProductPreparation(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        OrderMessage.alertPreparingProduct(product);
        if (product instanceof Preparable) {
            try {
                Preparable item = (Preparable) product;
                Thread.sleep(item.getPrepareTime());
                OrderMessage.alertReadyProduct(product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            OrderMessage.alertReadyProduct(product);
        }
    }
}

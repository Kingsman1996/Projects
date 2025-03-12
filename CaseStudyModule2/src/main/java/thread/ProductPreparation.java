package thread;

import message.OrderMessage;
import product.classes.Product;
import product.interfaces.Preparable;

public class ProductPreparation implements Runnable {
    private Product product;

    public ProductPreparation(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        if (product instanceof Preparable) {
            OrderMessage.alertPreparingProduct(product);
            try {
                Preparable item = (Preparable) product;
                Thread.sleep(item.getPrepareTime());
                OrderMessage.alertReadyProduct(product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            OrderMessage.alertPreparingProduct(product);
        }
    }
}

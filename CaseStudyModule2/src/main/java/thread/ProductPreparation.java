package thread;

import actors.product.classes.Product;
import actors.product.interfaces.Preparable;
import message.ProductMessage;

public class ProductPreparation implements Runnable {
    private Product product;

    public ProductPreparation(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        ProductMessage.preparing(product);
        if (product instanceof Preparable) {
            try {
                Preparable item = (Preparable) product;
                Thread.sleep(item.getPrepareTime());
                ProductMessage.ready(product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            ProductMessage.ready(product);
        }
    }
}

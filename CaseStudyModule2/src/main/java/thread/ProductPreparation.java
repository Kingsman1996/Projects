package thread;

import product.Product;
import product.Preparable;
import message.ProductMessage;

public class ProductPreparation implements Runnable {
    private Product product;

    public ProductPreparation(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
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

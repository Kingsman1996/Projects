package actors.product.classes;

import actors.product.interfaces.Discountable;

public class Sting extends Drink implements Discountable {
    private int discountPercent = 5;

    public Sting() {
        this.name = "Sting";
        this.price = 15000;
    }

    public Sting(String name) {
        super(name);
    }

    public Sting(int price) {
        super(price);
    }

    public Sting(String name, int price) {
        super(name, price);
    }

    @Override
    public int getRealPrice() {
        return (100 - discountPercent) / 100 * getPrice();
    }
}

package actors.product.classes;

import actors.product.interfaces.Discountable;

public class Sting extends Drink implements Discountable {
    private int discountPercent;

    public Sting(int price) {
        super(price);
        this.name = "Sting";
        this.discountPercent = 5;
    }

    public Sting(int price, int discountPercent) {
        super(price);
        this.name = "Sting";
        this.discountPercent = discountPercent;
    }

    @Override
    public int getRealPrice() {
        return (100 - discountPercent) / 100 * getPrice();
    }
}

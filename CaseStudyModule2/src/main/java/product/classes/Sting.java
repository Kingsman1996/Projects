package product.classes;

import product.interfaces.Discountable;

public class Sting extends Drink implements Discountable {
    private double discountPercent;


    public Sting(String name, double price) {
        super(name, price);
        this.discountPercent = 5;
    }

    public Sting(String name, double price, double discountPercent) {
        super(name, price);
        this.discountPercent = discountPercent;
    }

    @Override
    public double getRealPrice() {
        return (100 - discountPercent) / 100 * getPrice();
    }
}

package product.classes;

public abstract class Drink extends Product {
    protected boolean cold = true;

    public Drink(String name, double price) {
        super(name, price);
    }

    public boolean isCold() {
        return cold;
    }

    public void setCold(boolean cold) {
        this.cold = cold;
    }
}

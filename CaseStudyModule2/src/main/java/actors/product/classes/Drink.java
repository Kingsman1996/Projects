package actors.product.classes;

public abstract class Drink extends Product {
    protected boolean cold = true;

    public Drink(int price) {
        super(price);
    }

    public Drink(String name, int price) {
        super(name, price);
    }

    public boolean isCold() {
        return cold;
    }

    public void setCold(boolean cold) {
        this.cold = cold;
    }
}

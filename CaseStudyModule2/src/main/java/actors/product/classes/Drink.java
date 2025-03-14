package actors.product.classes;

public abstract class Drink extends Product {
    public Drink() {
    }

    public Drink(String name) {
        super(name);
    }

    public Drink(int price) {
        super(price);
    }

    public Drink(String name, int price) {
        super(name, price);
    }
}

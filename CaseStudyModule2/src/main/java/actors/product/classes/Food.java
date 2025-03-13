package actors.product.classes;

public abstract class Food extends Product {
    public Food() {
    }

    public Food(int price) {
        super(price);
    }

    public Food(String name) {
        super(name);
    }

    public Food(String name, int price) {
        super(name, price);
    }
}

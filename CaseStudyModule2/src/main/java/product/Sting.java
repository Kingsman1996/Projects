package product;

public class Sting extends Product {
    public Sting() {
        this.name = "Sting";
        this.price = 15000;
    }

    public Sting(String name) {
        super(name);
        this.price = 15000;
    }

    public Sting(int price) {
        super(price);
        this.name = "Sting";
    }

    public Sting(String name, int price) {
        super(name, price);
    }
}

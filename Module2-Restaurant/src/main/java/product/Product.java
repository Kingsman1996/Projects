package product;

public abstract class Product {
    protected String name;
    protected int price;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(int price) {
        this.price = price;
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ", " + price;
    }
}
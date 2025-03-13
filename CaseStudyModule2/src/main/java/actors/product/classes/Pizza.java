package actors.product.classes;

import actors.product.interfaces.Sizeable;

public class Pizza extends Food implements Sizeable {
    private String size = "Medium";

    public Pizza() {
        this.name = "Pizza";
        this.price = 80000;
    }

    public Pizza(String size) {
        this.name = "Pizza";
        this.price = 80000;
        if (size.equals("Small") || size.equals("Large")) {
            this.size = size;
        }
    }

    public Pizza(String name, int price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + size;
    }
}

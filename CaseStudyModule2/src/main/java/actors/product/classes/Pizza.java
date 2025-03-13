package actors.product.classes;

import actors.product.interfaces.Sizeable;

public class Pizza extends Food implements Sizeable {
    private String size;

    public Pizza(int price) {
        super(price);
        this.name = "Pizza";
        this.size = "Medium";
    }

    public Pizza(int price, String size) {
        super(price);
        this.name = "Pizza";
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
        return super.toString() + ", size: " + size;
    }
}

package product.classes;

import product.interfaces.Sizeable;

public class Pizza extends Food implements Sizeable {
    private String size;

    public Pizza(String name, double price) {
        super(name, price);
        this.size = "Medium";
    }

    public Pizza(String name, double price, String size) {
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
        return super.toString() + ", " + (hot ? "nóng" : "lạnh") + ", size: " + size;
    }
}

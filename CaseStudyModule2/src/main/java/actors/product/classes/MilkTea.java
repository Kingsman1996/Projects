package actors.product.classes;

import actors.product.interfaces.Preparable;
import actors.product.interfaces.Sizeable;

public class MilkTea extends Drink implements Sizeable, Preparable {
    private String size = "Medium";
    private int prepareTime = 5000;

    public MilkTea() {
        this.name = "Milk Tea";
        this.price = 40000;
    }

    public MilkTea(String size) {
        this.name = "Milk Tea";
        this.price = 40000;
        if (size.equals("Small") || size.equals("Large")) {
            this.size = size;
        }
    }

    public MilkTea(String name, int price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public int getPrepareTime() {
        return prepareTime;
    }

    @Override
    public void setPrepareTime(int prepareTime) {
        this.prepareTime = prepareTime;
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

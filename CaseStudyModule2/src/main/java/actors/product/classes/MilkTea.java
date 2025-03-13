package actors.product.classes;

import actors.product.interfaces.Preparable;
import actors.product.interfaces.Sizeable;

public class MilkTea extends Drink implements Sizeable, Preparable {
    private String size;
    private int prepareTime;


    public MilkTea(int price) {
        super(price);
        this.name = "Milk Tea";
        this.size = "Medium";
        this.prepareTime = 5000;
    }

    public MilkTea(int price, String size, int prepareTime) {
        super(price);
        this.name = "Milk Tea";
        this.size = size;
        this.prepareTime = prepareTime;
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
        return super.toString() + ", " + (cold ? "with ice" : "no ice") + ", size: " + size;
    }
}

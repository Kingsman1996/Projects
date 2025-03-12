package product.classes;

import product.interfaces.Preparable;
import product.interfaces.Sizeable;

public class MilkTea extends Drink implements Sizeable, Preparable {
    private String size;
    private int prepareTime;


    public MilkTea(String name, double price) {
        super(name, price);
        this.size = "Medium";
        this.prepareTime = 5000;
    }

    public MilkTea(String name, double price, String size, int prepareTime) {
        super(name, price);
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
        return super.toString() + ", " + (cold ? "thêm đá" : "không đá") + ", size: " + size;
    }
}

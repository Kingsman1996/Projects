package product.classes;

import product.interfaces.Preparable;

public class FriedRice extends Food implements Preparable {
    private int prepareTime;

    public FriedRice(String name, double price) {
        super(name, price);
        this.prepareTime = 8000;
    }

    public FriedRice(String name, double price, int prepareTime) {
        super(name, price);
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
}

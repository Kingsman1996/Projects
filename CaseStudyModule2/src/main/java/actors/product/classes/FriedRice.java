package actors.product.classes;

import actors.product.interfaces.Preparable;

public class FriedRice extends Food implements Preparable {
    private int prepareTime = 8000;

    public FriedRice() {
        this.name = "Fried Rice";
        this.price = 35000;
    }

    public FriedRice(String name) {
        super(name);
    }

    public FriedRice(int price) {
        super(price);
    }

    public FriedRice(String name, int price) {
        super(name, price);
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

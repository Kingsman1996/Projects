package actors.product.classes;

import actors.product.interfaces.Preparable;

public class FriedRice extends Food implements Preparable {
    private int prepareTime;

    public FriedRice(int price) {
        super(price);
        this.name = "Fried Rice";
        this.prepareTime = 8000;
    }

    public FriedRice(int price, int prepareTime) {
        super(price);
        this.name = "Fried Rice";
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

package product;

public class FriedRice extends Product implements Preparable {
    private int prepareTime = 8000;

    public FriedRice() {
        super("Fried Rice");
        this.price = 35000;
    }

    public FriedRice(String name) {
        super(name);
        this.price = 35000;
    }

    public FriedRice(int price) {
        super(price);
        this.name = "Fried Rice";
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

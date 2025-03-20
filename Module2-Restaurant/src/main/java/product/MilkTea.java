package product;

public class MilkTea extends Product implements Sizeable, Preparable {
    private String size = "Medium";
    private int prepareTime = 5000;

    public MilkTea() {
        super("Milk Tea");
        this.price = 45000;
    }

    public MilkTea(String size) {
        super("Milk Tea");
        updatePriceBySize(size);
    }

    public MilkTea(String name, String size) {
        super(name);
        updatePriceBySize(size);
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
        updatePriceBySize(size);
    }

    public void updatePriceBySize(String size) {
        this.size = size;
        switch (size) {
            case "Small":
                this.price = 35000;
                break;
            case "Large":
                this.price = 55000;
                break;
            default:
                this.price = 45000;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", " + size;
    }
}

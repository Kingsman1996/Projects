package product;

public class Pizza extends Product implements Sizeable {
    private String size = "Medium";

    public Pizza() {
        super("Pizza");
        this.price = 75000;
    }

    public Pizza(String size) {
        super("Pizza");
        updatePriceBySize(size);
    }

    public Pizza(String name, String size) {
        super(name);
        updatePriceBySize(size);
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
                this.price = 65000;
                break;
            case "Large":
                this.price = 85000;
                break;
            default:
                this.price = 75000;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", " + size;
    }
}

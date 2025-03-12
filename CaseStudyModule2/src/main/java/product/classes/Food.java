package product.classes;

public abstract class Food extends Product {
    protected boolean hot = true;

    public Food(String name, double price) {
        super(name, price);
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }
}

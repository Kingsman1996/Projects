import java.time.LocalDate;

public class Meat extends Material implements Discountable {
    private double weight;

    public Meat() {
    }

    public Meat(String id, String name, int cost, LocalDate manufacturingDate, double weight) {
        super(id, name, cost, manufacturingDate);
        this.weight = weight;
    }

    @Override
    public double getBasicMoney() {
        return weight * getCost();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusDays(7);
    }

    @Override
    public double getRealMoney() {
        return getBasicMoney() * 90 / 100;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Weight: " + weight
                + ", Expire Date: " + getExpiryDate()
                + "\n";
    }
}

import java.time.LocalDate;

public class CrispyFlour extends Material implements Discountable {
    private int quantity;

    public CrispyFlour() {
    }

    public CrispyFlour(String id, String name, int cost, LocalDate manufacturingDate, int quantity) {
        super(id, name, cost, manufacturingDate);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getBasicMoney() {
        return quantity * getCost();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusYears(1);
    }

    @Override
    public double getRealMoney() {
        return getBasicMoney() * 95 / 100;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Quantity: " + quantity
                + ", Expire Date: " + getExpiryDate()
                + "\n";
    }
}

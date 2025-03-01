import java.time.LocalDate;

public abstract class Material implements Comparable<Material> {
    private String id;
    private String name;
    private int cost;
    private LocalDate manufacturingDate;

    public Material() {
    }

    public Material(String id, String name, int cost, LocalDate manufacturingDate) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.manufacturingDate = manufacturingDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public abstract double getBasicMoney();

    public abstract LocalDate getExpiryDate();

    @Override
    public int compareTo(Material o) {
        return this.cost - o.cost;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", Name: " + name
                + ", Cost: " + cost
                + ", ManufacturingDate: " + manufacturingDate;
    }
}

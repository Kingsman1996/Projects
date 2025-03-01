public class FullTimeWorker extends Worker {
    private double bonusMoney;
    private double fineMoney;
    private double hardSalary;

    public FullTimeWorker() {
    }

    public FullTimeWorker(int id, String name, int age, String phoneNumber, String mailAddress) {
        super(id, name, age, phoneNumber, mailAddress);
    }

    public FullTimeWorker(int id, String name, int age, String phoneNumber, String mailAddress,
                          double bonusMoney, double fineMoney, double hardSalary) {
        super(id, name, age, phoneNumber, mailAddress);
        this.bonusMoney = bonusMoney;
        this.fineMoney = fineMoney;
        this.hardSalary = hardSalary;
    }

    public double getBonusMoney() {
        return bonusMoney;
    }

    public void setBonusMoney(double bonusMoney) {
        this.bonusMoney = bonusMoney;
    }

    public double getFineMoney() {
        return fineMoney;
    }

    public void setFineMoney(double fineMoney) {
        this.fineMoney = fineMoney;
    }

    public double getHardSalary() {
        return hardSalary;
    }

    public void setHardSalary(double hardSalary) {
        this.hardSalary = hardSalary;
    }

    @Override
    public double getRealSalary() {
        return hardSalary + bonusMoney - fineMoney;
    }

    @Override
    public String toString() {
        return "Nhân viên Full-Time "
                + super.toString()
                + ", Thực lĩnh: " + getRealSalary();
    }
}

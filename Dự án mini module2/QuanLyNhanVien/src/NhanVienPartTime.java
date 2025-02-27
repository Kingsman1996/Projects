public class NhanVienPartTime extends NhanVien {
    private int workingHours;

    public NhanVienPartTime() {
    }

    public NhanVienPartTime(int id, String name, int age, String phoneNumber, String mailAddress) {
        super(id, name, age, phoneNumber, mailAddress);
    }

    public NhanVienPartTime(int id, String name, int age, String phoneNumber, String mailAddress, int workingHours) {
        super(id, name, age, phoneNumber, mailAddress);
        this.workingHours = workingHours;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public double getRealSalary() {
        return workingHours * 100000;
    }

    @Override
    public String toString() {
        return "Nhân viên Part-Time "
                + super.toString()
                + ", Thực lĩnh: " + getRealSalary();
    }
}

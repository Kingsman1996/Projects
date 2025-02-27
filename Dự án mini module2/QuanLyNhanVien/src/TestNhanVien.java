public class TestNhanVien {
    public static void main(String[] args) {
        System.out.println("-----Quản lý nhân viên-----");
        QuanLyNhanVien danhSach = new QuanLyNhanVien();
        danhSach.addWorker(new NhanVienFullTime(1, "Nguyễn Văn A", 20, "012345678", "nva@gmail.com", 111111, 222222, 7777777));
        danhSach.addWorker(new NhanVienPartTime(2, "Nguyễn Văn B", 22, "012931278", "nvb@gmail.com", 24));
        danhSach.addWorker(new NhanVienPartTime(4, "Nguyễn Văn C", 27, "999999999", "nvc@gmail.com", 48));
        danhSach.addWorker(new NhanVienFullTime(3, "Nguyễn Văn A", 30, "987654321", "nva3@gmail.com", 333333, 444444, 5555555));
        danhSach.addWorker(new NhanVienPartTime(5, "Nguyễn Văn D", 37, "888888888", "nvd@gmail.com", 72));
        System.out.println("Danh sách nhân viên: ");
        System.out.println(danhSach.toString());
        System.out.println("Tìm kiếm nhân viên Nguyễn Văn A ");
        for (NhanVien each : danhSach.getWorkersByName("Nguyễn Văn A")) {
            System.out.println(each.toString());
        }
        System.out.println("Lương trung bình toàn bộ nhân viên: " + danhSach.getAverageSalary());
        System.out.println();
        System.out.println("Danh sách nhân viên Full-Time lương thấp hơn trung bình: ");
        System.out.println(danhSach.getLowSalaryFullTime());
        System.out.println();
        System.out.println("Tổng lương nhân viên Part-Time: ");
        System.out.println(danhSach.sumPartTimeRealSaraly());
        System.out.println();
        System.out.println("Danh sách nhân viên Full-Time theo lương tăng dần: ");
        for (NhanVien each : danhSach.getAllFullTimeSortedSalary()) {
            System.out.println(each.toString());
        }
    }
}

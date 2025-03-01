public class Main {
    public static void main(String[] args) {
        System.out.println("-----Quản lý nhân viên-----");
        WorkerManaging workerList = new WorkerManaging();
        workerList.addWorker(new FullTimeWorker(1, "Nguyễn Văn A", 20, "012345678", "nva@gmail.com", 111111, 222222, 7777777));
        workerList.addWorker(new PartTimeWorker(2, "Nguyễn Văn B", 22, "012931278", "nvb@gmail.com", 24));
        workerList.addWorker(new PartTimeWorker(4, "Nguyễn Văn C", 27, "999999999", "nvc@gmail.com", 48));
        workerList.addWorker(new FullTimeWorker(3, "Nguyễn Văn A", 30, "987654321", "nva3@gmail.com", 333333, 444444, 5555555));
        workerList.addWorker(new PartTimeWorker(5, "Nguyễn Văn D", 37, "888888888", "nvd@gmail.com", 72));
        System.out.println("Danh sách nhân viên: ");
        System.out.println(workerList.toString());
        System.out.println("Tìm kiếm nhân viên Nguyễn Văn A ");
        for (Worker each : workerList.getWorkersByName("Nguyễn Văn A")) {
            System.out.println(each.toString());
        }
        System.out.println("Lương trung bình toàn bộ nhân viên: " + workerList.getAverageSalary());
        System.out.println();
        System.out.println("Danh sách nhân viên Full-Time lương thấp hơn trung bình: ");
        System.out.println(workerList.getLowSalaryFullTime());
        System.out.println();
        System.out.println("Tổng lương nhân viên Part-Time: ");
        System.out.println(workerList.sumPartTimeRealSaraly());
        System.out.println();
        System.out.println("Danh sách nhân viên Full-Time theo lương tăng dần: ");
        for (Worker each : workerList.getAllFullTimeSortedSalary()) {
            System.out.println(each.toString());
        }
    }
}

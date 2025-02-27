import java.util.ArrayList;
import java.util.Collections;

public class QuanLyNhanVien {
    private ArrayList<NhanVien> workerList;
    private int workerAmount;

    public QuanLyNhanVien() {
        workerAmount = 0;
        workerList = new ArrayList<>();
    }

    public void addWorker(NhanVien newNhanVien) {
        workerList.add(newNhanVien);
        workerAmount++;
    }

    public void removeWorker(NhanVien nhanVien) {
        if (workerList.contains(nhanVien)) {
            workerList.remove(nhanVien);
            workerAmount--;
        }
    }

    public int getWorkerAmount() {
        return workerAmount;
    }

    public NhanVien getWorkerByIndex(int index) {
        return workerList.get(index);
    }

    public ArrayList<NhanVien> getWorkersByName(String name) {
        ArrayList<NhanVien> tempList = new ArrayList<>();
        for (NhanVien each : workerList) {
            if (each.getName().equals(name)) {
                tempList.add(each);
            }
        }
        return tempList;
    }

    public double getAverageSalary() {
        double sum = 0;
        for (NhanVien each : workerList) {
            sum += each.getRealSalary();
        }
        return sum / workerAmount;
    }

    public boolean isLowSalaryFullTime(NhanVien nhanVien, double averageSalary) {
        if (isFullTime(nhanVien)) {
            return nhanVien.getRealSalary() < averageSalary;
        }
        return false;
    }

    public ArrayList<NhanVien> getLowSalaryFullTime() {
        ArrayList<NhanVien> tempList = new ArrayList<>();
        for (NhanVien each : workerList) {
            if (isLowSalaryFullTime(each, getAverageSalary())) {
                tempList.add(each);
            }
        }
        return tempList;
    }

    public boolean isFullTime(NhanVien nhanVien) {
        return nhanVien instanceof NhanVienFullTime;
    }

    public boolean isPartTime(NhanVien nhanVien) {
        return nhanVien instanceof NhanVienPartTime;
    }

    public double sumPartTimeRealSaraly() {
        double sum = 0;
        for (NhanVien each : workerList) {
            if (isPartTime(each)) {
                sum += each.getRealSalary();
            }
        }
        return sum;
    }

    public ArrayList<NhanVien> getAllFullTimeSortedSalary() {
        ArrayList<NhanVien> tempList = new ArrayList<>();
        for (NhanVien each : workerList) {
            if (isFullTime(each)) {
                tempList.add(each);
            }
        }
        Collections.sort(tempList);
        return tempList;
    }

    @Override
    public String toString() {
        if (workerAmount == 0) {
            return "Danh sách trống";
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < workerAmount; i++) {
                result.append(i + 1).append(": ").append(workerList.get(i).toString()).append("\n");
            }
            return result.toString();
        }
    }
}

import java.util.ArrayList;
import java.util.Collections;

public class WorkerManaging {
    private ArrayList<Worker> workerList;
    private int workerAmount;

    public WorkerManaging() {
        workerAmount = 0;
        workerList = new ArrayList<>();
    }

    public void addWorker(Worker newWorker) {
        workerList.add(newWorker);
        workerAmount++;
    }

    public void removeWorker(Worker worker) {
        if (workerList.contains(worker)) {
            workerList.remove(worker);
            workerAmount--;
        }
    }

    public int getWorkerAmount() {
        return workerAmount;
    }

    public Worker getWorkerByIndex(int index) {
        return workerList.get(index);
    }

    public ArrayList<Worker> getWorkersByName(String name) {
        ArrayList<Worker> tempList = new ArrayList<>();
        for (Worker each : workerList) {
            if (each.getName().equals(name)) {
                tempList.add(each);
            }
        }
        return tempList;
    }

    public double getAverageSalary() {
        double sum = 0;
        for (Worker each : workerList) {
            sum += each.getRealSalary();
        }
        return sum / workerAmount;
    }

    public boolean isLowSalaryFullTime(Worker worker, double averageSalary) {
        if (isFullTime(worker)) {
            return worker.getRealSalary() < averageSalary;
        }
        return false;
    }

    public ArrayList<Worker> getLowSalaryFullTime() {
        ArrayList<Worker> tempList = new ArrayList<>();
        for (Worker each : workerList) {
            if (isLowSalaryFullTime(each, getAverageSalary())) {
                tempList.add(each);
            }
        }
        return tempList;
    }

    public boolean isFullTime(Worker worker) {
        return worker instanceof FullTimeWorker;
    }

    public boolean isPartTime(Worker worker) {
        return worker instanceof PartTimeWorker;
    }

    public double sumPartTimeRealSaraly() {
        double sum = 0;
        for (Worker each : workerList) {
            if (isPartTime(each)) {
                sum += each.getRealSalary();
            }
        }
        return sum;
    }

    public ArrayList<Worker> getAllFullTimeSortedSalary() {
        ArrayList<Worker> tempList = new ArrayList<>();
        for (Worker each : workerList) {
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

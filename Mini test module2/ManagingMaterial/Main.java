import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        MaterialManaging materialList = new MaterialManaging();
        materialList.addCrispyFlour("5", "Bot Chien Gion 55", 1213, LocalDate.now().minusYears(1), 15);
        materialList.addCrispyFlour("1", "Bot Chien Gion 99", 123, LocalDate.now().minusYears(5), 11);
        materialList.addCrispyFlour("3", "Bot Chien Gion 77", 789, LocalDate.now().minusYears(3), 13);
        materialList.addCrispyFlour("2", "Bot Chien Gion 88", 456, LocalDate.now().minusYears(4), 12);
        materialList.addCrispyFlour("4", "Bot Chien Gion 66", 1011, LocalDate.now().minusYears(2), 14);
        materialList.addMeat("6", "Thit Lon", 321, LocalDate.now().minusMonths(1), 16);
        materialList.addMeat("9", "Thit Bo", 1110, LocalDate.now().minusMonths(4), 19);
        materialList.addMeat("7", "Thit Ga", 654, LocalDate.now().minusMonths(2), 17);
        materialList.addMeat("10", "Thit Trau", 1312, LocalDate.now().minusMonths(5), 20);
        materialList.addMeat("8", "Thit Cho", 987, LocalDate.now().minusMonths(3), 18);
        System.out.println("Before sorting:");
        System.out.println(materialList);
        System.out.println("After sorting by Cost:");
        materialList.sortMaterialsByCost();
        System.out.println(materialList);
        double sumBasicMoney = materialList.sumMaterialBasicMoney();
        double sumRealMoney = materialList.sumMaterialRealMoney();
        System.out.println("Total basic money " + sumBasicMoney);
        System.out.println("Total real money " + sumRealMoney);
        double difference = sumBasicMoney - sumRealMoney;
        System.out.println("Difference: " + difference);
    }
}

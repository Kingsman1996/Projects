package message;

import data.OrderDataHandler;
import product.classes.Product;

public class OrderMessage {
    public static void alertOrderStart(int id) {
        System.out.println("Đang chuẩn bị đơn hàng, id: " + id);
    }

    public static void alertOrderEnd(int id) {
        System.out.println("Đơn hàng " + id + " đã hoàn thành");
    }

    public static void alertPreparingProduct(Product product) {
        System.out.println("Đang chuẩn bị " + product.getName());
    }

    public static void alertReadyProduct(Product product) {
        System.out.println(product.getName() + " đã sẵn sàng");
    }

    public static void showOrderList() {
        System.out.println("Danh sách đơn hàng:");
        for (String line : OrderDataHandler.getAllOrders()) {
            System.out.println(line);
        }
    }
}

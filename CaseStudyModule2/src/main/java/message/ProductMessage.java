package message;

import data.ProductDataHandler;

public class ProductMessage {
    public static void addedSuccess() {
        System.out.println("Đã thêm sản phẩm vào menu");
    }

    public static void fixedSuccess() {
        System.out.println("Đã sửa thông tin sản phẩm");
    }

    public static void deletedSuccess() {
        System.out.println("Sản phẩm đã được xóa thành công!");
    }

    public static void showProductList() {
        System.out.println("===== DANH SÁCH SẢN PHẨM =====");
        for (String product : ProductDataHandler.getAllProducts()) {
            System.out.println(product);
        }
    }

    public static void isEmpty() {
        System.out.println("Danh sách sản phẩm trống");
    }

    public static void notFound() {
        System.out.println("Không tìm thấy sản phẩm");
    }
}

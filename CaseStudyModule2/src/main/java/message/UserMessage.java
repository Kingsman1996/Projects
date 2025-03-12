package message;

import user.User;

public class UserMessage {
    public static void showSystemTitle() {
        System.out.println("==== HỆ THỐNG QUẢN LÝ NHÀ HÀNG ====");
    }

    public static void welcome(User user) {
        System.out.println("Chào bạn " + user.getUsername());
        System.out.println("Mời nhập lựa chọn");
    }

    public static void back() {
        System.out.println("0. Về menu trước");
    }

    public static void exit() {
        System.out.println("Thoát chương trình.");
    }

    public static void invalidChoice() {
        System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
    }

    public static void invalidPrice() {
        System.out.println("Giá không hợp lệ! Vui lòng nhập lại.");
    }

    public static void showAdminChoices() {
        System.out.println("1. Xem danh sách đơn hàng");
        System.out.println("2. Xem danh sách sản phẩm");
        System.out.println("3. Thêm sản phẩm");
        System.out.println("4. Sửa sản phẩm");
        System.out.println("5. Xóa sản phẩm");
        exit();
    }

    public static void showCustomerChoices() {
        System.out.print("Nhập lựa chọn: ");
        System.out.println("1. Xem menu & đặt hàng");
        System.out.println("2. Thanh toán");
        exit();
    }

    public static void insertProductName() {
        System.out.print("Nhập tên sản phẩm: ");
    }

    public static void insertProductPrice() {
        System.out.print("Nhập giá sản phẩm: ");
    }
    public static void propertyChoice() {
        System.out.println("Bạn muốn sửa gì?");
        System.out.println("1. Tên sản phẩm");
        System.out.println("2. Giá sản phẩm");
        back();
    }
}

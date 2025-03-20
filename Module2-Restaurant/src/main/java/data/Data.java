package data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Data {
    protected static final String PRODUCT_FILE = "src/main/resources/products.txt";
    protected static final String ORDER_FILE = "src/main/resources/order.txt";
    protected static final String USER_FILE = "src/main/resources/users.txt";

    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return lines;
    }

    public static void writeFile(String path, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void appendToFile(String path, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String getProductFile() {
        return PRODUCT_FILE;
    }

    public static String getOrderFile() {
        return ORDER_FILE;
    }

    public static String getUserFile() {
        return USER_FILE;
    }
}

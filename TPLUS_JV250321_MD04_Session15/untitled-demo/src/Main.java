import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/databasetest";
        String user = "root";
        String password = "123456789";

        try {
            // Tạo kết nối đến database
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Kết nối thành công!");
            // Đóng kết nối khi không dùng nữa
            connection.close();
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kết nối: " + e.getMessage());
        }
    }
}
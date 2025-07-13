import java.sql.*;
import java.util.Scanner;

public class StudentManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    //  Hiển thị danh sách sinh viên
    public void displayStudents() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            CallableStatement cs = conn.prepareCall("{CALL get_all_students()}");
            ResultSet rs = cs.executeQuery();

            System.out.println(" Danh sách sinh viên:");
            while (rs.next()) {
                System.out.println(" ID: " + rs.getInt("student_id"));
                System.out.println(" Họ tên: " + rs.getString("full_name"));
                System.out.println(" Ngày sinh: " + rs.getDate("date_of_birth"));
                System.out.println(" Email: " + rs.getString("email"));
                System.out.println("-------------------------");
            }
            cs.close();
        } catch (SQLException e) {
            System.err.println(" Lỗi khi lấy danh sách sinh viên: " + e.getMessage());
        }
    }

    //  Thêm sinh viên mới
    public void addStudent(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            CallableStatement cs = conn.prepareCall("{CALL add_student(?, ?, ?)}");

            System.out.print("👤 Nhập họ tên: ");
            String name = scanner.nextLine();

            String dob;
            while (true) {
                System.out.print(" Nhập ngày sinh (YYYY-MM-DD): ");
                dob = scanner.nextLine();
                if (dob.matches("\\d{4}-\\d{2}-\\d{2}")) break;
                System.out.println(" Định dạng ngày không hợp lệ! Hãy nhập lại.");
            }

            String email;
            while (true) {
                System.out.print(" Nhập email: ");
                email = scanner.nextLine();
                if (email.contains("@") && email.contains(".")) break;
                System.out.println(" Email không hợp lệ! Hãy nhập lại.");
            }

            cs.setString(1, name);
            cs.setDate(2, Date.valueOf(dob));
            cs.setString(3, email);

            cs.executeUpdate();
            System.out.println(" Thêm sinh viên thành công!");

            cs.close();
        } catch (SQLException e) {
            System.err.println(" Lỗi khi thêm sinh viên: " + e.getMessage());
        }
    }

    //  Sửa sinh viên
    public void updateStudent(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            CallableStatement cs = conn.prepareCall("{CALL update_student(?, ?, ?, ?)}");

            System.out.print(" Nhập ID sinh viên cần sửa: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("👤 Nhập họ tên mới: ");
            String name = scanner.nextLine();

            String dob;
            while (true) {
                System.out.print(" Nhập ngày sinh mới (YYYY-MM-DD): ");
                dob = scanner.nextLine();
                if (dob.matches("\\d{4}-\\d{2}-\\d{2}")) break;
                System.out.println(" Định dạng ngày không hợp lệ! Hãy nhập lại.");
            }

            System.out.print(" Nhập email mới: ");
            String email = scanner.nextLine();

            cs.setInt(1, id);
            cs.setString(2, name);
            cs.setDate(3, Date.valueOf(dob));
            cs.setString(4, email);

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(" Sửa sinh viên thành công!");
            } else {
                System.out.println(" Không tìm thấy sinh viên với ID: " + id);
            }

            cs.close();
        } catch (SQLException e) {
            System.err.println(" Lỗi khi sửa sinh viên: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println(" ID phải là số nguyên!");
        }
    }

    //  Xóa sinh viên
    public void deleteStudent(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            CallableStatement cs = conn.prepareCall("{CALL delete_student(?)}");

            System.out.print(" Nhập ID sinh viên cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine());

            cs.setInt(1, id);

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(" Xóa sinh viên thành công!");
            } else {
                System.out.println(" Không tìm thấy sinh viên với ID: " + id);
            }

            cs.close();
        } catch (SQLException e) {
            System.err.println(" Lỗi khi xóa sinh viên: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println(" ID phải là số nguyên!");
        }
    }
}
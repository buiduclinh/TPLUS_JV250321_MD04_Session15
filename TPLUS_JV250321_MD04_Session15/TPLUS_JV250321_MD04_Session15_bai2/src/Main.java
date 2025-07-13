import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n======= MENU QUẢN LÝ SINH VIÊN =======");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm sinh viên mới");
            System.out.println("3. Thoát");
            System.out.print("Chọn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        manager.displayStudents();
                        break;
                    case 2:
                        manager.addStudent(scanner);
                        break;
                    case 3:
                        System.out.println("👋 Thoát chương trình.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("⚠️ Lựa chọn không hợp lệ. Hãy nhập từ 1-3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Bạn phải nhập số nguyên! Hãy thử lại.");
            }
        }
    }
}
import service.TaskManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManagement tm = new TaskManagement();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMENU TO-DO LIST");
            System.out.println("1. Thêm công việc");
            System.out.println("2. Liệt kê công việc");
            System.out.println("3. Cập nhật trạng thái công việc");
            System.out.println("4. Xoá công việc");
            System.out.println("5. Tìm kiếm công việc");
            System.out.println("6. Thống kê công việc");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Nhập tên công việc: ");
                    String name = sc.nextLine();
                    System.out.print("Nhập trạng thái (Chưa hoàn thành/Đã hoàn thành): ");
                    String status = sc.nextLine();
                    tm.addTask(name, status);
                    break;
                case 2:
                    tm.listTasks();
                    break;
                case 3:
                    System.out.print("Nhập ID công việc: ");
                    int idUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nhập trạng thái mới: ");
                    String newStatus = sc.nextLine();
                    tm.updateTaskStatus(idUpdate, newStatus);
                    break;
                case 4:
                    System.out.print("Nhập ID công việc để xoá: ");
                    int idDelete = sc.nextInt();
                    tm.deleteTask(idDelete);
                    break;
                case 5:
                    System.out.print("Nhập tên công việc cần tìm: ");
                    String searchName = sc.nextLine();
                    tm.searchTaskByName(searchName);
                    break;
                case 6:
                    tm.taskStatistics();
                    break;
                case 0:
                    System.out.println("Thoát chương trình...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);
    }
}
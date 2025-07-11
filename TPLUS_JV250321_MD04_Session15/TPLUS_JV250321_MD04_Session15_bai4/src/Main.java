import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieManagement movieManager = new MovieManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n🎥 MENU QUẢN LÝ PHIM 🎥");
            System.out.println("1. Thêm phim");
            System.out.println("2. Liệt kê phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Xóa phim");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập tiêu đề: ");
                    String title = sc.nextLine();
                    System.out.print("Nhập đạo diễn: ");
                    String director = sc.nextLine();
                    System.out.print("Nhập năm phát hành: ");
                    int year = Integer.parseInt(sc.nextLine());
                    movieManager.addMovie(title, director, year);
                    break;
                case 2:
                    movieManager.listMovies();
                    break;
                case 3:
                    System.out.print("Nhập ID phim cần sửa: ");
                    int updateId = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập tiêu đề mới: ");
                    String newTitle = sc.nextLine();
                    System.out.print("Nhập đạo diễn mới: ");
                    String newDirector = sc.nextLine();
                    System.out.print("Nhập năm phát hành mới: ");
                    int newYear = Integer.parseInt(sc.nextLine());
                    movieManager.updateMovie(updateId, newTitle, newDirector, newYear);
                    break;
                case 4:
                    System.out.print("Nhập ID phim cần xóa: ");
                    int deleteId = Integer.parseInt(sc.nextLine());
                    movieManager.deleteMovie(deleteId);
                    break;
                case 5:
                    System.out.println("👋 Thoát chương trình.");
                    System.exit(0);
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }
}
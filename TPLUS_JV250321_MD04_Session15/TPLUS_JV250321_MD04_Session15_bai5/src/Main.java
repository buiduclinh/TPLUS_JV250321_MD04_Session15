import service.MovieBusiness;

import java.util.Scanner;

public class Main {
    private static final MovieBusiness movieBusiness = new MovieBusiness();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            do {
                System.out.println("==== Quản Lý Rạp Chiếu Phim ====");
                System.out.println("1. Thêm phim mới");
                System.out.println("2. Cập nhật phim");
                System.out.println("3. Xóa phim");
                System.out.println("4. Hiển thị danh sách phim");
                System.out.println("5. Thoát");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> movieBusiness.addMovie(scanner);
                    case 2 -> movieBusiness.updateMovie(scanner);
                    case 3 -> movieBusiness.deleteMovie(scanner);
                    case 4 -> movieBusiness.displayMovies();
                    case 5 -> System.exit(0);
                    default -> System.out.println("Chọn không hợp lệ!");
                }
            }
            while (true);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
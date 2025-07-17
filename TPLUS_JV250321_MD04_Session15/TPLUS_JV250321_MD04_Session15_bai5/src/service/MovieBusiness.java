package service;

import DAO.MovieDAOBusiness;
import entity.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MovieBusiness {
    private static MovieDAOBusiness movieDAOBusiness;


    public MovieBusiness() {
        movieDAOBusiness = new MovieDAOBusiness();
    }

    public static void addMovie(Scanner scanner) {
        try {
            Movie movie = new Movie();
            movie.inputData(scanner);
            boolean success = movieDAOBusiness.addMovie(movie);
            System.out.println(success ? "Thêm phim thành công!" : "Thêm phim thất bại!");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateMovie(Scanner scanner) {
        try {
            System.out.print("Nhập movie_id cần cập nhật: ");
            int movieId = Integer.parseInt(scanner.nextLine());

            Movie movie = movieDAOBusiness.findById(movieId);
            if (movie != null) {
                System.out.println("Can't found movie");
            } else {
                boolean update = false;
                do {
                    System.out.println("Update Menu");
                    System.out.println("1. Update title");
                    System.out.println("2. Update director");
                    System.out.println("3. Update duration");
                    System.out.println("4. Update release date movie");
                    System.out.println("5. Update status movie");
                    System.out.println("6. Back to menu.");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            String newTitle = scanner.nextLine();
                            movie.setTitle(newTitle);
                            break;
                        case 2:
                            String newDirector = scanner.nextLine();
                            movie.setDirector(newDirector);
                            break;
                        case 3:
                            String newDuration = scanner.nextLine();
                            movie.setDuration(Integer.parseInt(newDuration));
                            break;
                        case 4:
                            String newReleaseDate = scanner.nextLine();
                            movie.setReleaseDate(LocalDate.parse(newReleaseDate));
                            break;
                        case 5:
                            System.out.println("Press: 1: active, 0: inactive ");
                            boolean active = false;
                            try {
                                do {
                                    if (scanner.nextLine().equals("1")) {
                                        movie.setStatus(true);
                                        active = true;
                                    } else if (scanner.nextLine().equals("0")) {
                                        movie.setStatus(false);
                                        active = true;
                                    } else {
                                        System.out.println("Invalid Input!");
                                    }
                                } while (!active);
                            } catch (RuntimeException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 6:
                            update = true;
                            break;
                        default:
                            System.out.println("Invalid Choice");
                    }
                } while (!update);
                boolean success = movieDAOBusiness.updateMovie(movie);
                System.out.println(success ? "Cập nhật thành công!" : "Cập nhật thất bại!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteMovie(Scanner scanner) {
        try {
            System.out.print("Nhập movie_id cần xóa: ");
            int movieId = Integer.parseInt(scanner.nextLine());
            boolean success = movieDAOBusiness.deleteMovie(movieId);
            System.out.println(success ? "Xóa thành công!" : "Xóa thất bại!");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayMovies() {
        List<Movie> movies = movieDAOBusiness.getAllActiveMovies();
        if (movies.isEmpty()) {
            System.out.println("Không có phim nào!");
        } else {
            movies.forEach(System.out::println);
        }
    }
}

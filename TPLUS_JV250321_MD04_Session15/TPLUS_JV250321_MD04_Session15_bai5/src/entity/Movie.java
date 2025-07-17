package entity;

import java.time.LocalDate;
import java.util.Scanner;

public class Movie {
    private int movieId;
    private String title;
    private String director;
    private int duration;
    private LocalDate releaseDate;
    private boolean status;

    // Constructor
    public Movie() {
    }

    public Movie(String title, String director, int duration, LocalDate releaseDate) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.status = true;
    }

    // Getter & Setter
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner){
        System.out.print("Nhập tiêu đề phim: ");
        String title = scanner.nextLine();
        System.out.print("Nhập đạo diễn: ");
        String director = scanner.nextLine();
        System.out.print("Nhập thời lượng phim (phút): ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập ngày phát hành (yyyy-MM-dd): ");
        LocalDate releaseDate = LocalDate.parse(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", duration=" + duration +
                ", releaseDate=" + releaseDate +
                ", status=" + status +
                '}';
    }
}
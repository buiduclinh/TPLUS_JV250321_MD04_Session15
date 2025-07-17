package DAO;

import entity.Movie;

import java.util.List;

public interface MovieDAO {
    boolean addMovie(Movie movie);
    boolean updateMovie(Movie movie);
    boolean deleteMovie(int movieId);
    List<Movie> getAllActiveMovies();
    Movie findById(int movieId);
}
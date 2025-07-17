package DAO;

import entity.Movie;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOBusiness implements MovieDAO {

    @Override
    public boolean addMovie(Movie movie) {
        String sql = "{CALL add_movie(?,?,?,?)}";
        return DBHelper.executeUpdate(
                sql,
                movie.getTitle(),
                movie.getDirector(),
                movie.getDuration(),
                Date.valueOf(movie.getReleaseDate())
        );
    }

    @Override
    public boolean updateMovie(Movie movie) {
        String sql = "{CALL update_movie(?,?,?,?,?)}";
        return DBHelper.executeUpdate(
                sql,
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getDuration(),
                Date.valueOf(movie.getReleaseDate())
        );
    }

    @Override
    public boolean deleteMovie(int movieId) {
        String sql = "{CALL delete_movie(?)}";
        return DBHelper.executeUpdate(sql, movieId);
    }


    @Override
    public List<Movie> getAllActiveMovies() {
        String sql = "{CALL get_active_movies()}";
        return DBHelper.executeQuery(sql, rs -> {
            List<Movie> movies = new ArrayList<>();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieId(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setDuration(rs.getInt("duration"));
                movie.setReleaseDate(rs.getDate("release_date").toLocalDate());
                movie.setStatus(rs.getBoolean("status"));
                movies.add(movie);
            }
            return movies;
        });
    }

    public Movie findById(int movieId){
        String sql = "{CALL find_by_id(?)}";
        return DBHelper.executeQuery(sql,rs -> {
            Movie movie = new Movie();
            movie.setMovieId(rs.getInt("movie_id"));
            movie.setTitle(rs.getString("title"));
            movie.setDirector(rs.getString("director"));
            movie.setDuration(rs.getInt("duration"));
            movie.setReleaseDate(rs.getDate("release_date").toLocalDate());
            movie.setStatus(rs.getBoolean("status"));
            return movie;
        });
    }

}
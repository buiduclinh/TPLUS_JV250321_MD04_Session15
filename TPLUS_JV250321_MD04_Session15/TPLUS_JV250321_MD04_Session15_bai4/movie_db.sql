CREATE DATABASE movie_db;
USE movie_db;

CREATE TABLE movies (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        director VARCHAR(255) NOT NULL,
                        release_year INT NOT NULL
);


DELIMITER //
CREATE PROCEDURE add_movie(IN movie_title VARCHAR(255), IN movie_director VARCHAR(255), IN year INT)
BEGIN
    INSERT INTO movies (title, director, release_year)
    VALUES (movie_title, movie_director, year);
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE list_movies()
BEGIN
    SELECT * FROM movies;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_movie(IN movie_id INT, IN movie_title VARCHAR(255), IN movie_director VARCHAR(255), IN year INT)
BEGIN
    UPDATE movies
    SET title = movie_title, director = movie_director, release_year = year
    WHERE id = movie_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_movie(IN movie_id INT)
BEGIN
    DELETE FROM movies WHERE id = movie_id;
END //
DELIMITER ;



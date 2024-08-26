/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.DatabaseInfo.DBURL;
import static dao.DatabaseInfo.DRIVERNAME;
import static dao.DatabaseInfo.PASSDB;
import static dao.DatabaseInfo.USERDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Movie;
/**
 *
 * @author ADMIN
 */
public class MovieDB implements DatabaseInfo {
    public static Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e);
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    
    public static List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();      
        try (Connection con = getConnect()){
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM movies");
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int durationMinutes = rs.getInt("duration_minutes");
                String imageUrl = rs.getString("image_url");
                String director = rs.getString("direction");
                String description = rs.getString("description");
                String starring = rs.getString("starring");

                Movie movie = new Movie(id, title, durationMinutes, imageUrl, director, description, starring);
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return movies;
    }
    
        public static Movie getMovieById (int movie_id) {    
        try (Connection con = getConnect()){
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM movies where id = ?");
             stmt.setInt(1, movie_id);
             ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int durationMinutes = rs.getInt("duration_minutes");
                String imageUrl = rs.getString("image_url");
                String director = rs.getString("direction");
                String description = rs.getString("description");
                String starring = rs.getString("starring");

                Movie movie = new Movie(id, title, durationMinutes, imageUrl, director, description, starring);
                return movie;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean addMovie(Movie movie) {
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO Movies (title, durationMinutes, imageUrl, director, description, starring) VALUES (?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, movie.getTitle());
            stmt.setInt(2, movie.getDurationMinutes());
            stmt.setString(3, movie.getImageUrl());
            stmt.setString(4, movie.getDirector());
            stmt.setString(5, movie.getDescription());
            stmt.setString(6, movie.getStarring());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    movie.setId(generatedKeys.getInt(1));
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteMovie(int id) throws Exception {
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Movies WHERE id = ?");
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
}

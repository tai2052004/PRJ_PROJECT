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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Showtime;
/**
 *
 * @author ADMIN
 */
public class ShowtimeDB implements DatabaseInfo {
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
    
    public static List<Showtime> getAllShowtimes() {
        List<Showtime> showtimes = new ArrayList<>();
        String query = "SELECT * FROM showtimes";
        
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int movieId = rs.getInt("movie_id");
                int theaterId = rs.getInt("theater_id");
                Date date = rs.getDate("show_date");
                LocalDateTime startTime = rs.getTimestamp("show_time").toLocalDateTime();
                Showtime showtime = new Showtime(id, movieId, theaterId, date, startTime);
                showtimes.add(showtime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return showtimes;
    }
    
    public static List<Showtime> getShowtimesByMovie(int movie) {
        List<Showtime> showtimes = new ArrayList<>(); 
        try (Connection con = getConnect()){
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM showtimes where movie_id = ?");
            stmt.setInt(1, movie);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int movieId = rs.getInt("movie_id");
                int theaterId = rs.getInt("theater_id");
                Date date = rs.getDate("show_date");
                LocalDateTime startTime = rs.getTimestamp("show_time").toLocalDateTime();
                Showtime showtime = new Showtime(id, movieId, theaterId, date, startTime);
                showtimes.add(showtime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return showtimes;
    }
    
    public static List<Showtime> getShowtimesByMovieAndDay(int movie, String show_date, int theater_id) {
        List<Showtime> showtimes = new ArrayList<>(); 
        try (Connection con = getConnect()){
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM showtimes where movie_id = ? and show_date= ? and theater_id = ?");
            stmt.setInt(1, movie);
            stmt.setString(2, show_date);
            stmt.setInt(3, theater_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int movieId = rs.getInt("movie_id");
                int theaterId = rs.getInt("theater_id");
                Date date = rs.getDate("show_date");
                LocalDateTime startTime = rs.getTimestamp("show_time").toLocalDateTime();
                Showtime showtime = new Showtime(id, movieId, theaterId, date, startTime);
                showtimes.add(showtime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return showtimes;
    }
    public static Showtime getShowtimeById(int showtime_id) {
        try (Connection con = getConnect()){
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM showtimes where id = ?");
            stmt.setInt(1, showtime_id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("id");
                int movieId = rs.getInt("movie_id");
                int theaterId = rs.getInt("theater_id");
                Date date = rs.getDate("show_date");
                LocalDateTime startTime = rs.getTimestamp("show_time").toLocalDateTime();
                Showtime showtime = new Showtime(id, movieId, theaterId, date, startTime);
                return showtime;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean addShowtime(Showtime showtime) {
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO Showtimes (movie_id, theater_id, show_date, show_time) VALUES (?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, showtime.getMovieId());
            stmt.setInt(2, showtime.getTheaterId());
            stmt.setDate(3, new java.sql.Date(showtime.getShowDate().getTime()));
            stmt.setObject(4, showtime.getStartTime());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    showtime.setId(generatedKeys.getInt(1));
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteShowtime(int id) throws Exception {
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Showtimes WHERE id = ?");
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static List<Date> getShowDates() {
        List<Date> showDates = new ArrayList<>();
        String query = "SELECT DISTINCT show_date FROM showtimes";
        
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Date showDate = rs.getDate("show_date");
                showDates.add(showDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return showDates;
    }
}

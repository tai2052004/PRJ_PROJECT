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
import model.Seat;

/**
 *
 * @author ADMIN
 */
public class SeatDB implements DatabaseInfo {
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
    
    
    public static List<Seat> getBookedSeatsByShowtime(int showtimeId) {
        List<Seat> bookedSeats = new ArrayList<>();
        String query = "SELECT b.id AS booking_id, s.row, s.number " +
                       "FROM bookings b " +
                       "CROSS APPLY STRING_SPLIT(b.seat_id, ',') AS seat_ids " +
                       "INNER JOIN seats s ON s.id = TRY_CAST(seat_ids.value AS INT) " +
                       "WHERE b.showtime_id = ?";

        try (Connection con = ShowtimeDB.getConnect();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, showtimeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String row = rs.getString("row");
                int number = rs.getInt("number");
                Seat seat = new Seat(row, number);
                bookedSeats.add(seat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookedSeats;
    }
    
    public static boolean isBooked(char row, int num, List<Seat> seat){
        for (Seat s : seat){
            String row_String = String.valueOf(row);
            if (s.getRow().equals(row_String) && s.getNumber() == num){
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
       List<Seat> bookedSeats = getBookedSeatsByShowtime(2);
       for (Seat s : bookedSeats){
           System.out.println(s);
       }
    }
}


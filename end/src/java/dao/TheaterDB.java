/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Theater;
/**
 *
 * @author ADMIN
 */
public class TheaterDB implements DatabaseInfo {
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
    
    public static List<Theater> getAllTheaters() {
        List<Theater> theaters = new ArrayList<>();
        
        try (Connection con = getConnect()){
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM theater");
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");

                Theater theater = new Theater(id, name, location);
                theaters.add(theater);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return theaters;
    }
    
    public static boolean addTheater(Theater theater) {
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO Theater (name, location) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, theater.getName());
            stmt.setString(2, theater.getLocation());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    theater.setId(generatedKeys.getInt(1));
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteTheater(int id) throws Exception {
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM theater WHERE id = ?");
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static Theater getTheaterById(int id) {
    try (Connection con = getConnect();
         PreparedStatement stmt = con.prepareStatement("SELECT * FROM theater WHERE id = ?")) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int theaterId = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                Theater theater = new Theater(theaterId, name, location);
                return theater;
            } else {
                return null;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}

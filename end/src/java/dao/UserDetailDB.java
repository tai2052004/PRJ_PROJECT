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
import model.UserDetail;
import model.Users;

/**
 *
 * @author ngoct
 */
public class UserDetailDB implements DatabaseInfo {

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

    public static void AddUserDetail(UserDetail user_info) {
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO UsersDetail(ID, FullName, DOB) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setString(2, user_info.getFullname());
            stmt.setString(3, user_info.getDOB());
            stmt.setInt(1, user_info.getUsers_id());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new user detail was inserted successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static UserDetail getUserDetailById(int userId) {
        UserDetail userDetail = null;

        try (Connection con = getConnect()) {
            String query = "SELECT ID,FullName, DOB FROM UsersDetail WHERE ID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                userDetail = new UserDetail();
                userDetail.setUsers_id(rs.getInt("ID"));
                userDetail.setFullname(rs.getString("FullName"));
                userDetail.setDOB(rs.getString("DOB"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userDetail;
    }

    public static boolean userExists(int userId) {
        boolean exists = false;

        try (Connection con = getConnect()) {
            String query = "SELECT COUNT(*) FROM UsersDetail WHERE ID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                
                exists = (count > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    public static void updateUserDetails(UserDetail user_info) {

        try (Connection con = getConnect()) {
            String query = "UPDATE UsersDetail SET FullName = ?, DOB = ? WHERE ID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, user_info.getFullname());
            stmt.setString(2, user_info.getDOB());
            stmt.setInt(3, user_info.getUsers_id());

            int rowsUpdated = stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

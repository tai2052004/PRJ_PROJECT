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
import model.Users;


/**
 *
 * @author ngoct
 */
public class UserDB implements DatabaseInfo{
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
    public static Users login(String username, String password) {
        Users user = null;
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("SELECT ID,Users_tel, Role FROM Users WHERE Users_name=? AND Users_password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("ID");
                String tel = rs.getString("Users_tel");
                String role = rs.getString("Role");
                user = new Users(userID,username, password, tel, role);
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }
    public static boolean addUser(Users user) {
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(
                    "Insert into Users(Users_name,Users_password,Users_tel,Role) values (?, ?, ?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, user.getUser_name());
            stmt.setString(2, user.getUser_password());
            stmt.setString(3, user.getUser_tel());
            stmt.setString(4, user.getUser_role());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setUser_id(generatedKeys.getInt(1));
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     public static ArrayList<Users> listAllUsers() throws Exception {
        ArrayList<Users> userList = new ArrayList<>();
        try (Connection con=getConnect()) {
            PreparedStatement stmt = con.prepareStatement("SELECT ID, Users_name, Users_password,Users_tel, Role FROM Users");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int Userid = rs.getInt("ID");
                String username = rs.getString("Users_name");
                String password = rs.getString("Users_password");
                String tel = rs.getString("Users_tel");
                String role = rs.getString("Role");
                Users user = new Users(Userid,username, password, tel, role);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public static boolean userExists(String username) {
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM Users WHERE Users_name = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean deleteUser(int ID) throws Exception {
        try (Connection con=getConnect()) {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Users WHERE ID = ?");
            stmt.setInt(1, ID);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

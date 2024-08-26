package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.Bookings;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingsDB implements DatabaseInfo {

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

    public static boolean AddBooking(Bookings booking) {
        boolean s = false;
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO bookings(user_id, showtime_id, seat_id, status, payment_method, booking_date, price) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            stmt.setInt(1, booking.getUser_id());
            stmt.setInt(2, booking.getShowtime_id());
            stmt.setString(3, booking.getSeat_id());
            stmt.setString(4, booking.getStatus());
            stmt.setString(5, booking.getPayment_method());
            stmt.setString(6, booking.getBooking_date());
            stmt.setString(7, booking.getPrice());

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                s = true;
                System.out.println("A new booking was inserted successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    
    public static String FindSeatByRowAndNum(String p1, String p2) {
    String seatId = null; // Default value if seat is not found
    try (Connection con = getConnect()) {
        PreparedStatement stmt = con.prepareStatement(
            "SELECT id FROM seats WHERE row = ? AND number = ?"
        );

        stmt.setString(1, p1);
        stmt.setInt(2, Integer.parseInt(p2));

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            seatId = rs.getString("id");
            System.out.println("Seat ID found: " + seatId);
        } else {
            System.out.println("No seat found with the given row and number.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return seatId;
}

}

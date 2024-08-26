package servlet;

import dao.BookingsDB;
import static dao.BookingsDB.FindSeatByRowAndNum;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Bookings;
import model.Showtime;
import model.Users;

public class ConfirmOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> l = new ArrayList<>();
        HttpSession s = request.getSession();
        String paymentMethod = request.getParameter("payment-gate");
        Users user = (Users) s.getAttribute("user");
        String seat = (String) s.getAttribute("seats");
        String[] parts = seat.split(",");

        for (String part : parts) {
            String letter = part.substring(0, 1);
            String number = part.substring(1);
            String seatId = FindSeatByRowAndNum(letter, number);
            if (seatId != null && !seatId.isEmpty()) {
                l.add(seatId);
            }
        }

        String combine = String.join(",", l);

        Showtime showtime = (Showtime) s.getAttribute("selectedShowtime");
        LocalDate bookingDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String bd = bookingDate.format(formatter);
        String price = (String) s.getAttribute("totalprice");

        String status = "successful";
        int showtime_id = showtime.getId();
        int user_id = user.getUser_id();
        Bookings b = new Bookings(user_id, showtime_id, combine, status, paymentMethod, bd, price);
        s.setAttribute("newBooking", b);
        boolean c = BookingsDB.AddBooking(b);

        response.setContentType("text/html;charset=UTF-8");

        if (c) {
            response.sendRedirect("Bill.jsp");
        } else {
            response.sendRedirect("payment.jsp?error=Payment fail");
        }
    }

}

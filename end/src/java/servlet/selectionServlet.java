/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package servlet;

import dao.MovieDB;
import dao.ShowtimeDB;
import dao.TheaterDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Movie;
import model.Showtime;
import model.Theater;

/**
 *
 * @author ADMIN
 */
public class selectionServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet selectionServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet selectionServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        int theater = Integer.parseInt(request.getParameter("selectedTheater"));
        Theater theaterObj = TheaterDB.getTheaterById(theater);
        String date =  request.getParameter("selectedDate");
        int movie =  Integer.parseInt(request.getParameter("selectedMovie"));
        Movie movieObj = MovieDB.getMovieById(movie);
        int showtime =  Integer.parseInt(request.getParameter("selectedShowtime"));
        Showtime showtimeObj = ShowtimeDB.getShowtimeById(showtime);
        request.setAttribute("selectedTheater", theater);
        session.setAttribute("selectedTheater", theaterObj);
        session.setAttribute("selectedDate", date);
        session.setAttribute("selectedMovie", movieObj);
        session.setAttribute("selectedShowtime", showtimeObj);

        request.getRequestDispatcher("book_seat.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       String theater = request.getParameter("selectedTheater");
       request.setAttribute("selectedTheater", theater);
       String date =  request.getParameter("selectedDate");
       request.setAttribute("selectedDate", date);
       request.getRequestDispatcher("newjsp.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

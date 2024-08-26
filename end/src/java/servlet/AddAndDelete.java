/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Users;
import dao.UserDB;

/**
 *
 * @author THANH CONG
 */
@WebServlet(name = "AddAndDelete", urlPatterns = {"/AddAndDelete"})
public class AddAndDelete extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user already exists
        String action = request.getParameter("action");
        if ( "add".equalsIgnoreCase(action)) {
            Add(request,response);
        }
        else {
            doDelete(request,response);
        }


    }
    protected void Add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String role = request.getParameter("role");

        
        // Create a new User object
        Users newUser = new Users(username, password,tel, role);
        

        // Check if the user already exists
        if (UserDB.userExists(username)) {
            response.sendRedirect("manage_users.jsp?error=User already exists");
            return;
        }

        // Add the new user to the database
        boolean success = UserDB.addUser(newUser);
        
        request.setAttribute("user1", newUser);
        if (success) {
            request.getRequestDispatcher("manage_users.jsp?success=User add succesfully").forward(request, response);
        } else {
            response.sendRedirect("manage_users.jsp?error=Error adding user");
        }
    }
   
   @Override
protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
        int userID = Integer.parseInt(req.getParameter("userID"));

        // Delete the user from the database
        boolean success = UserDB.deleteUser(userID);

        if (success) {
            resp.sendRedirect("manage_users.jsp?success=User deleted successfully");
        } else {
            resp.sendRedirect("manage_users.jsp?error=Error deleting user");
        }
    } catch (Exception e) {
        e.printStackTrace();
        resp.sendRedirect("manage_users.jsp?error=An error occurred while deleting the user");
    }
}
    
}

package Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import model.Users;


public class NewFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<html><head>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
            out.println("</head><body>");
            out.println("<script>");
            out.println("Swal.fire({");
            out.println("  icon: 'warning',");
            out.println("  title: 'Oops...',");
            out.println("  text: 'Please login before accessing this page!',");
            out.println("  confirmButtonText: 'Login',");
            out.println("}).then((result) => {");
            out.println("  if (result.isConfirmed) {");
            out.println("    window.location.href = 'login.jsp';");
            out.println("  }");
            out.println("});");
            out.println("</script>");
            out.println("</body></html>");
            out.close();
            return;  
        } 
        
        chain.doFilter(request, response);
    }

    public void destroy() {}
}

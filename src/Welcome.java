import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Welcome extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
        response.setContentType("text/html;charset=UTF-8");
        
        System.out.println("nnnnnnnnnn");
        System.out.println(request.getParameter("haslo"));
        PrintWriter out = response.getWriter();
        out.println("<p>xcvxcvxcv" + request.getParameter("email") + "</p> <a href=\"http://localhost:8080/BiletyLotnicze/\">fgdfgfg</a>" + request.getParameter("haslo"));
        out.println(request.getParameter("haslo"));
        out.println(request);
      }  
}
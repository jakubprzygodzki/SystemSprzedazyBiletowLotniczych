import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        String login = request.getParameter("login");
        String haslo = request.getParameter("password");
        
        
        System.out.println("login: "+login);
        System.out.println("haslo: "+haslo);
        
        if(Validate.checkUser(login, haslo))
        {
            RequestDispatcher rs = request.getRequestDispatcher("indexLog.html");
            rs.forward(request, response);
        }
        else
        {
           out.println("Username or Password incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("index.html");
           rs.include(request, response);
        }
    }  
}
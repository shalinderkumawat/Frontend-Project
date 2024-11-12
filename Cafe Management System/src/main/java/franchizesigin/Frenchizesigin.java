package franchizesigin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/frenchizesignin")
public class Frenchizesigin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Frenchizesigin() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafe?characterEncoding=utf8", "root", "kumar@12345"); 
            
            String sql = "SELECT * FROM frenchize WHERE name=? AND email = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1 , name);
            ps.setString(2, email);
            ps.setString(3, password);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                RequestDispatcher rd = request.getRequestDispatcher("staffwelcom.html");
                rd.forward(request, response);
            }
            
            else {
                out.println("<h3>Invalid username or password</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.include(request, response);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        } 
    }
}

package frenchize;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet("/frenchize_signup")
public class Frenchize extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Frenchize() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cafe?characterEncoding=utf8","root","kumar@12345");  
			//here sonoo is database name, root is username and password  
			String sql="select * from frenchize where name=? and fathername=? and mothername=? and email=? and contact=? and password=?";
			 PreparedStatement ps=con.prepareStatement( "insert into frenchize(name , fatherName , motherName , email , contact , password) values (? , ? , ? , ? , ? ,?)");  
			 
			 ps.setString(1,request.getParameter("name"));
			 ps.setString(2,request.getParameter("fatherName"));
			 ps.setString(3,request.getParameter("motherName"));
			 ps.setString(4,request.getParameter("email"));
			 ps.setString(5,request.getParameter("contact"));
			 ps.setString(6,request.getParameter("password"));
			 
			 int status = ps.executeUpdate();
             if (status > 0) {
                 response.sendRedirect("staffwelcom.html");  // Redirect to cafeitem.html after successful insert
             } else {
                 out.println("<h3>Failed to insert data. Please try again.</h3>");
             } 
		        con.close();  
			 
		}catch(Exception e){out.println(e);} 
    }
}

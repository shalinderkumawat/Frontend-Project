package staff;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/staff")
public class Staffinfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Staffinfo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafe?characterEncoding=utf8", "root", "kumar@12345");

            // PreparedStatement for the insert query
            String insertSQL = "INSERT INTO staff (roleInput, name, email, degree, experience, shift, section, managerName, tasks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertSQL);

            // Set each parameter correctly
            ps.setString(1, request.getParameter("roleInput"));
            ps.setString(2, request.getParameter("name"));
            ps.setString(3, request.getParameter("email"));
            ps.setString(4, request.getParameter("degree"));
            ps.setString(5, request.getParameter("experience"));
            ps.setString(6, request.getParameter("shift"));
            ps.setString(7, request.getParameter("section"));
            ps.setString(8, request.getParameter("managerName"));
            ps.setString(9, request.getParameter("tasks"));

            // Execute the update and get status
            int status = ps.executeUpdate();
            if (status > 0) {
                response.sendRedirect("staffwelcom.html");  // Redirect to cafeitem.html after successful insert
            } else {
                out.println("<h3>Failed to insert data. Please try again.</h3>");
            } 
		        con.close();  

            con.close();
        } catch (Exception e) {
            out.println("Error: " + e);
        }
    }
}

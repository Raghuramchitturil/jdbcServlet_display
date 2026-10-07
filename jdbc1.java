//package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import org.example.utils.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/students")
public class jdbc1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");




            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","svist@123");

            if (con == null) {
                out.println("<h1>Connection is NULL</h1>");
                return;
            } else {
                out.println("<h1>Connection Created Successfully</h1>");
            }

            Statement stmt = con.createStatement();

            ResultSet rs =
                    stmt.executeQuery("SELECT * FROM csef");

            out.println("<html>");
            out.println("<body>");

            out.println("<h1>Student Records</h1>");

            out.println("<table border='1'>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Marks</th>");
            out.println("</tr>");

            while(rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getInt("id") + "</td>");

                out.println("<td>" + rs.getString("name") + "</td>");

                out.println("<td>" + rs.getString("marks") + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");

        } catch (SQLException | ClassNotFoundException e) {

            //out.println("<h2>SQLException Occurred</h2>");
            //out.println("<pre>");
            e.printStackTrace(out);
            //out.println("</pre>");

        }

    }
}

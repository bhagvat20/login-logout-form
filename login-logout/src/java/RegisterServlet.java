import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException {

        String uname = req.getParameter("uname");
        String pass = req.getParameter("pass");

        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(username, password) VALUES(?,?)"
            );
            ps.setString(1, uname);
            ps.setString(2, pass);

            int saved = ps.executeUpdate();

            if(saved > 0){
                res.sendRedirect("index.jsp");
            } else {
                res.sendRedirect("register.jsp");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

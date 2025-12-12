import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        String uname = req.getParameter("uname");
        String pass = req.getParameter("pass");

        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );
            ps.setString(1, uname);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                HttpSession session = req.getSession();
                session.setAttribute("username", uname);
                res.sendRedirect("welcome.jsp");
            } else {
                res.sendRedirect("error.jsp");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

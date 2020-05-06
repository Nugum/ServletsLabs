import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String u_login=request.getParameter("u_login");
        String u_password=request.getParameter("u_password");
        RequestDispatcher dispatcher = null;
        HttpSession session = null;
        ArrayList<User> users = UserDB.select();
        if(u_login != "" && u_password != "" && u_login!=null && u_login!=null
                && UserCheck.isUserPasCorrect(users, u_login, u_password)){
            session = request.getSession(true);
            session.setAttribute("u_login", u_login);
            dispatcher = request.getRequestDispatcher("hello.jsp");
        } else {
            //request.getRequestDispatcher("error.jsp").forward(request,response);
            throw new IllegalStateException("Wrong Login or Password");
        }
        dispatcher.forward(request, response);
    }
}
package com.my.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.my.servlet.User;
import com.my.servlet.UserDB;


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
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
        dispatcher.forward(request, response);
    }
}
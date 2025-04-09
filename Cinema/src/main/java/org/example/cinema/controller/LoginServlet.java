package org.example.cinema.controller;

import org.example.cinema.model.User;
import org.example.cinema.service.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputUserName = request.getParameter("username");
        String inputPassword = request.getParameter("password");
        User foundUser = userDAO.getUser(inputUserName);

        if (foundUser != null && foundUser.getUserPassword().equals(inputPassword)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", foundUser);
            String nextPage = "";
            switch (foundUser.getUserRole()) {
                case "admin":
                    nextPage = "admin";
                    break;
                case "customer":
                    nextPage = "customer";
                    break;
            }
            response.sendRedirect(nextPage);
        } else {
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}

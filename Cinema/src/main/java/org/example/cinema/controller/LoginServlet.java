package org.example.cinema.controller;

import org.example.cinema.model.User;
import org.example.cinema.service.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputUserName = request.getParameter("username");
        String inputPassword = request.getParameter("password");
        User foundUser = userDAO.getUser(inputUserName);
        if (foundUser == null) {
            request.setAttribute("error", "Tên đăng nhập không đúng!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        String storedPassword = foundUser.getUserPassword();
        if (!verifyPassword(inputPassword, storedPassword)) {
            request.setAttribute("error", "Mật khẩu không đúng!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", foundUser);
        String redirectPath = foundUser.getUserRole().equals("admin") ? "admin" : "customer";
        response.sendRedirect(redirectPath);
    }
    private boolean verifyPassword(String inputPassword, String storedPassword) {
        return storedPassword.equals(inputPassword);
    }
}

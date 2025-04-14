package org.example.cinema.controller;

import org.example.cinema.model.User;
import org.example.cinema.service.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputUserName = request.getParameter("inputUsername");
        String inputPassword = request.getParameter("inputPassword");
        if (!userDAO.isExist(inputUserName)) {
            request.setAttribute("error", "Sai tên đăng nhập!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        User foundUser = userDAO.getByUsername(inputUserName);
        String storedPassword = foundUser.getPassword();
        if (!verifyPassword(inputPassword, storedPassword)) {
            request.setAttribute("error", "Sai mật khẩu!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", foundUser);
        String redirectPath = foundUser.isAdmin() ? "admin" : "customer";
        response.sendRedirect(redirectPath);
    }

    private boolean verifyPassword(String inputPassword, String storedPassword) {
        return storedPassword.equals(inputPassword);
    }
}

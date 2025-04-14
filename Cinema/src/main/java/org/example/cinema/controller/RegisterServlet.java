package org.example.cinema.controller;

import org.example.cinema.model.User;
import org.example.cinema.service.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String inputUsername = request.getParameter("inputUsername");
        String inputPassword = request.getParameter("inputPassword");
        if (userDAO.isExist(inputUsername)) {
            request.setAttribute("error", "Tên tài khoản đã tồn tại!");
        } else {
            User user = new User();
            user.setUsername(inputUsername);
            user.setPassword(inputPassword);
            userDAO.add(user);
            request.setAttribute("success", "Đăng ký thành công!");
        }
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}

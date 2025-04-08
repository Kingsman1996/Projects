package org.example.cinema.controller;

import org.example.cinema.model.dao.UserDAO;
import org.example.cinema.model.object.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO ;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("customer");
        if (userDAO.add(user)) {
            request.setAttribute("success", "Đăng ký thành công!");
        } else {
            request.setAttribute("error", "Tên tài khoản đã tồn tại!");
        }
        request.getRequestDispatcher("register.jsp").forward(request,response);
    }
}

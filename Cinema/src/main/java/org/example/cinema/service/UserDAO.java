package org.example.cinema.service;

import org.example.cinema.model.User;

import java.sql.*;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DBConnection.connect();
    }


    public User getUser(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userid"));
                user.setUserName(rs.getString("username"));
                user.setUserPassword(rs.getString("userpassword"));
                user.setUserRole(rs.getString("userrole"));
                return user;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void addUser(User user) {
        String sql = "INSERT INTO user (username, userpassword) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm user: " + e.getMessage());
        }
    }
}

package org.example.cinema.model.dao;

import org.example.cinema.model.object.User;
import org.example.cinema.model.DBConnection;

import java.sql.*;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DBConnection.connect();
    }

    public boolean add(User user) {
        String sql = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public User getUser(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}

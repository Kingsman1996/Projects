package org.example.cinema.service;

import org.example.cinema.model.User;

import java.sql.*;

public class UserDAO {
    private static final String INSERT_SQL = "INSERT INTO user (username, password) VALUES (?, ?)";
    private static final String GET_BY_ID_SQL = "SELECT * FROM user WHERE id = ?";
    private static final String GET_BY_USERNAME_SQL = "SELECT * FROM user WHERE username = ?";
    private static final String CHECK_SQL = "SELECT COUNT(*) FROM user WHERE username = ?";

    private final Connection connection = DBConnection.connect();

    public void add(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm user: " + e.getMessage());
        }
    }

    public User getById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User outputUser = new User();
                outputUser.setId(resultSet.getInt("id"));
                outputUser.setUsername(resultSet.getString("username"));
                return outputUser;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi UserDAO -> getById " + e.getMessage());
        }
        return null;
    }

    public User getByUsername(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME_SQL)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User outputUser = new User();
                outputUser.setId(resultSet.getInt("id"));
                outputUser.setUsername(resultSet.getString("username"));
                outputUser.setPassword(resultSet.getString("password"));
                outputUser.setAdmin(resultSet.getBoolean("isadmin"));
                return outputUser;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi UserDAO -> getByUsername " + e.getMessage());
        }
        return null;
    }

    public boolean isExist(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_SQL)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi UserDAO -> isExist " + e.getMessage());
        }
        return false;
    }
}

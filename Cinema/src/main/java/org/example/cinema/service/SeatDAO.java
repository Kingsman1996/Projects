package org.example.cinema.service;

import org.example.cinema.model.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {
    private final Connection connection = DBConnection.connect();
    public static final String GET_ALL_SQL = "SELECT * FROM seat";
    public static final String GET_BY_ID_SQL = "SELECT * FROM seat WHERE id = ?";

    public List<Seat> getAll() {
        List<Seat> seatList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL)) {
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                seatList.add(convertResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SeatDAO getAll" + e.getMessage());
        }
        return seatList;
    }

    public Seat getById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return convertResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SeatDAO getById" + e.getMessage());
        }
        return null;
    }

    private static Seat convertResultSet(ResultSet resultSet) {
        Seat seat = new Seat();
        try {
            seat.setId(resultSet.getInt("id"));
            seat.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            System.out.println("Lỗi SeatDAO convertResultSet" + e.getMessage());
        }
        return seat;
    }
}

package org.example.cinema.service;


import org.example.cinema.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private static final String GET_ALL_SQL = "SELECT * FROM room";
    private final Connection connection = DBConnection.connect();

    public List<Room> getAll() {
        List<Room> roomList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
                roomList.add(room);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi RoomDAO getAll: " + e.getMessage());
        }
        return roomList;
    }
}

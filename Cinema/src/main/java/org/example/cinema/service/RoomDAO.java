package org.example.cinema.service;


import org.example.cinema.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private final Connection connection = DBConnection.connect();

    public List<Room> getAll() {
        List<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
                roomList.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomList;
    }
}

package org.example.cinema.service;

import org.example.cinema.model.Seat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {
    private Connection connection;

    public SeatDAO() {
        connection = DBConnection.connect();
    }

    public List<Seat> getAll() {
        List<Seat> seatList = new ArrayList<>();
        String sql = "SELECT * FROM seat";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Seat seat = new Seat();
                seat.setId(rs.getInt("seatid"));
                seat.setSeatCode(rs.getString("seatcode"));
                seat.setStatus(rs.getString("seatstatus"));
                seatList.add(seat);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách ghế: " + e.getMessage());
        }
        return seatList;
    }

    public boolean update(String seatCode, String newStatus) {
        String sql = "UPDATE seat SET seatstatus = ? WHERE seatcode = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setString(2, seatCode);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật trạng thái ghế: " + e.getMessage());
            return false;
        }
    }
}
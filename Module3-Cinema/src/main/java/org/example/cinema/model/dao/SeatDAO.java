package org.example.cinema.model.dao;

import org.example.cinema.model.DBConnection;
import org.example.cinema.model.object.Seat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {
    private Connection connection;

    public SeatDAO() {
        connection = DBConnection.connect();
    }

    public List<Seat> getAll() {
        List<Seat> seats = new ArrayList<>();
        String query = "CALL get_all_records('seat')";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Seat seat = new Seat();
                seat.setId(rs.getInt("seatid"));
                seat.setCode(rs.getString("seatcode"));
                seat.setStatus(rs.getString("seatstatus"));
                seats.add(seat);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return seats;
    }

    public void book(String seatCode) {
        String query = "CALL update_seat_status(?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, seatCode);
            ps.setString(2, "booked");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}

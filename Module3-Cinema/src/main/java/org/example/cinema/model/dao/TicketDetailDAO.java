package org.example.cinema.model.dao;

import org.example.cinema.model.DBConnection;
import org.example.cinema.model.object.TicketDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDetailDAO {
    private Connection connection;

    public TicketDetailDAO() {
        this.connection = DBConnection.connect();
    }

    public List<TicketDetail> getAll() {
        List<TicketDetail> ticketDetails = new ArrayList<>();
        String sql = "{CALL GetTicketDetails()}";
        try (CallableStatement stmt = connection.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TicketDetail ticketDetail = new TicketDetail();
                ticketDetail.setTicketId(rs.getInt("tkid"));
                ticketDetail.setUsername(rs.getString("username"));
                ticketDetail.setSeatCode(rs.getString("seatcode"));
                ticketDetail.setShowDate(rs.getDate("showdate").toLocalDate());
                ticketDetail.setShowTime(rs.getTime("showtime").toLocalTime());
                ticketDetail.setMovieName(rs.getString("mvname"));
                ticketDetails.add(ticketDetail);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách vé: " + e.getMessage());
        }
        return ticketDetails;
    }
    public List<TicketDetail> getByUsername(String username) {
        List<TicketDetail> ticketDetails = new ArrayList<>();
        String sql = "CALL GetTicketDetailsByUsername(?)";
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TicketDetail ticketDetail = new TicketDetail();
                ticketDetail.setTicketId(rs.getInt("tkid"));
                ticketDetail.setUsername(rs.getString("username"));
                ticketDetail.setSeatCode(rs.getString("seatcode"));
                ticketDetail.setShowDate(rs.getDate("showdate").toLocalDate());
                ticketDetail.setShowTime(rs.getTime("showtime").toLocalTime());
                ticketDetail.setMovieName(rs.getString("mvname"));
                ticketDetails.add(ticketDetail);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ticketDetails;
    }
}

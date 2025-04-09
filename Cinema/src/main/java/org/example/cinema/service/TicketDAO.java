package org.example.cinema.service;


import org.example.cinema.model.TicketDetailDTO;
import org.example.cinema.model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private Connection connection;

    public TicketDAO() {
        this.connection = DBConnection.connect();
    }

    public void add(Ticket ticket) {
        String sql = "INSERT INTO ticket (userid, seatid, playtimeid) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getUserId());
            stmt.setInt(2, ticket.getSeatId());
            stmt.setInt(3, ticket.getPlaytimeId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm vé: " + e.getMessage());
        }
    }

    public List<TicketDetailDTO> getAllTicketDetails() {
        return executeTicketQuery("{CALL GetAllTicketDetail()}", null);
    }

    public List<TicketDetailDTO> getTicketDetailsByUsername(String username) {
        return executeTicketQuery("{CALL GetTicketDetailByUsername(?)}", username);
    }

    private List<TicketDetailDTO> executeTicketQuery(String sql, String username) {
        List<TicketDetailDTO> ticketDetails = new ArrayList<>();
        try (CallableStatement stmt = connection.prepareCall(sql)) {
            if (username != null) {
                stmt.setString(1, username);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TicketDetailDTO ticket = new TicketDetailDTO();
                    ticket.setTicketId(rs.getInt("ticketid"));
                    ticket.setUsername(rs.getString("username"));
                    ticket.setSeatCode(rs.getString("seatcode"));
                    ticket.setDay(rs.getDate("playday").toLocalDate());
                    ticket.setTime(rs.getTime("playtime").toLocalTime());
                    ticket.setMovieName(rs.getString("moviename"));
                    ticketDetails.add(ticket);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn vé: " + e.getMessage());
        }
        return ticketDetails;
    }
}

package org.example.cinema.model.dao;


import org.example.cinema.model.DBConnection;
import org.example.cinema.model.object.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private Connection connection;

    public TicketDAO() {
        this.connection = DBConnection.connect();
    }

    public void add(Ticket ticket) {
        String sql = "INSERT INTO ticket (userid, seatid, mtid) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getUserId());
            stmt.setInt(2, ticket.getSeatId());
            stmt.setInt(3, ticket.getMovieTimeId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Ticket getByUserId(int userId) {
        String sql = "SELECT * FROM ticket WHERE userid = ?";
        Ticket ticket = new Ticket();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ticket.setId(rs.getInt("id"));
                ticket.setUserId(userId);
                ticket.setSeatId(rs.getInt("seatid"));
                ticket.setMovieTimeId(rs.getInt("mtid"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ticket;
    }

    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setUserId(rs.getInt("userid"));
                ticket.setSeatId(rs.getInt("seatid"));
                ticket.setMovieTimeId(rs.getInt("mtid"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tickets;
    }

}

package org.example.cinema.service;


import org.example.cinema.model.Seat;
import org.example.cinema.model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    public static final String GET_ALL_SQL = "SELECT * FROM ticket";
    public static final String GET_BY_USER_SQL = "SELECT * FROM ticket WHERE userid = ?";
    public static final String GET_BY_PLAYTIME_SQL = "SELECT * FROM ticket WHERE playtimeid = ?";
    public static final String INSERT_SQL = "INSERT INTO ticket (userid, playtimeid, seatid) VALUES (?, ?, ?)";

    private final Connection connection = DBConnection.connect();
    private final UserDAO userDAO = new UserDAO();
    private final PlayTimeDAO playtimeDAO = new PlayTimeDAO();

    public List<Ticket> getByUser(int userId) {
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(GET_BY_USER_SQL)) {
            stmt.setInt(1, userId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    ticketList.add(mapResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ticketList;
    }

    public List<Ticket> getByPlaytime(int playtimeId) {
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(GET_BY_PLAYTIME_SQL)) {
            stmt.setInt(1, playtimeId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    ticketList.add(mapResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ticketList;
    }

    public void insert(Ticket ticket) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_SQL)) {
            stmt.setInt(1, ticket.getUser().getId());
            stmt.setInt(2, ticket.getPlaytime().getId());
            stmt.setInt(3, ticket.getSeat().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi thêm vé" + e.getMessage());
        }
    }

    public List<Ticket> getAll() {
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(GET_ALL_SQL)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                ticketList.add(mapResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn vé" + e.getMessage());
        }
        return ticketList;
    }

    private Ticket mapResultSet(ResultSet resultSet) {
        Ticket ticket = new Ticket();
        try {
            ticket.setId(resultSet.getInt("id"));
            ticket.setUser(userDAO.getById(resultSet.getInt("userid")));
            ticket.setPlaytime(playtimeDAO.getById(resultSet.getInt("playtimeid")));
            Seat seat = new SeatDAO().getById(resultSet.getInt("seatid"));
            ticket.setSeat(seat);
            ticket.setBookingTime(resultSet.getTimestamp("bookingtime").toLocalDateTime());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ticket;
    }
}

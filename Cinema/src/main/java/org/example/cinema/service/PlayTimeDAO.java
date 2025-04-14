package org.example.cinema.service;

import org.example.cinema.model.Playtime;
import org.example.cinema.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaytimeDAO {
    public static final String INSERT_SQL = "INSERT INTO playtime (movieid, roomid, day, hour) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_SQL = "UPDATE playtime SET roomid = ?, movieid = ?, day = ?, hour = ? WHERE id = ?";
    public static final String DELETE_SQL = "DELETE FROM playtime WHERE id = ?";
    public static final String CHECK_EXIST_SQL = "SELECT COUNT(*) FROM playtime WHERE roomid = ? AND day = ? AND hour = ?";
    public static final String GET_ALL_SQL = "SELECT * FROM playtimedetail";
    public static final String GET_BY_ID_SQL = "SELECT * FROM playtimedetail WHERE playtimeid = ?";
    private static final String IS_BOOKED_SQL = "SELECT COUNT(*) AS bookedplaytime FROM ticket WHERE playtimeid = ?";
    private static final String GET_BY_MOVIE_SQL = "SELECT * FROM playtimedetail WHERE moviename = ?";

    private final Connection connection = DBConnection.connect();

    public void insert(Playtime playtime) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setInt(1, playtime.getMovie().getId());
            preparedStatement.setInt(2, playtime.getRoom().getId());
            preparedStatement.setDate(3, Date.valueOf(playtime.getDay()));
            preparedStatement.setTime(4, Time.valueOf(playtime.getHour()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi PlaytimeDAO insert " + e.getMessage());
        }
    }

    public void update(Playtime playtime) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setInt(1, playtime.getRoom().getId());
            preparedStatement.setInt(2, playtime.getMovie().getId());
            preparedStatement.setDate(3, Date.valueOf(playtime.getDay()));
            preparedStatement.setTime(4, Time.valueOf(playtime.getHour()));
            preparedStatement.setInt(5, playtime.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi PlaytimeDAO update " + e.getMessage());
        }
    }

    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi PlaytimeDAO delete " + e.getMessage());
        }
    }

    public Playtime getById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return convertResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi PlaytimeDAO getById " + e.getMessage());
        }
        return null;
    }

    private Playtime convertResultSet(ResultSet resultSet) {
        Playtime playtime = new Playtime();
        try {
            playtime.setId(resultSet.getInt("playtimeid"));

            playtime.setMovie(MovieDAO.convertResultSetWithNoId(resultSet));

            Room room = new Room();
            room.setName(resultSet.getString("roomname"));
            playtime.setRoom(room);

            playtime.setDay(resultSet.getDate("day").toLocalDate());

            playtime.setHour(resultSet.getTime("hour").toLocalTime());
        } catch (SQLException e) {
            System.out.println("Lỗi PlaytimeDAO convertResultSet " + e.getMessage());
        }
        return playtime;
    }

    public List<Playtime> getAll() {
        List<Playtime> playtimeList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                playtimeList.add(convertResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi PlaytimeDAO getAll " + e.getMessage());
        }
        return playtimeList;
    }

    public List<Playtime> getByMovieName(String movieName) {
        List<Playtime> playtimeList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_MOVIE_SQL)) {
            preparedStatement.setString(1, movieName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                playtimeList.add(convertResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi PlaytimeDAO getByMovieName " + e.getMessage());
        }
        return playtimeList;
    }

    public boolean isBooked(int playTimeId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(IS_BOOKED_SQL)) {
            preparedStatement.setInt(1, playTimeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi PlaytimeDAO isBooked " + e.getMessage());
        }
        return false;
    }

    public boolean isExisted(Playtime playtime) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EXIST_SQL)) {
            preparedStatement.setInt(1, playtime.getRoom().getId());
            preparedStatement.setDate(2, Date.valueOf(playtime.getDay()));
            preparedStatement.setTime(3, Time.valueOf(playtime.getHour()));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi PlaytimeDAO isExisted " + e.getMessage());
        }
        return false;
    }
}




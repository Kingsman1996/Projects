package org.example.cinema.service;

import org.example.cinema.model.Playtime;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayTimeDAO {
    private Connection connection;

    public PlayTimeDAO() {
        this.connection = DBConnection.connect();
    }

    public boolean add(Playtime playtime) {
        String sql = "INSERT INTO playtime (movieid, playday, hour) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, playtime.getMovieId());
            stmt.setDate(2, Date.valueOf(playtime.getPlayDay()));
            stmt.setTime(3, Time.valueOf(playtime.getHour()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm suất chiếu: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Playtime playtime) {
        String sql = "UPDATE playtime SET movieid = ?, playday = ?, hour = ? WHERE playtimeid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, playtime.getMovieId());
            stmt.setDate(2, Date.valueOf(playtime.getPlayDay()));
            stmt.setTime(3, Time.valueOf(playtime.getHour()));
            stmt.setInt(4, playtime.getPlayTimeId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật suất chiếu: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        if (hasTickets(id)) {
            return false;
        }
        String sql = "DELETE FROM playtime WHERE playtimeid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa suất chiếu: " + e.getMessage());
        }
        return false;
    }

    public Playtime getById(int id) {
        String sql = "SELECT FROM playtime WHERE playtimeid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Playtime playtime = new Playtime();
                playtime.setMovieId(rs.getInt("movieid"));
                playtime.setPlayTimeId(rs.getInt("playtimeid"));
                playtime.setPlayDay(rs.getDate("playday").toLocalDate());
                playtime.setHour(rs.getTime("hour").toLocalTime());
                return playtime;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa suất chiếu: " + e.getMessage());
        }
        return null;
    }

    private List<Playtime> executePlaytimeQuery(String sql, Integer movieId) {
        List<Playtime> playtimeList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            if (movieId != null) {
                stmt.setInt(1, movieId);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    playtimeList.add(mapResultSetToPlaytime(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn suất chiếu: " + e.getMessage());
        }
        return playtimeList;
    }

    private Playtime mapResultSetToPlaytime(ResultSet rs) throws SQLException {
        Playtime playtime = new Playtime();
        playtime.setPlayTimeId(rs.getInt("playtimeid"));
        playtime.setMovieId(rs.getInt("movieid"));
        playtime.setPlayDay(rs.getDate("playday").toLocalDate());
        playtime.setHour(rs.getTime("hour").toLocalTime());
        return playtime;
    }

    public List<Playtime> getAll() {
        return executePlaytimeQuery("SELECT * FROM playtime", null);
    }

    public List<Playtime> getByMovieId(int movieId) {
        return executePlaytimeQuery("SELECT * FROM playtime WHERE movieid = ?", movieId);
    }

    private List<Playtime> getByProcedure(String procedureName) {
        List<Playtime> playtimeList = new ArrayList<>();
        try (CallableStatement stmt = connection.prepareCall("{CALL " + procedureName + "}")) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    playtimeList.add(mapResultSetToPlaytime(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi stored procedure " + procedureName + ": " + e.getMessage());
        }
        return playtimeList;
    }

    public List<Playtime> getThisWeek() {
        return getByProcedure("GetThisWeekPlaytime");
    }

    public List<Playtime> getNextWeek() {
        return getByProcedure("GetNextWeekPlaytime");
    }

    public boolean hasTickets(int playTimeId) {
        String sql = "SELECT COUNT(*) FROM ticket WHERE playtimeid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, playTimeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra ticket: " + e.getMessage());
        }
        return false;
    }

    public boolean hasMovie(int movieId) {
        String sql = "SELECT COUNT(*) FROM playtime WHERE movieid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra playtime theo movieId: " + e.getMessage());
        }
        return false;
    }
}




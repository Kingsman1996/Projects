package org.example.cinema.model.dao;

import org.example.cinema.model.object.MovieTime;
import org.example.cinema.model.DBConnection;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieTimeDAO {
    private Connection connection;

    public MovieTimeDAO() {
        this.connection = DBConnection.connect();
    }

    public void add(MovieTime movieTime) {
        String sql = "INSERT INTO movietime (mvid, showdate, showtime) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movieTime.getMovieId());
            stmt.setDate(2, Date.valueOf(movieTime.getShowDate()));
            stmt.setTime(3, Time.valueOf(movieTime.getShowTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm lịch chiếu: " + e.getMessage());
        }
    }

    public List<MovieTime> getAll() {
        List<MovieTime> movieTimes = new ArrayList<>();
        String sql = "SELECT * FROM movietime";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MovieTime movieTime = new MovieTime();
                movieTime.setId(rs.getInt("mtid"));
                movieTime.setMovieId(rs.getInt("mvid"));
                movieTime.setShowDate(rs.getDate("showdate").toLocalDate());
                movieTime.setShowTime(rs.getTime("showtime").toLocalTime());
                movieTimes.add(movieTime);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn lịch chiếu: " + e.getMessage());
        }
        return movieTimes;
    }

    public void delete(int id) {
        String sql = "DELETE FROM movietime WHERE mtid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa lịch chiếu: " + e.getMessage());
        }
    }

    public MovieTime getById(int id) {
        MovieTime movieTime = new MovieTime();
        String sql = "SELECT * FROM movietime WHERE mtid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                movieTime.setId(rs.getInt("mtid"));
                movieTime.setMovieId(rs.getInt("mvid"));
                movieTime.setShowDate(rs.getDate("showdate").toLocalDate());
                movieTime.setShowTime(rs.getTime("showtime").toLocalTime());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return movieTime;
    }

    public void update(MovieTime movieTime) {
        String sql = "UPDATE movietime SET mvid = ?, showdate = ?, showtime = ? WHERE mtid = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, movieTime.getMovieId());
            pstm.setDate(2, Date.valueOf(movieTime.getShowDate()));
            pstm.setTime(3, Time.valueOf(movieTime.getShowTime()));
            pstm.setInt(4, movieTime.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<MovieTime> getThisWeek() {
        List<MovieTime> movieTimes = new ArrayList<>();
        String sql = "SELECT * FROM movietime WHERE showdate BETWEEN ? AND ?";

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startOfWeek));
            stmt.setDate(2, Date.valueOf(endOfWeek));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MovieTime movieTime = new MovieTime();
                    movieTime.setId(rs.getInt("mtid"));
                    movieTime.setMovieId(rs.getInt("mvid"));
                    movieTime.setShowDate(rs.getDate("showdate").toLocalDate());
                    movieTime.setShowTime(rs.getTime("showtime").toLocalTime());
                    movieTimes.add(movieTime);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn lịch chiếu trong tuần: " + e.getMessage());
        }
        return movieTimes;
    }
    public List<MovieTime> getNextWeek() {
        List<MovieTime> movieTimes = new ArrayList<>();
        String sql = "SELECT * FROM movietime WHERE showdate BETWEEN ? AND ?";

        LocalDate today = LocalDate.now();
        LocalDate startOfNextWeek = today.with(DayOfWeek.MONDAY).plusWeeks(1);
        LocalDate endOfNextWeek = today.with(DayOfWeek.SUNDAY).plusWeeks(1);

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startOfNextWeek));
            stmt.setDate(2, Date.valueOf(endOfNextWeek));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MovieTime movieTime = new MovieTime();
                    movieTime.setId(rs.getInt("mtid"));
                    movieTime.setMovieId(rs.getInt("mvid"));
                    movieTime.setShowDate(rs.getDate("showdate").toLocalDate());
                    movieTime.setShowTime(rs.getTime("showtime").toLocalTime());
                    movieTimes.add(movieTime);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn lịch chiếu tuần tiếp theo: " + e.getMessage());
        }
        return movieTimes;
    }
    public List<MovieTime> getByMovieId(int movieId) {
        List<MovieTime> movieTimes = new ArrayList<>();
        String sql = "SELECT * FROM movietime WHERE mvid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MovieTime movieTime = new MovieTime();
                movieTime.setId(rs.getInt("mtid"));
                movieTime.setMovieId(rs.getInt("mvid"));
                movieTime.setShowDate(rs.getDate("showdate").toLocalDate());
                movieTime.setShowTime(rs.getTime("showtime").toLocalTime());
                movieTimes.add(movieTime);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return movieTimes;
    }
}



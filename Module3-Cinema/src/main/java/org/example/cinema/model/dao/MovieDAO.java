package org.example.cinema.model.dao;

import org.example.cinema.model.DBConnection;
import org.example.cinema.model.object.Movie;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    private Connection connection;

    public MovieDAO() {
        this.connection = DBConnection.connect();
    }

    public void add(Movie movie) {
        String query = "INSERT INTO movie (mvname, mvtype, duration) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, movie.getName());
            pstmt.setString(2, movie.getType());
            pstmt.setInt(3, movie.getDuration());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movie";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("mvid"));
                movie.setName(rs.getString("mvname"));
                movie.setType(rs.getString("mvtype"));
                movie.setDuration(rs.getInt("duration"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return movies;
    }

    public Movie getById(int id) {
        String query = "SELECT * FROM movie WHERE mvid = ?";
        Movie movie = null;
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                movie = new Movie();
                movie.setId(rs.getInt("mvid"));
                movie.setName(rs.getString("mvname"));
                movie.setType(rs.getString("mvtype"));
                movie.setDuration(rs.getInt("duration"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return movie;
    }

    public void update(Movie movie) {
        String query = "UPDATE movie SET mvname = ?, mvtype = ?, duration = ? WHERE mvid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, movie.getName());
            pstmt.setString(2, movie.getType());
            pstmt.setInt(3, movie.getDuration());
            pstmt.setInt(4, movie.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM movie WHERE mvid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Movie> getThisWeek() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT DISTINCT m.mvid, m.mvname, m.mvtype, m.duration " +
                "FROM movie m " +
                "JOIN movietime mt ON m.mvid = mt.mvid " +
                "WHERE mt.showdate BETWEEN ? AND ?";
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startOfWeek));
            stmt.setDate(2, Date.valueOf(endOfWeek));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Movie movie = new Movie();
                    movie.setId(rs.getInt("mvid"));
                    movie.setName(rs.getString("mvname"));
                    movie.setType(rs.getString("mvtype"));
                    movie.setDuration(rs.getInt("duration"));
                    movies.add(movie);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn phim chiếu trong tuần: " + e.getMessage());
        }
        return movies;
    }

    public List<Movie> getNextWeek() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT DISTINCT m.* FROM movie m " +
                "JOIN movietime mt ON m.mvid = mt.mvid " +
                "WHERE mt.showdate BETWEEN ? AND ?";

        LocalDate today = LocalDate.now();
        LocalDate startOfNextWeek = today.with(DayOfWeek.MONDAY).plusWeeks(1);
        LocalDate endOfNextWeek = today.with(DayOfWeek.SUNDAY).plusWeeks(1);

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(startOfNextWeek));
            stmt.setDate(2, Date.valueOf(endOfNextWeek));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Movie movie = new Movie();
                    movie.setId(rs.getInt("mvid"));
                    movie.setName(rs.getString("mvname"));
                    movie.setType(rs.getString("mvtype"));
                    movie.setDuration(rs.getInt("duration"));
                    movies.add(movie);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn phim có lịch chiếu tuần tới: " + e.getMessage());
        }
        return movies;
    }
}

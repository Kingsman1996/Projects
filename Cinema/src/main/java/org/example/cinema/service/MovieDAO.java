package org.example.cinema.service;

import org.example.cinema.model.Movie;

import java.sql.*;
import java.util.*;

public class MovieDAO {
    private final Connection connection = DBConnection.connect();
    public static final String INSERT_SQL = "INSERT INTO movie (name, type, duration) VALUES (?, ?, ?)";
    public static final String UPDATE_SQL = "UPDATE movie SET name = ?, type = ?, duration = ? WHERE id = ?";
    public static final String DELETE_SQL = "DELETE FROM movie WHERE id = ?";
    public static final String GET_ALL_SQL = "SELECT * FROM movie ORDER BY id";
    public static final String GET_BY_ID_SQL = "SELECT * FROM movie WHERE id = ?";
    public static final String HAS_PLAYTIME_SQL = "SELECT COUNT(*) FROM playtime WHERE movieid = ?";
    public static final String GET_THIS_WEEK_SQL = "SELECT moviename, movietype, movieduration \n" +
            "FROM playtimedetail \n" +
            "WHERE YEARWEEK(day, 1) = YEARWEEK(CURDATE(), 1)\n" +
            "GROUP BY moviename, movietype, movieduration;\n";
    public static final String GET_NEXT_WEEK_SQL = "SELECT DISTINCT moviename, movietype, movieduration\n" +
            "FROM playtimedetail\n" +
            "WHERE YEARWEEK(day, 1) = YEARWEEK(DATE_ADD(CURDATE(), INTERVAL 7 DAY), 1);\n";

    public void add(Movie movie) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setString(2, movie.getType());
            preparedStatement.setInt(3, movie.getDuration());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Movie> getAll() {
        List<Movie> movieList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(GET_ALL_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                movieList.add(mapResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return movieList;
    }

    private Movie mapResultSet(ResultSet resultSet) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setName(resultSet.getString("name"));
        movie.setType(resultSet.getString("type"));
        movie.setDuration(resultSet.getInt("duration"));
        movie.setImageUrl(movie.getName().toLowerCase().replaceAll("\\s+", "").replace(":", "").replace("&", "") + ".jpg");
        return movie;
    }

    public Movie getById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void update(Movie movie) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setString(2, movie.getType());
            preparedStatement.setInt(3, movie.getDuration());
            preparedStatement.setInt(4, movie.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean hasPlaytime(int movieId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(HAS_PLAYTIME_SQL)) {
            preparedStatement.setInt(1, movieId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Movie> getThisWeek() {
        List<Movie> movieList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(GET_THIS_WEEK_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setName(resultSet.getString("moviename"));
                movie.setType(resultSet.getString("movietype"));
                movie.setDuration(resultSet.getInt("movieduration"));
                movie.setImageUrl(movie.getName().toLowerCase().replaceAll("\\s+", "").replace(":", "").replace("&", "") + ".jpg");
                movieList.add(movie);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi MovieDAO GetThisWeek" + e.getMessage());
        }
        return movieList;
    }

    public List<Movie> getNextWeek() {
        List<Movie> movieList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(GET_NEXT_WEEK_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setName(resultSet.getString("moviename"));
                movie.setType(resultSet.getString("movietype"));
                movie.setDuration(resultSet.getInt("movieduration"));
                movie.setImageUrl(movie.getName().toLowerCase().replaceAll("\\s+", "").replace(":", "").replace("&", "") + ".jpg");
                movieList.add(movie);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi MovieDAO GetNextWeek" + e.getMessage());
        }
        return movieList;
    }
}

package org.example.cinema.service;

import org.example.cinema.model.MoviePlayTimeDTO;
import org.example.cinema.model.Movie;
import org.example.cinema.model.Playtime;

import java.sql.*;
import java.util.*;

public class MovieDAO {
    private Connection connection;

    public MovieDAO() {
        this.connection = DBConnection.connect();
    }

    public void add(Movie movie) {
        String query = "INSERT INTO movie (moviename, movietype, movieduration) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.setString(2, movie.getMovieType());
            preparedStatement.setInt(3, movie.getMovieDuration());
            preparedStatement.executeUpdate();
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
                movie.setMovieId(rs.getInt("movieid"));
                movie.setMovieName(rs.getString("moviename"));
                movie.setMovieType(rs.getString("movietype"));
                movie.setMovieDuration(rs.getInt("movieduration"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return movies;
    }

    public Movie getById(int id) {
        String query = "SELECT * FROM movie WHERE movieid = ?";
        Movie movie = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                movie = new Movie();
                movie.setMovieId(rs.getInt("movieid"));
                movie.setMovieName(rs.getString("moviename"));
                movie.setMovieType(rs.getString("movietype"));
                movie.setMovieDuration(rs.getInt("movieduration"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return movie;
    }

    public void update(Movie movie) {
        String query = "UPDATE movie SET moviename = ?, movietype = ?, movieduration = ? WHERE movieid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, movie.getMovieName());
            pstmt.setString(2, movie.getMovieType());
            pstmt.setInt(3, movie.getMovieDuration());
            pstmt.setInt(4, movie.getMovieId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM movie WHERE movieid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<MoviePlayTimeDTO> getThisWeek() {
        return executeMovieQuery("CALL GetThisWeekMovie()", new PlayTimeDAO().getThisWeek());
    }

    public List<MoviePlayTimeDTO> getNextWeek() {
        return executeMovieQuery("CALL GetNextWeekMovie()", new PlayTimeDAO().getNextWeek());
    }

    private List<MoviePlayTimeDTO> executeMovieQuery(String sql, List<Playtime> playtimeList) {
        Map<Integer, MoviePlayTimeDTO> movieMap = new HashMap<>();
        try (CallableStatement cst = connection.prepareCall(sql);
             ResultSet rs = cst.executeQuery()) {
            while (rs.next()) {
                MoviePlayTimeDTO movieDTO = new MoviePlayTimeDTO();
                int movieId = rs.getInt("movieid");
                movieDTO.setMovieId(movieId);
                movieDTO.setMovieName(rs.getString("moviename"));
                movieDTO.setMovieType(rs.getString("movietype"));
                movieDTO.setMovieDuration(rs.getInt("movieduration"));
                movieDTO.setPlayTimes(playtimeList);
                movieMap.put(movieId, movieDTO);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn " + e.getMessage());
        }
        return new ArrayList<>(movieMap.values());
    }
}

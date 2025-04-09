package org.example.cinema.service;

import org.example.cinema.model.MoviePlayTimeDTO;
import org.example.cinema.model.Movie;
import org.example.cinema.model.Playtime;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MovieDAO {
    private Connection connection;

    public MovieDAO() {
        this.connection = DBConnection.connect();
    }

    public void add(Movie movie) {
        String query = "INSERT INTO movie (moviename, movietype, movieduration) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, movie.getMovieName());
            pstmt.setString(2, movie.getMovieType());
            pstmt.setInt(3, movie.getMovieDuration());
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
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
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
        return executeMovieQuery("CALL GetThisWeekMovie()");
    }

    public List<MoviePlayTimeDTO> getNextWeek() {
        return executeMovieQuery("CALL GetNextWeekMovie()");
    }

    private List<MoviePlayTimeDTO> executeMovieQuery(String sql) {
        Map<Integer, MoviePlayTimeDTO> movieMap = new LinkedHashMap<>();
        try (CallableStatement cst = connection.prepareCall(sql);
             ResultSet rs = cst.executeQuery()) {
            while (rs.next()) {
                int movieId = rs.getInt("movieid");
                MoviePlayTimeDTO movieDTO = movieMap.get(movieId);
                if (movieDTO == null) {
                    movieDTO = new MoviePlayTimeDTO();
                    movieDTO.setMovieId(movieId);
                    movieDTO.setMovieName(rs.getString("moviename"));
                    movieDTO.setMovieType(rs.getString("movietype"));
                    movieDTO.setMovieDuration(rs.getInt("movieduration"));
                    movieDTO.setPlayTimes(new ArrayList<>());
                    movieMap.put(movieId, movieDTO);
                }
                Playtime playtime = new Playtime();
                playtime.setDay(rs.getDate("playday").toLocalDate());
                playtime.setTime(rs.getTime("playtime").toLocalTime());
                movieDTO.getPlayTimes().add(playtime);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn phim: " + e.getMessage());
        }
        return new ArrayList<>(movieMap.values());
    }
}

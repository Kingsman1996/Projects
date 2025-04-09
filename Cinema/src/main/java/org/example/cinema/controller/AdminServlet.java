package org.example.cinema.controller;

import org.example.cinema.model.Movie;
import org.example.cinema.model.Playtime;
import org.example.cinema.model.TicketDetailDTO;
import org.example.cinema.service.MovieDAO;
import org.example.cinema.service.PlayTimeDAO;
import org.example.cinema.service.TicketDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private PlayTimeDAO playTimeDAO;
    private TicketDAO ticketDAO;

    @Override
    public void init() {
        movieDAO = new MovieDAO();
        playTimeDAO = new PlayTimeDAO();
        ticketDAO = new TicketDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addMovie":
                response.sendRedirect("admin/addMovie.jsp");
                break;
            case "updateMovie":
                int movieId = Integer.parseInt(request.getParameter("id"));
                Movie foundMovie = movieDAO.getById(movieId);
                request.setAttribute("movie", foundMovie);
                request.getRequestDispatcher("/admin/updateMovie.jsp").forward(request, response);
                break;
            case "addPlayTime":
                request.setAttribute("movies", movieDAO.getAll());
                request.getRequestDispatcher("/admin/addPlayTime.jsp").forward(request, response);
                break;
            case "updatePlayTime":
                int playTimeId = Integer.parseInt(request.getParameter("playTimeId"));
                Playtime foundPlayTime = playTimeDAO.getById(playTimeId);
                List<Movie> movies = movieDAO.getAll();
                request.setAttribute("movies", movies);
                request.setAttribute("playTime", foundPlayTime);
                request.getRequestDispatcher("/admin/updatePlayTime.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("movies", movieDAO.getAll());
                request.setAttribute("playTimes", playTimeDAO.getAll());
                List<TicketDetailDTO> dtoList = ticketDAO.getAllTicketDetails();
                request.setAttribute("ticketDetails", dtoList);
                request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        switch (action) {
            case "addMovie":
                String mvname = request.getParameter("mvname");
                String mvtype = request.getParameter("mvtype");
                int duration = Integer.parseInt(request.getParameter("duration"));
                addMovie(mvname, mvtype, duration);
                request.setAttribute("message", "Thêm phim thành công!");
                request.getRequestDispatcher("/admin/addMovie.jsp").forward(request, response);
                break;
            case "updateMovie":
                int movieId = Integer.parseInt(request.getParameter("id"));
                mvname = request.getParameter("name");
                mvtype = request.getParameter("type");
                duration = Integer.parseInt(request.getParameter("duration"));
                updateMovie(movieId, mvname, mvtype, duration);
                Movie updatedMovie = movieDAO.getById(movieId);
                request.setAttribute("movie", updatedMovie);
                request.setAttribute("message", "Cập nhật phim thành công!");
                request.getRequestDispatcher("/admin/updateMovie.jsp").forward(request, response);
                break;
            case "deleteMovie":
                movieId = Integer.parseInt(request.getParameter("id"));
                if (!playTimeDAO.hasMovie(movieId)) {
                    movieDAO.delete(movieId);
                } else {
                    String failMessage = "Không thể xóa phim có lịch chiếu";
                    HttpSession session = request.getSession();
                    session.setAttribute("failMessage", failMessage);
                }
                response.sendRedirect("admin");
                break;
            case "addPlayTime":
                movieId = Integer.parseInt(request.getParameter("movieId"));
                String showDate = request.getParameter("showDate");
                String showTime = request.getParameter("showTime");
                addPlayTime(movieId, showDate, showTime);
                request.setAttribute("message", "Thêm lịch chiếu thành công!");
                request.getRequestDispatcher("/admin/addPlayTime.jsp").forward(request, response);
                break;
            case "updatePlayTime":
                int playTimeId = Integer.parseInt(request.getParameter("playTimeId"));
                int newMovieId = Integer.parseInt(request.getParameter("newMovieId"));
                String newShowDate = request.getParameter("newShowDate");
                String newShowTime = request.getParameter("newShowTime");
                updatePlayTime(playTimeId, newMovieId, newShowDate, newShowTime);
                Playtime updatedPlayTime = playTimeDAO.getById(playTimeId);
                List<Movie> movies = movieDAO.getAll();
                request.setAttribute("movies", movies);
                request.setAttribute("playTime", updatedPlayTime);
                request.setAttribute("message", "Cập nhật thành công!");
                request.getRequestDispatcher("/admin/updatePlayTime.jsp").forward(request, response);
                break;
            case "deletePlayTime":
                playTimeId = Integer.parseInt(request.getParameter("playTimeId"));
                boolean deleted = playTimeDAO.delete(playTimeId);
                if (!deleted) {
                    String failMessage = "Không thể xóa lịch chiếu vì đã có vé được đặt";
                    HttpSession session = request.getSession();
                    session.setAttribute("failMessage", failMessage);
                }
                response.sendRedirect("admin");
                break;
        }
    }

    public void addMovie(String name, String type, int duration) {
        Movie movie = new Movie();
        movie.setMovieName(name);
        movie.setMovieType(type);
        movie.setMovieDuration(duration);
        movieDAO.add(movie);
    }

    public void updateMovie(int id, String name, String type, int duration) {
        Movie movie = new Movie();
        movie.setMovieId(id);
        movie.setMovieName(name);
        movie.setMovieType(type);
        movie.setMovieDuration(duration);
        movieDAO.update(movie);
    }

    public void addPlayTime(int movieId, String showDate, String showTime) {
        Playtime playTime = new Playtime();
        playTime.setMovieId(movieId);
        playTime.setDay(LocalDate.parse(showDate));
        playTime.setTime(LocalTime.parse(showTime));
        playTimeDAO.add(playTime);
    }

    public void updatePlayTime(int playTimeId, int movieId, String showDate, String showTime) {
        Playtime playTime = new Playtime();
        playTime.setPlayTimeId(playTimeId);
        playTime.setMovieId(movieId);
        playTime.setDay(LocalDate.parse(showDate));
        playTime.setTime(LocalTime.parse(showTime));
        playTimeDAO.update(playTime);
    }
}


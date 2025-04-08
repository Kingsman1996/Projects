package org.example.cinema.controller;

import org.example.cinema.model.dao.MovieDAO;
import org.example.cinema.model.dao.MovieTimeDAO;
import org.example.cinema.model.dao.TicketDetailDAO;
import org.example.cinema.model.object.Movie;
import org.example.cinema.model.object.MovieTime;
import org.example.cinema.model.object.TicketDetail;

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
    private MovieTimeDAO movieTimeDAO;
    private TicketDetailDAO ticketDetailDAO;

    @Override
    public void init() {
        movieDAO = new MovieDAO();
        movieTimeDAO = new MovieTimeDAO();
        ticketDetailDAO = new TicketDetailDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addMovie":
                request.getRequestDispatcher("/admin/addMovie.jsp").forward(request, response);
                break;
            case "updateMovie":
                String id = request.getParameter("id");
                Movie movie = movieDAO.getById(Integer.parseInt(id));
                request.setAttribute("movie", movie);
                request.getRequestDispatcher("/admin/updateMovie.jsp").forward(request, response);
                break;
            case "addMovieTime":
                request.setAttribute("movies", movieDAO.getAll());
                request.getRequestDispatcher("/admin/addMovieTime.jsp").forward(request, response);
                break;
            case "updateMovieTime":
                id = request.getParameter("id");
                MovieTime movieTime = movieTimeDAO.getById(Integer.parseInt(id));
                String movieId = request.getParameter("movieId");
                movie = movieDAO.getById(Integer.parseInt(movieId));
                List<Movie> movies = movieDAO.getAll();
                request.setAttribute("movieTime", movieTime);
                request.setAttribute("movie", movie);
                request.setAttribute("movies", movies);
                request.getRequestDispatcher("/admin/updateMovieTime.jsp").forward(request, response);
                break;
            case "history":
                List<TicketDetail> ticketDetails = ticketDetailDAO.getAll();
                request.setAttribute("ticketDetails", ticketDetails);
                request.getRequestDispatcher("/admin/history.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("movies", movieDAO.getAll());
                request.setAttribute("movieTimes", movieTimeDAO.getAll());
                request.getRequestDispatcher("/admin/firstview.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        switch (action) {
            case "addMovie":
                String mvname = request.getParameter("mvname");
                String mvtype = request.getParameter("mvtype");
                String duration = request.getParameter("duration");
                addMovie(mvname, mvtype, duration);
                request.setAttribute("message", "Thêm phim thành công!");
                response.sendRedirect("admin?action=addMovie&message=success");
                break;
            case "updateMovie":
                String movieId = request.getParameter("id");
                mvname = request.getParameter("name");
                mvtype = request.getParameter("type");
                duration = request.getParameter("duration");
                updateMovie(movieId, mvname, mvtype, duration);
                Movie updatedMovie = movieDAO.getById(Integer.parseInt(movieId));
                request.setAttribute("movie", updatedMovie);
                request.setAttribute("message", "Cập nhật phim thành công!");
                request.getRequestDispatcher("/admin/updateMovie.jsp").forward(request, response);
                break;
            case "deleteMovie":
                movieId = request.getParameter("id");
                movieDAO.delete(Integer.parseInt(movieId));
                HttpSession session = request.getSession();
                session.setAttribute("message", "Xóa phim thành công!");
                response.sendRedirect(request.getContextPath() + "/admin");
                break;
            case "addMovieTime":
                movieId = request.getParameter("movieId");
                String showDate = request.getParameter("showDate");
                String showTime = request.getParameter("showTime");
                addMovieTime(movieId, showDate, showTime);
                session = request.getSession();
                session.setAttribute("message", "Thêm lịch chiếu thành công!");
                response.sendRedirect("/admin/addMovieTime.jsp");
                break;
            case "updateMovieTime":
                String movieTimeId = request.getParameter("id");
                movieId = request.getParameter("newMovieId");
                showDate = request.getParameter("newShowDate");
                showTime = request.getParameter("newShowTime");
                updateMovieTime(movieTimeId, movieId, showDate, showTime);
                MovieTime updatedMovieTime = movieTimeDAO.getById(Integer.parseInt(movieTimeId));
                List<Movie> movies = movieDAO.getAll();
                Movie movie = movieDAO.getById(Integer.parseInt(movieId));
                request.setAttribute("movieTime", updatedMovieTime);
                request.setAttribute("movie", movie);
                request.setAttribute("movies", movies);
                request.setAttribute("message", "Cập nhật thành công!");
                request.getRequestDispatcher("/admin/updateMovieTime.jsp").forward(request, response);
                break;
            case "deleteMovieTime":
                movieTimeId = request.getParameter("id");
                movieTimeDAO.delete(Integer.parseInt(movieTimeId));
                session = request.getSession();
                session.setAttribute("message", "Xóa phim thành công!");
                response.sendRedirect(request.getContextPath() + "/admin");
                break;
        }
    }
    public void addMovie(String name, String type, String duration) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setType(type);
        movie.setDuration(Integer.parseInt(duration));
        movieDAO.add(movie);
    }
    public void updateMovie(String id, String name, String type, String duration) {
        Movie movie = new Movie();
        movie.setId(Integer.parseInt(id));
        movie.setName(name);
        movie.setType(type);
        movie.setDuration(Integer.parseInt(duration));
        movieDAO.update(movie);
    }
    public void addMovieTime(String movieId, String showDate, String showTime) {
        MovieTime movieTime = new MovieTime();
        movieTime.setMovieId(Integer.parseInt(movieId));
        movieTime.setShowDate(LocalDate.parse(showDate));
        movieTime.setShowTime(LocalTime.parse(showTime));
        movieTimeDAO.add(movieTime);
    }
    public void updateMovieTime(String movieTimeId, String movieId, String showDate, String showTime) {
        MovieTime movieTime = new MovieTime();
        movieTime.setId(Integer.parseInt(movieTimeId));
        movieTime.setMovieId(Integer.parseInt(movieId));
        movieTime.setShowDate(LocalDate.parse(showDate));
        movieTime.setShowTime(LocalTime.parse(showTime));
        movieTimeDAO.update(movieTime);
    }
}


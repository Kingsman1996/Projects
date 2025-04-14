package org.example.cinema.controller;

import org.example.cinema.model.*;
import org.example.cinema.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private final MovieDAO movieDAO = new MovieDAO();
    private final PlaytimeDAO playtimeDAO = new PlaytimeDAO();
    private final TicketDAO ticketDAO = new TicketDAO();
    private final RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addMovie":
                response.sendRedirect("admin/addMovie.jsp");
                break;
            case "updateMovie":
                setMovieToRequestByMovieId(request);
                request.getRequestDispatcher("/admin/updateMovie.jsp").forward(request, response);
                break;
            case "addPlaytime":
                setMovieListToRequest(request);
                setRoomListToRequest(request);
                request.getRequestDispatcher("/admin/addPlaytime.jsp").forward(request, response);
                break;
            case "updatePlaytime":
                setPlaytimeToRequestById(request);
                setMovieListToRequest(request);
                setRoomListToRequest(request);
                request.getRequestDispatcher("/admin/updatePlayTime.jsp").forward(request, response);
                break;
            default:
                setMovieListToRequest(request);
                setPlaytimeListToRequest(request);
                setTicketListToRequest(request);
                request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int movieId;
        String movieName;
        String movieType;
        int movieDuration;

        int playTimeId;
        String day;
        String hour;

        int roomId;

        String action = request.getParameter("action");
        switch (action) {
            case "addMovie":
                movieName = request.getParameter("name");
                movieType = request.getParameter("type");
                movieDuration = Integer.parseInt(request.getParameter("duration"));
                addMovie(movieName, movieType, movieDuration);
                request.setAttribute("message", "Thêm phim thành công!");
                request.getRequestDispatcher("/admin/addMovie.jsp").forward(request, response);
                break;
            case "updateMovie":
                movieId = Integer.parseInt(request.getParameter("movieId"));
                movieName = request.getParameter("name");
                movieType = request.getParameter("type");
                movieDuration = Integer.parseInt(request.getParameter("duration"));
                updateMovie(movieId, movieName, movieType, movieDuration);
                setMovieToRequestByMovieId(request);
                request.setAttribute("message", "Cập nhật phim thành công!");
                request.getRequestDispatcher("/admin/updateMovie.jsp").forward(request, response);
                break;
            case "deleteMovie":
                movieId = Integer.parseInt(request.getParameter("movieId"));
                if (!movieDAO.hasPlaytime(movieId)) {
                    movieDAO.delete(movieId);
                } else {
                    String failMessage = "Không thể xóa phim có lịch chiếu";
                    HttpSession session = request.getSession();
                    session.setAttribute("failMessage", failMessage);
                }
                response.sendRedirect("admin");
                break;
            case "addPlaytime":
                movieId = Integer.parseInt(request.getParameter("movieId"));
                roomId = Integer.parseInt(request.getParameter("roomId"));
                day = request.getParameter("day");
                hour = request.getParameter("hour");
                if (addPlaytime(movieId, roomId, day, hour)) {
                    request.setAttribute("message", "Thêm lịch chiếu thành công!");
                } else {
                    request.setAttribute("message", "Lịch chiếu trùng lặp, không thể thêm!");
                }
                setMovieListToRequest(request);
                setRoomListToRequest(request);
                request.getRequestDispatcher("/admin/addPlaytime.jsp").forward(request, response);
                break;
            case "updatePlaytime":
                playTimeId = Integer.parseInt(request.getParameter("playtimeId"));
                movieId = Integer.parseInt(request.getParameter("movieId"));
                roomId = Integer.parseInt(request.getParameter("roomId"));
                day = request.getParameter("day");
                hour = request.getParameter("hour");
                if (updatePlaytime(playTimeId, roomId, movieId, day, hour)) {
                    request.setAttribute("message", "Cập nhật thành công!");
                } else {
                    request.setAttribute("error", "Lịch chiếu trùng lặp, cập nhật thất bại!");
                }
                setPlaytimeToRequestById(request);
                setMovieListToRequest(request);
                setRoomListToRequest(request);
                request.getRequestDispatcher("/admin/updatePlayTime.jsp").forward(request, response);
                break;
            case "deletePlaytime":
                playTimeId = Integer.parseInt(request.getParameter("playtimeId"));
                if (playtimeDAO.isBooked(playTimeId)) {
                    String failMessage = "Không thể xóa lịch chiếu đã được đặt";
                    HttpSession session = request.getSession();
                    session.setAttribute("failMessage", failMessage);
                } else {
                    playtimeDAO.delete(playTimeId);
                }
                response.sendRedirect("admin");
                break;
        }
    }

    public void addMovie(String name, String type, int duration) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setType(type);
        movie.setDuration(duration);
        movieDAO.insert(movie);
    }

    public void updateMovie(int id, String name, String type, int duration) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setName(name);
        movie.setType(type);
        movie.setDuration(duration);
        movieDAO.update(movie);
    }

    public boolean addPlaytime(int movieId, int roomId, String day, String hour) {
        Playtime playtime = new Playtime();
        setupPlaytime(movieId, roomId, day, hour, playtime);
        if (playtimeDAO.isExisted(playtime)) {
            return false;
        }
        playtimeDAO.insert(playtime);
        return true;
    }

    public boolean updatePlaytime(int playTimeId, int roomId, int movieId, String playDay, String hour) {
        Playtime playtime = new Playtime();
        playtime.setId(playTimeId);
        setupPlaytime(movieId, roomId, playDay, hour, playtime);
        if (playtimeDAO.isExisted(playtime)) {
            return false;
        }
        playtimeDAO.update(playtime);
        return true;
    }

    private static void setupPlaytime(int movieId, int roomId, String day, String hour, Playtime playtime) {
        Movie movie = new Movie();
        Room room = new Room();
        movie.setId(movieId);
        room.setId(roomId);
        playtime.setMovie(movie);
        playtime.setRoom(room);
        playtime.setDay(LocalDate.parse(day));
        playtime.setHour(LocalTime.parse(hour));
    }

    private void setTicketListToRequest(HttpServletRequest request) {
        request.setAttribute("ticketList", ticketDAO.getAll());
    }

    private void setPlaytimeListToRequest(HttpServletRequest request) {
        request.setAttribute("playtimeList", playtimeDAO.getAll());
    }

    private void setPlaytimeToRequestById(HttpServletRequest request) {
        int playtimeId = Integer.parseInt(request.getParameter("playtimeId"));
        request.setAttribute("playtime", playtimeDAO.getById(playtimeId));
    }

    private void setMovieToRequestByMovieId(HttpServletRequest request) {
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        request.setAttribute("movie", movieDAO.getById(movieId));
    }

    private void setRoomListToRequest(HttpServletRequest request) {
        request.setAttribute("roomList", roomDAO.getAll());
    }

    private void setMovieListToRequest(HttpServletRequest request) {
        request.setAttribute("movieList", movieDAO.getAll());
    }
}


package org.example.cinema.controller;

import org.example.cinema.model.dao.MovieDAO;
import org.example.cinema.model.dao.MovieTimeDAO;
import org.example.cinema.model.dao.SeatDAO;
import org.example.cinema.model.dao.TicketDetailDAO;
import org.example.cinema.model.object.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private MovieTimeDAO movieTimeDAO;
    private SeatDAO seatDAO;
    private TicketDetailDAO ticketDetailDAO;

    @Override
    public void init() {
        movieDAO = new MovieDAO();
        movieTimeDAO = new MovieTimeDAO();
        seatDAO = new SeatDAO();
        ticketDetailDAO = new TicketDetailDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "incoming":
                List<MovieTime> movieTimes = movieTimeDAO.getNextWeek();
                List<Movie> movies = movieDAO.getNextWeek();
                for (Movie movie : movies) {
                    String imageName = movie.getName()
                            .toLowerCase()
                            .replaceAll("\\s+", "")
                            .replace(":", "")
                            .replace("&", "")
                            + ".jpg";
                    movie.setImageUrl(imageName);
                }
                request.setAttribute("movieTimes", movieTimes);
                request.setAttribute("movies", movies);
                request.getRequestDispatcher("/customer/cusview.jsp").forward(request, response);
                break;
            case "booking":
                String movieId = request.getParameter("movieId");
                Movie movie = movieDAO.getById(Integer.parseInt(movieId));
                movieTimes = movieTimeDAO.getByMovieId(Integer.parseInt(movieId));
                List<Seat> seatList = seatDAO.getAll();
                request.setAttribute("seatList", seatList);
                request.setAttribute("movie", movie);
                request.setAttribute("movieTimes", movieTimes);
                request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
                break;
            case "history":
                List<TicketDetail> ticketDetails = ticketDetailDAO.getByUsername(username);
                request.setAttribute("ticketDetails", ticketDetails);
                request.getRequestDispatcher("/customer/history.jsp").forward(request, response);
                break;
            default:
                movies = movieDAO.getThisWeek();
                movieTimes = movieTimeDAO.getThisWeek();
                for (Movie each : movies) {
                    String imageName = each.getName()
                            .toLowerCase()
                            .replaceAll("\\s+", "")
                            .replace(":", "")
                            .replace("&", "")
                            + ".jpg";
                    each.setImageUrl(imageName);
                }
                request.setAttribute("movies", movies);
                request.setAttribute("movieTimes", movieTimes);
                request.getRequestDispatcher("/customer/cusview.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "booking":
                String selectedSeats = request.getParameter("selectedSeats");
                String movieId = request.getParameter("movieId");
                Movie movie = movieDAO.getById(Integer.parseInt(movieId));
                List<Seat> seatList = seatDAO.getAll();
                List<MovieTime> movieTimes = movieTimeDAO.getByMovieId(Integer.parseInt(movieId));
                request.setAttribute("seatList", seatList);
                request.setAttribute("movie", movie);
                request.setAttribute("movieTimes", movieTimes);
                if (selectedSeats != null && !selectedSeats.isEmpty()) {
                    for (String item : selectedSeats.split(",")) {
                        seatDAO.book(item);
                    }
                    request.setAttribute("message", "Đặt vé thành công!");
                }
                request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
                break;
        }
    }
}

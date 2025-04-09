package org.example.cinema.controller;


import org.example.cinema.model.*;
import org.example.cinema.service.MovieDAO;
import org.example.cinema.service.PlayTimeDAO;
import org.example.cinema.service.SeatDAO;
import org.example.cinema.service.TicketDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private PlayTimeDAO playTimeDAO;
    private SeatDAO seatDAO;
    private TicketDAO ticketDAO;

    @Override
    public void init() {
        movieDAO = new MovieDAO();
        playTimeDAO = new PlayTimeDAO();
        seatDAO = new SeatDAO();
        ticketDAO = new TicketDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "incoming":
                List<MoviePlayTimeDTO> mptList = movieDAO.getNextWeek();
                setImageUrl(mptList);
                request.setAttribute("movies", mptList);
                request.getRequestDispatcher("/customer/view.jsp").forward(request, response);
                break;
            case "booking":
                int movieId = Integer.parseInt(request.getParameter("movieId"));
                Movie movie = movieDAO.getById(movieId);
                List<Playtime> playTimes = playTimeDAO.getByMovieId(movieId);
                List<Seat> seatList = seatDAO.getAll();
                request.setAttribute("seatList", seatList);
                request.setAttribute("movie", movie);
                request.setAttribute("playTimes", playTimes);
                request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
                break;
            case "history":
                List<TicketDetailDTO> ticketDetails = ticketDAO.getTicketDetailsByUsername(user.getUserName());
                request.setAttribute("ticketDetails", ticketDetails);
                request.getRequestDispatcher("/customer/history.jsp").forward(request, response);
                break;
            default:
                mptList = movieDAO.getThisWeek();
                setImageUrl(mptList);
                request.setAttribute("movies", mptList);
                request.getRequestDispatcher("/customer/view.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (action.equals("booking")) {
            String selectedSeats = request.getParameter("selectedSeats");
            String selectedSeatIds = request.getParameter("selectedSeatIds");
            int playTimeId = Integer.parseInt(request.getParameter("playTimeId"));
            int movieId = Integer.parseInt(request.getParameter("movieId"));
            for (String item : selectedSeats.split(",")) {
                seatDAO.update(item, "booked");
            }
            for (String item : selectedSeatIds.split(",")) {
                Ticket ticket = new Ticket();
                ticket.setUserId(user.getUserId());
                ticket.setPlaytimeId(playTimeId);
                ticket.setSeatId(Integer.parseInt(item));
                ticketDAO.add(ticket);
            }
            Movie foundMovie = movieDAO.getById(movieId);
            List<Playtime> playTimes = playTimeDAO.getByMovieId(movieId);
            List<Seat> seatList = seatDAO.getAll();
            request.setAttribute("seatList", seatList);
            request.setAttribute("movie", foundMovie);
            request.setAttribute("movieTimes", playTimes);
            request.setAttribute("message", "Đặt vé thành công!");
            request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
        }
    }

    private static void setImageUrl(List<MoviePlayTimeDTO> mptList) {
        for (MoviePlayTimeDTO item : mptList) {
            String imageName = item.getMovieName()
                    .toLowerCase()
                    .replaceAll("\\s+", "")
                    .replace(":", "")
                    .replace("&", "")
                    + ".jpg";
            item.setImageUrl(imageName);
        }
    }
}

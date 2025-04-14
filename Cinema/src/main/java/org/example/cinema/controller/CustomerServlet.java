package org.example.cinema.controller;


import org.example.cinema.model.*;
import org.example.cinema.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private final MovieDAO movieDAO = new MovieDAO();
    private final PlaytimeDAO playtimeDAO = new PlaytimeDAO();
    private final TicketDAO ticketDAO = new TicketDAO();
    private final SeatDAO seatDAO = new SeatDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int playtimeId;
        List<Playtime> playtimeList;
        String movieName;

        List<Ticket> ticketList;

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "incoming":
                request.setAttribute("movieList", movieDAO.getNextWeek());
                request.getRequestDispatcher("/customer/view.jsp").forward(request, response);
                break;
            case "checkPlaytime":
                movieName = request.getParameter("movieName");
                playtimeList = playtimeDAO.getByMovieName(movieName);
                request.setAttribute("playtimeList", playtimeList);
                request.setAttribute("now", LocalDateTime.now());
                request.getRequestDispatcher("/customer/playtime.jsp").forward(request, response);
                break;
            case "booking":
                playtimeId = Integer.parseInt(request.getParameter("playtimeId"));
                ticketList = ticketDAO.getByPlaytime(playtimeId);
                List<Seat> bookedSeatList = getSeatListFromTicketList(ticketList);
                request.setAttribute("bookedSeatList", bookedSeatList);
                setAllSeatToRequest(request);
                request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
                break;
            case "history":
                ticketList = ticketDAO.getByUser(user.getId());
                request.setAttribute("ticketList", ticketList);
                request.getRequestDispatcher("/customer/history.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("movieList", movieDAO.getThisWeek());
                request.getRequestDispatcher("/customer/view.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Ticket> ticketList;

        if (action.equals("booking")) {
            String selectedSeatList = request.getParameter("selectedSeatList");
            int playtimeId = Integer.parseInt(request.getParameter("playtimeId"));
            String[] seatIdList = selectedSeatList.split(",");
            for (String seatId : seatIdList) {
                Ticket ticket = new Ticket();

                Seat seat = new Seat();
                seat.setId(Integer.parseInt(seatId));
                ticket.setSeat(seat);

                ticket.setUser(user);

                Playtime playtime = new Playtime();
                playtime.setId(playtimeId);
                ticket.setPlaytime(playtime);

                ticketDAO.insert(ticket);
            }
            ticketList = ticketDAO.getByPlaytime(playtimeId);
            setAllSeatToRequest(request);
            List<Seat> bookedSeatList = getSeatListFromTicketList(ticketList);
            request.setAttribute("bookedSeatList", bookedSeatList);
            request.setAttribute("message", "Đặt vé thành công!");
            request.getRequestDispatcher("/customer/booking.jsp").forward(request, response);
        }
    }

    private void setAllSeatToRequest(HttpServletRequest request) {
        request.setAttribute("seatList", seatDAO.getAll());
    }

    private List<Seat> getSeatListFromTicketList(List<Ticket> ticketList) {
        List<Seat> bookedSeatList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            for (Seat seat : seatDAO.getAll()) {
                if (ticket.getSeat().getId() == seat.getId()) {
                    bookedSeatList.add(seat);
                }
            }
        }
        return bookedSeatList;
    }
}
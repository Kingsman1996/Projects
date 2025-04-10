package org.example.cinema.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketDetailDTO {
    private int ticketId;
    private String username;
    private String seatCode;
    private LocalDate playDay;
    private LocalTime hour;
    private String movieName;

    public TicketDetailDTO() {
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public LocalDate getPlayDay() {
        return playDay;
    }

    public void setPlayDay(LocalDate playDay) {
        this.playDay = playDay;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}

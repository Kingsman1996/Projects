package org.example.cinema.model;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private User user;
    private Playtime playtime;
    private Seat seat;
    private LocalDateTime bookingTime;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Playtime getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Playtime playtime) {
        this.playtime = playtime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}


package org.example.cinema.model;

public class Ticket {
    private int id;
    private int userId;
    private int seatId;
    private int playtimeId;

    public Ticket() {
    }

    public int getPlaytimeId() {
        return playtimeId;
    }

    public void setPlaytimeId(int playtimeId) {
        this.playtimeId = playtimeId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


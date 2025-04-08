package org.example.cinema.model.object;

public class Ticket {
    private int id;
    private int userId;
    private int seatId;
    private int movieTimeId;

    public Ticket() {
    }

    public Ticket(int id, int userId, int seatId, int movieTimeId) {
        this.id = id;
        this.userId = userId;
        this.seatId = seatId;
        this.movieTimeId = movieTimeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getMovieTimeId() {
        return movieTimeId;
    }

    public void setMovieTimeId(int movieTimeId) {
        this.movieTimeId = movieTimeId;
    }
}


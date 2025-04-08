package org.example.cinema.model.object;

import java.time.LocalDate;
import java.time.LocalTime;

public class MovieTime {
    private int id;
    private int movieId;
    private LocalDate showDate;
    private LocalTime showTime;

    public MovieTime() {
    }

    public MovieTime(int id, int movieId, LocalDate showDate, LocalTime showTime) {
        this.id = id;
        this.movieId = movieId;
        this.showDate = showDate;
        this.showTime = showTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }
}

package org.example.cinema.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Playtime {
    private int playTimeId;
    private int movieId;
    private LocalDate playDay;
    private LocalTime hour;

    public Playtime() {
    }

    public int getPlayTimeId() {
        return playTimeId;
    }

    public void setPlayTimeId(int playTimeId) {
        this.playTimeId = playTimeId;
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

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}

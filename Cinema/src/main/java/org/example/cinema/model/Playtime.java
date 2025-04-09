package org.example.cinema.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Playtime {
    private int playTimeId;
    private int movieId;
    private LocalDate day;
    private LocalTime time;

    public Playtime() {
    }

    public int getPlayTimeId() {
        return playTimeId;
    }

    public void setPlayTimeId(int playTimeId) {
        this.playTimeId = playTimeId;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}

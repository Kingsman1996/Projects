package org.example.cinema.model;

import java.util.List;

public class MoviePlayTimeDTO {
    private int movieId;
    private String movieName;
    private String movieType;
    private int movieDuration;
    private List<Playtime> playTimes;
    private String imageUrl;


    public MoviePlayTimeDTO() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public int getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    public List<Playtime> getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(List<Playtime> playTimes) {
        this.playTimes = playTimes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

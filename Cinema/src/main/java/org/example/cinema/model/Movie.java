package org.example.cinema.model;

public class Movie {
    private int id;
    private String name;
    private String type;
    private int duration;
    private String imageUrl;

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void convertNameToImageUrl() {
        setImageUrl(name.toLowerCase().replaceAll("\\s+", "").replace(":", "").replace("&", "") + ".jpg");
    }
}

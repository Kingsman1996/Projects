package org.example.cinema.model.object;

public class Seat {
    private int id;
    private String code;
    private String status;

    public Seat() {
    }

    public Seat(int id, String code, String status) {
        this.id = id;
        this.code = code;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

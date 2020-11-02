package com.example.galgeleg;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Score {
    private String date;
    private int points;

    public Score(String date, int points) {
        this.date = date;
        this.points = points;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

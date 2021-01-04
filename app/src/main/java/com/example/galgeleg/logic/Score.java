package com.example.galgeleg.logic;


public class Score {
    private String date;
    private String name;
    private String word;
    private int points;

    public Score(String name,String word,String date, int points) {
        this.name = name;
        this.word = word;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}

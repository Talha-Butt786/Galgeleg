package com.example.galgeleg.logic;

import java.util.ArrayList;
import java.util.List;

public class ScoreList {
    private List<Score> scores;

    public ScoreList() {
        this.scores = new ArrayList<>();
    }


    public List<Score> getScoreslist() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public void addscore(Score score) {
        scores.add(score);
    }

}

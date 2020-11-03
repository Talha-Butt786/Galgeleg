package com.example.galgeleg.State_logic;

import java.util.ArrayList;
import java.util.List;

public class HighScoreList {
    private List<Score> scores;

    public HighScoreList() {
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

    public int getHighestScore() {
        if (scores.size() == 0) {
            return 0;
        } else {
            int score = 0;
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i).getPoints() > score) score = scores.get(i).getPoints();
            }
            return score;
        }

    }

}

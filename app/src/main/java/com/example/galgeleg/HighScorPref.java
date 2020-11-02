package com.example.galgeleg;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class HighScorPref implements UpdatePref{

        public static final String HIGHEST_SCORE = "highscore";
        public static HighScorPref highScorPref;
        private HighScorPref() {};


        public static HighScorPref getInstance(){
            if(highScorPref==null){
               highScorPref = new HighScorPref();
            }
            return highScorPref;
        }

    public void saveHighestScore (Context context, int highestscore){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt(HIGHEST_SCORE,highestscore).apply();
    }
    public int getHighestScore(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(HIGHEST_SCORE,0);
    }


    @Override
    public void update() {

    }


}

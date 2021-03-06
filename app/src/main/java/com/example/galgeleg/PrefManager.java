package com.example.galgeleg;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.galgeleg.logic.ScoreList;
import com.google.gson.Gson;

public class PrefManager {

    public static final String LIST_SCORE = "sharedprefs";  //gemmes liste over alle scores
    public static final String HIGHEST_SCORE = "highscore"; // gemmes high score
    public static PrefManager prefManager;
    private PrefManager() {};


    public static PrefManager getInstance(){
        if(prefManager==null){
            prefManager = new PrefManager();
        }
        return prefManager;
    }

    public void saveScoreList (Context context, ScoreList highscores){
        Gson gson = new Gson();
        String jsonobj = gson.toJson(highscores);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit(); // to save the variable
        editor.putString(LIST_SCORE,jsonobj);
        editor.apply();
    }

    public ScoreList getScoresfromPref (Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonobj = preferences.getString(LIST_SCORE,"{\"scores\":[{\"name\":\"noname\",\"word\":\"noword\",\"date\":\"nodate\",\"points\":0}]}");

         Gson gson = new Gson();
         return gson.fromJson(jsonobj, ScoreList.class);
    }

    public void clearData (Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().remove(LIST_SCORE).apply();
        preferences.edit().remove(HIGHEST_SCORE).apply();
    }

    public void saveHighestScore (Context context, int highestscore){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt(HIGHEST_SCORE,highestscore).apply();
    }
    public int getHighestScore(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(HIGHEST_SCORE,0);
    }
}

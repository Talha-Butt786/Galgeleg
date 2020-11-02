package com.example.galgeleg;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.List;

public class PrefManager {

    public static final String LIST_SCORE = "sharedprefs";
    public static final String HIGHEST_SCORE = "highscore";
    public static PrefManager prefManager;
    private PrefManager() {};


    public static PrefManager getInstance(){
        if(prefManager==null){
            prefManager = new PrefManager();
        }
        return prefManager;
    }

    public void saveScoreList (Context context, HighScoreList highscores){
        Gson gson = new Gson();
        String jsonobj = gson.toJson(highscores);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        //SharedPreferences.Editor editor = preferences.edit(); // to save the variable
        //editor.putString(LIST_SCORE,jsonobj);
        preferences.edit().putString(LIST_SCORE,jsonobj).apply();
        //editor.apply();
    }

    public HighScoreList getScoresfromPref (Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonobj = preferences.getString(LIST_SCORE,"{\"scores\":[{\"date\":\"nodate\",\"points\":0}]}");

         Gson gson = new Gson();
         return gson.fromJson(jsonobj,HighScoreList.class);
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

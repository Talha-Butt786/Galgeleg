package com.example.galgeleg;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class ScoreListPref implements UpdatePref {

    public static final String LIST_SCORE = "sharedprefs";
    public static ScoreListPref prefManager;
    private ScoreListPref() {};


    public static ScoreListPref getInstance(){
        if(prefManager==null){
            prefManager = new ScoreListPref();
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
    @Override
    public void update() {

    }
}

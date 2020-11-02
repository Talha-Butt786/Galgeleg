package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ScoreListActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorelist);
        PrefManager prefManager = PrefManager.getInstance();
        HighScoreList score = prefManager.getScoresfromPref(this);
        listView = findViewById(R.id.listview);
        ScoreAdapter scoreAdapter = new ScoreAdapter(this,score);
        listView.setAdapter(scoreAdapter);
    }
}
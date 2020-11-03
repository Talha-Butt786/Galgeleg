package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.galgeleg.State_logic.HighScoreList;

public class ScoreListActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorelist);
        home = findViewById(R.id.homeidd);
        home.setOnClickListener(this);
        PrefManager prefManager = PrefManager.getInstance();
        HighScoreList score = prefManager.getScoresfromPref(this);
        listView = findViewById(R.id.listview);
        ScoreAdapter scoreAdapter = new ScoreAdapter(this,score);
        listView.setAdapter(scoreAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
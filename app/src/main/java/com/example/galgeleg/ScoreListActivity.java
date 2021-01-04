package com.example.galgeleg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.galgeleg.logic.Score;
import com.example.galgeleg.logic.ScoreList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreListActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    Button home,clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorelist);
        home = findViewById(R.id.homeidd);
        home.setOnClickListener(this);
        clear = findViewById(R.id.clearList_ID);
        clear.setOnClickListener(this);
        getAllScores();
        
    }

    @Override
    public void onClick(View v) {
        if (v == home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(v == clear){
            AlertDialog.Builder dialogbox = new AlertDialog.Builder(this);
            dialogbox.setMessage("Confirm that you want to clear the History?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PrefManager.getInstance().clearData(getApplicationContext());
                            getAllScores();
                        }
                    })
                    .setNegativeButton("NO",null)
                    .show();
        }
    }

    public void getAllScores(){
        PrefManager prefManager = PrefManager.getInstance();
        ScoreList score = prefManager.getScoresfromPref(this);
        ScoreList scoreList = new ScoreList();
        scoreList.addscore(new Score("Talha","udkast","23-01-2020",1234));
        recyclerView = findViewById(R.id.scorelist_id);
        Collections.sort(score.getScoreslist(), new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return Integer.valueOf(o2.getPoints()).compareTo(o1.getPoints());
            }
        });
        ScoreAdapter scoreAdapter = new ScoreAdapter(this,score);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(scoreAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
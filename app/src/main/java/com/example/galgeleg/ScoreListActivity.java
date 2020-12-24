package com.example.galgeleg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.galgeleg.logic.ScoreList;

public class ScoreListActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
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
        listView = findViewById(R.id.listview);
        ScoreAdapter scoreAdapter = new ScoreAdapter(this,score);
        listView.setAdapter(scoreAdapter);
    }
}
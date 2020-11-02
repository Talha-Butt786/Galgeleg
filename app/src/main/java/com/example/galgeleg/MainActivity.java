package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button startbutton, scoreliste;
    TextView getWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //PrefManager.getInstance().clearData(getApplicationContext());  //to clear the list and highscore
        setContentView(R.layout.activity_main);
        startbutton =findViewById(R.id.startbutton);
        startbutton.setOnClickListener(this);
        scoreliste = findViewById(R.id.scoreliste);
        scoreliste.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == startbutton){
            Intent spil = new Intent(this, SpilActivity.class);
            startActivity(spil);
        }
        if(v == scoreliste){
            Intent list = new Intent(this, ScoreListActivity.class);
            startActivity(list);
        }

    }
}
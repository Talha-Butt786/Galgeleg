package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jinatonic.confetti.CommonConfetti;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button startbutton, scoreliste,help;
    TextView getWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //PrefManager.getInstance().clearData(this);
        setContentView(R.layout.activity_main);
        startbutton =findViewById(R.id.startbutton);
        startbutton.getBackground().setColorFilter(getResources().getColor(R.color.colorstartbutton), PorterDuff.Mode.MULTIPLY);
        startbutton.setOnClickListener(this);
        scoreliste = findViewById(R.id.scoreliste);
        scoreliste.setOnClickListener(this);
        help = findViewById(R.id.hjælp);
        help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == startbutton){
            Intent load = new Intent(this, LoadingActivity.class);
            startActivity(load);
        }
        if(v == scoreliste){
            Intent list = new Intent(this, ScoreListActivity.class);
            startActivity(list);
        }
        if(v==help){
            Toast.makeText(this,"not implemented",Toast.LENGTH_SHORT).show();
        }

    }
}
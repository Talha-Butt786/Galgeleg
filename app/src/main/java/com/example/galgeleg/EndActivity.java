package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class EndActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView winLose;
    Button prøveigen, home;
    TextView highscore,newhighscore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        highscore = findViewById(R.id.highscoreid1);
        newhighscore = findViewById(R.id.yournewid);
        prøveigen = findViewById(R.id.prøvid);
        prøveigen.setOnClickListener(this);
        prøveigen.getBackground().setColorFilter(getResources().getColor(R.color.colorprøveigen), PorterDuff.Mode.MULTIPLY);
        home = findViewById(R.id.homeid);
        home.setOnClickListener(this);
        home.getBackground().setColorFilter(getResources().getColor(R.color.colorgohome), PorterDuff.Mode.MULTIPLY);

        //todo update highscore
//        if(getIntent().getIntExtra("newhighscore",0)==0){
//            highscore.setText("");
//            newhighscore.setText("");
//        }else{
//            int newhighscore1 = getIntent().getIntExtra("newhighscore",0);
//            highscore.setText(newhighscore1);
//        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onClick(View v) {
        if(v == prøveigen){
            this.finish();
            Intent spil = new Intent(this, SpilActivity.class);
            startActivity(spil);
        }
        if(v == home){
            this.finish();
            Intent spil = new Intent(this, MainActivity.class);
            startActivity(spil);
        }
    }


}
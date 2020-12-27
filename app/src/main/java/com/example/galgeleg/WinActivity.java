package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class WinActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView winLose;
    Button prøveigen, home;
    TextView highscore,newhighscore,antalforsøge;
    FrameLayout frameLayout;
    boolean multiplayer = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);


        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}

        multiplayer = getIntent().getBooleanExtra("multiplayer",false);
        frameLayout = findViewById(R.id.lottie_Frame);
       // CommonConfetti.rainingConfetti(konfettiView,new int[]{Color.GREEN,Color.YELLOW,Color.BLUE}).infinite();
        final KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA,Color.BLUE)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 700f, -50f, -50f)
                .streamFor(300, 2000L);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.victory);
        mediaPlayer.start();
        highscore = findViewById(R.id.highscoreid1);
        newhighscore = findViewById(R.id.yournewid);
        prøveigen = findViewById(R.id.prøvid);
        prøveigen.setOnClickListener(this);
        prøveigen.getBackground().setColorFilter(getResources().getColor(R.color.colorprøveigen), PorterDuff.Mode.MULTIPLY);
        home = findViewById(R.id.homeid);
        home.setOnClickListener(this);
        home.getBackground().setColorFilter(getResources().getColor(R.color.colorgohome), PorterDuff.Mode.MULTIPLY);
        antalforsøge = findViewById(R.id.nrforsøge);


        
        int nyhighscore = getIntent().getIntExtra("newhighscore",0);
        int nrforsøg = getIntent().getIntExtra("nrforsøg",0);
        int totalpoints = getIntent().getIntExtra("totalpoints",0);
        String strForsøg =  String.valueOf(nrforsøg);
        String total = String.valueOf(totalpoints);
        antalforsøge.setText("Antal fosøg: "+strForsøg);
        if(nyhighscore==0){  // hvis ikke var new high score
            highscore.setText("you scored: " + total);
            newhighscore.setText("you won the game");
        }else{
           int newhighscore1 = getIntent().getIntExtra("newhighscore",0);
            highscore.setText(String.valueOf(newhighscore1));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onClick(View v) {
        if(v == prøveigen){
            this.finish();
            if(multiplayer){
                Intent spil = new Intent(this, ListActivity.class);
                startActivity(spil);
                finish();
            }else {
                Intent spil = new Intent(this, LoadingActivity.class);
                startActivity(spil);
                finish();
            }
        }
        if(v == home){
            this.finish();
            Intent spil = new Intent(this, MainActivity.class);
            startActivity(spil);
            finish();
        }
    }


}
package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class EndActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    ImageView winLose;
    Button prøveigen;
    TextView msg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        String rigtigOrd = getIntent().getStringExtra("rigtig ord");
        winLose = (ImageView) findViewById(R.id.win_lose);
        msg = (TextView) findViewById(R.id.finishMsg);
        prøveigen = (Button) findViewById(R.id.PrøveIgen);
        boolean gameWon = getIntent().getBooleanExtra("gamewon",false);
        showImage(gameWon,rigtigOrd);
        prøveigen.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onClick(View v) {
        if(v == prøveigen){
            this.finish();
            Intent spil = new Intent(this,SpilActivity.class);
            startActivity(spil);
        }
    }

    public void showImage(boolean gameWon, String rigtiOrd){
        if(gameWon){
            winLose.setImageResource(R.drawable.won);
            msg.setText("Den rigtig Ord er " + rigtiOrd);
        }else {
            winLose.setImageResource(R.drawable.gameover);
            msg.setText("Den rigtig Ord var "+ rigtiOrd);
        }
    }

    //todo correct hardware back button
    //taken from stack overflow but doesn't works yet.
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
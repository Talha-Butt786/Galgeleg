package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoseActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView gohome,prøveigen;
    TextView correctword, bestscore;
    boolean multiplayer = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}
        gohome = findViewById(R.id.home_img);
        prøveigen = findViewById(R.id.tryagain_img);
        multiplayer = getIntent().getBooleanExtra("multiplayer2",false);
        //gohome.getBackground().setColorFilter(getResources().getColor(R.color.colorgohome), PorterDuff.Mode.MULTIPLY);
        //prøveigen.getBackground().setColorFilter(getResources().getColor(R.color.colorprøveigen), PorterDuff.Mode.MULTIPLY);
        bestscore = findViewById(R.id.best_score);
        bestscore.setText(String.valueOf(PrefManager.getInstance().getHighestScore(this)));
        correctword = findViewById(R.id.correctwordid);
        correctword.setText(getIntent().getStringExtra("rigtig ord"));
        gohome.setOnClickListener(this);
        prøveigen.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==gohome){
            this.finish();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(v==prøveigen){
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
    }
}
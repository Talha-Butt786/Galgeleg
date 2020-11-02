package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoseActivity extends AppCompatActivity implements View.OnClickListener {

    Button gohome,prøveigen;
    TextView correctword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}
        gohome = findViewById(R.id.gohomeid);
        prøveigen = findViewById(R.id.prøveigen);
        gohome.getBackground().setColorFilter(getResources().getColor(R.color.colorgohome), PorterDuff.Mode.MULTIPLY);
        prøveigen.getBackground().setColorFilter(getResources().getColor(R.color.colorprøveigen), PorterDuff.Mode.MULTIPLY);
        correctword = findViewById(R.id.correctwordid);
        correctword.setText(getIntent().getStringExtra("rigtig ord"));
        gohome.setOnClickListener(this);
        prøveigen.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==gohome){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(v==prøveigen){
            Intent intent = new Intent(this,SpilActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
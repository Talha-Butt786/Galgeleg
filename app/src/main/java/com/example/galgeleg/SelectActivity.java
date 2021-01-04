package com.example.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView single,multiplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}

        single = findViewById(R.id.single_id);
        multiplayer = findViewById(R.id.multi_id);

        single.setOnClickListener(this);
        multiplayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==single){
            Intent spil = new Intent(this, LoadingActivity.class);
            startActivity(spil);
        }
        if(v==multiplayer){
            Intent list = new Intent(this,ListActivity.class);
            startActivity(list);
        }
    }
}

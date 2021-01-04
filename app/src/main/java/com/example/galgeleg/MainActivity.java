package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView startbutton, scoreliste,help;
    TextView getWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //PrefManager.getInstance().clearData(this);
        setContentView(R.layout.activity_main);
        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}
        startbutton =findViewById(R.id.single_id);
        //startbutton.getBackground().setColorFilter(getResources().getColor(R.color.colorstartbutton), PorterDuff.Mode.MULTIPLY);
        startbutton.setOnClickListener(this);
        scoreliste = findViewById(R.id.multi_id);
        scoreliste.setOnClickListener(this);
        help = findViewById(R.id.hjælp);
        help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == startbutton){
            Intent select = new Intent(this, SelectActivity.class);
            startActivity(select);
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
package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button startbutton;
    TextView getWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbutton =findViewById(R.id.startbutton);
        startbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == startbutton){
            Intent spil = new Intent(this,spil.class);
            startActivity(spil);
        }
    }
}
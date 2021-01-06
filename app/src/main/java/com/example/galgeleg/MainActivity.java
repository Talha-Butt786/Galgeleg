package com.example.galgeleg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton startbutton, scoreliste,rules;
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
        rules = findViewById(R.id.rules);
        rules.setOnClickListener(this);
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
        if(v==rules){
            AlertDialog.Builder dialogbox = new AlertDialog.Builder(this);
            dialogbox.setMessage("* Just click on any alphabet to guess\n* Once clicked, you can't click again\n* Timer is set on once the game is ready" +
                    "\n* Longer the word to guess bigger the bonus you can get\n* Total points are given by evaluating total time points + bonus points" +
                    "\n* One can maximum get 3000 points");
            dialogbox.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialogbox.show();
        }

    }
}
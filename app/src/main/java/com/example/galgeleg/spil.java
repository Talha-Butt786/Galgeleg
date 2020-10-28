package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class spil extends AppCompatActivity implements View.OnClickListener {
    GridLayout gridLayout;
    TextView skjultOrd;
    GalgelegLogik galgelegLogik;
    List<Button> buttons;
    HashMap<Integer,String> danskOrd;
    public spil(){galgelegLogik = new GalgelegLogik();}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);
        danskOrd = new HashMap<>();
        danskOrd.put(123,"Æ");
        danskOrd.put(124,"Ø");
        danskOrd.put(125,"Å");
        buttons = new ArrayList<>();
        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}


        gridLayout = findViewById(R.id.grid);
        gridLayout.setRowCount(3);
        gridLayout.setColumnCount(10);


        addButtons();
        String ord = galgelegLogik.getSynligtOrd();
        skjultOrd = findViewById(R.id.SkjultOrd);
        skjultOrd.setText(ord);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    if(id>=97 && id<=122){
                        String IndtastOrd = String.valueOf(Character.toChars(id));
                        galgelegLogik.gætBogstav(IndtastOrd);
                        String ord = galgelegLogik.getSynligtOrd();
                        skjultOrd = findViewById(R.id.SkjultOrd);
                        skjultOrd.setText(ord);
                    }
                    else if(id>=123 && id<=125){
                        String IndtastOrd = danskOrd.get(id);
                        galgelegLogik.gætBogstav(IndtastOrd);
                        String ord = galgelegLogik.getSynligtOrd();
                        skjultOrd = findViewById(R.id.SkjultOrd);
                        skjultOrd.setText(ord);
                    }
                    //https://stackoverflow.com/questions/13842447/android-set-button-background-programmatically
                    v.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryLight), PorterDuff.Mode.MULTIPLY);
                    v.setClickable(false);
                }
            });
        }



        //assert Indtastbogstav != null;
        //galgelegLogik.gætBogstav(Indtastbogstav);
        galgelegLogik.logStatus();

    }
    
    public void addButtons(){
        int alphaAscii = 97;
        for (int i = 0; i < 29; i++) {
            System.out.println("row nr: "+gridLayout.getRowCount());
            Button button = new Button(this);
            if(i<26){
                button.setText(String.valueOf(Character.toChars(alphaAscii)));
            }else {
                button.setText(danskOrd.get(alphaAscii));
            }
            button.setWidth(20);
            button.setHeight(30);
            button.setId(alphaAscii);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.height = 180;
            layoutParams.width = 107;
            button.setLayoutParams(layoutParams);

            //https://stackoverflow.com/questions/13842447/android-set-button-background-programmatically
            button.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
            buttons.add(button);
            gridLayout.addView(button);
            alphaAscii++;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        System.out.println("id: "+ id);
    }
}
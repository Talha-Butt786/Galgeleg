package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class spil extends AppCompatActivity implements View.OnClickListener {
    GridLayout gridLayout;
    List<Button> buttons;
    HashMap<Integer,String> danskOrd;
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
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

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
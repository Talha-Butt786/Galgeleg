package com.example.galgeleg;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class SpilActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView forkert1;
    TextView skjultord, sidsteord, gætord;
    EditText indtastOrd;
    Button okbutton;
    boolean gamefinished;
    GalgelegLogik galgelegLogik;

    public SpilActivity(){
        galgelegLogik = new GalgelegLogik();
    }


    public boolean isHaveWon() {
       return galgelegLogik.erSpilletVundet();
    }

    public boolean isGamefinished() {
        return gamefinished = galgelegLogik.erSpilletSlut();
    }

    int antalforket;
    int ordlengde;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        forkert1 = findViewById(R.id.listeelem_billede);
        skjultord = findViewById(R.id.skjultOrd);
        indtastOrd = findViewById(R.id.indtastOrdet);
        indtastOrd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        okbutton = findViewById(R.id.ok_button);
        gætord = findViewById(R.id.gætord);
        sidsteord = findViewById(R.id.sidsteOrd);
        skjultord.setText(galgelegLogik.getSynligtOrd());
        okbutton.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public void onClick(View v) {
        if(isGamefinished()){
            Intent spil = new Intent(this,SpilActivity.class);
            startActivity(spil);
        }
        else
            updatespil(v);
    }

    public void updatespil(View view) {
        List<String> brugteBogstaver = new ArrayList<>();
        brugteBogstaver=galgelegLogik.getBrugteBogstaver();
            String ord = indtastOrd.getText().toString();
            String brugtOrd = "Ord er allerede brugt!";
        for (int i = 0; i < brugteBogstaver.size() ; i++) {
            if(brugteBogstaver.get(i).equals(ord)){
                sidsteord.setText(brugtOrd);
                return;
            }
        }
            galgelegLogik.gætBogstav(ord);
            String msg = "Tillykke du gættet rigtig";
            String igen = "prøve igen";
            if (galgelegLogik.erSidsteBogstavKorrekt()) {
                sidsteord.setText(msg);
            } else {
                sidsteord.setText(igen);
            }

            skjultord.setText(galgelegLogik.getSynligtOrd());
            antalforket = galgelegLogik.getAntalForkerteBogstaver();
            System.out.println("num: " + antalforket);
            changePicsAccordingly();
            galgelegLogik.logStatus();

        if(isGamefinished()) {
            String rigtigOrd = galgelegLogik.getOrdet();
            okbutton.setText("prøve igen");
            indtastOrd.setEnabled(false);
            Intent End = new Intent(this, EndActivity.class);
            End.putExtra("gamewon",isHaveWon());
            End.putExtra("rigtig ord",rigtigOrd);
            startActivity(End);
//            if(isHaveWon()){
//                forkert1.setImageResource(R.drawable.won);
//                gætord.setText("tillykke du gættet rigtig ord: ");
//            }else {
//                forkert1.setImageResource(R.drawable.gameover);
//                gætord.setText("øv du tabt, den rigtig ord er: ");
//                skjultord.setText(galgelegLogik.getOrdet());
//            }
//
        }
    }

    public void changePicsAccordingly (){

        switch (antalforket){
            case 1:
                forkert1.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                forkert1.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                forkert1.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                forkert1.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                forkert1.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                forkert1.setImageResource(R.drawable.forkert6);
                break;
        }

    }


}

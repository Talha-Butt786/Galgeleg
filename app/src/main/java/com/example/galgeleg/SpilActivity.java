package com.example.galgeleg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.galgeleg.logic.Statelogic.*;
import com.example.galgeleg.logic.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SpilActivity extends AppCompatActivity implements View.OnClickListener {
    Button home,help;
    TextView skjultOrd, highestscore;
    GalgeSpilContext galgelegLogik;
    ImageView wrongimage;
    HashMap<Integer,String> danskOrd;
    List<Button> buttonList;
    Executor backThread;
    Handler uiThread;
    Chronometer chronometer;
    long time1,time2;
    ScoreList scorelist;
    String name;
    AlertDialog alertDialog;
    boolean multiplayer = false;
    public SpilActivity(){
        galgelegLogik = new GalgeSpilContext();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spil);
        skjultOrd = findViewById(R.id.SkjultOrd);
        highestscore = findViewById(R.id.highscoreid1);
        wrongimage = findViewById(R.id.wrongimage);
        chronometer = findViewById(R.id.tid);
        home = findViewById(R.id.hjem);
        help = findViewById(R.id.help);
        home.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        help.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        home.setOnClickListener(this);
        help.setOnClickListener(this);
        danskOrd = new HashMap<>();
        danskOrd.put(123,"æ");
        danskOrd.put(124,"ø");
        danskOrd.put(125,"å");
        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}
        startdialogbox();
        String highscore = Integer.toString(PrefManager.getInstance().getHighestScore(getApplicationContext()));
        highestscore.setText(highscore);
        //hentOnlineOrd();  //initialisere spillet samtidlig hentes ord online  todo remove this
        galgelegLogik.setMuligeOrd(getIntent().getStringArrayListExtra("ord_list"));
        if(getIntent().getStringExtra("given_word")!=null){
            multiplayer = true;
            galgelegLogik.setGivenWord(getIntent().getStringExtra("given_word"));
        }
        galgelegLogik.startSpil();
        skjultOrd.setText(galgelegLogik.getSynligtOrd());
        addButtons();
        //startWatch();
        galgelegLogik.logStatus();
    }
    public void startdialogbox(){
            AlertDialog.Builder dialogbox = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();  //todo remove this
            dialogbox.setTitle("Your Name");
            dialogbox.setMessage("Please type your name");
            final EditText editText = new EditText(this);
            dialogbox.setView(editText);
            dialogbox.setCancelable(false);
            dialogbox.setPositiveButton("submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    name = editText.getText().toString();
                    startWatch();
                }
            });
            alertDialog = dialogbox.create();
            alertDialog.show();
            alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.select_button);
    }


    public void addButtons(){
        int alphaAscii = 97;  //tilføjes bogstaver med ascii.
        buttonList = new ArrayList<>();

        Display display = getWindowManager().getDefaultDisplay();
        int widtsize = display.getWidth();
        int heightsize=display.getHeight();
        for (int i = 0; i < 30; i++) {
            Button button = new Button(this);
            if(i<26){
                button.setText(String.valueOf(Character.toChars(alphaAscii)));
            }else {
                button.setText(danskOrd.get(alphaAscii)); // hentes danske ord
            }

            if(i>=11&&i<=20){
                button.setTextSize(14);
                button.setId(alphaAscii);
                LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,2);
                LayoutParams.setMargins(5,10,5,10);
                button.setLayoutParams(LayoutParams);
            }
            else if(i>=21&&i<=29){
                button.setTextSize(14);
                button.setId(alphaAscii);
                LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,2);
                LayoutParams.setMargins(5,10,5,10);
                button.setLayoutParams(LayoutParams);

            }else {
                button.setTextSize(14);
                button.setId(alphaAscii);
                LinearLayout.LayoutParams LayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,2);
                LayoutParams.setMargins(5,10,5,10);
                button.setLayoutParams(LayoutParams);
            }

            button.setOnClickListener(this);
            //https://stackoverflow.com/questions/13842447/android-set-button-background-programmatically
            //button.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            button.setBackground(ContextCompat.getDrawable(this,R.drawable.button_home));
            button.setTextColor(Color.WHITE);
            buttonList.add(button);
            alphaAscii++;
        }
        buttonList.get(29).setVisibility(View.INVISIBLE);   //ikke skal vises tom button.
        LinearLayout linearLayout = findViewById(R.id.tast);
        int buttonIndex = 0;
        int nrbuttons = 11;
        for (int i = 0; i < 3; i++) {
            LinearLayout row = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            row.setLayoutParams(layoutParams);


            for (int j = 0; j < nrbuttons; j++) {
                if(buttonList.size()==buttonIndex+1){
                    break;
                }
                Button button = buttonList.get(buttonIndex);
                //button.getLayoutParams().width=50;
                row.addView(button);
                buttonIndex++;
                if(nrbuttons-j == 1){
                    nrbuttons--;
                }
            }
            linearLayout.addView(row);
        }
    }

    public void changePicsAccordingly (int wrongs){

        switch (wrongs){
            case 1:
                wrongimage.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                wrongimage.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                wrongimage.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                wrongimage.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                wrongimage.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                wrongimage.setImageResource(R.drawable.forkert6);
                break;
            case 7:
                wrongimage.setImageResource(R.drawable.forkert7);
                break;
        }

    }

    public void hentOnlineOrd (){   //todo remove this
        backThread.execute(new Runnable() {
            @Override
            public void run() {
                uiThread.post(new Runnable() {
                    @Override
                    public void run() {
                        skjultOrd.setText("Henter Ord fra nettet...");
                    }
                });

                try {
                   // galgelegLogik.hentOrdFraRegneark("2",galgelegLogik.getMuligeOrd());
                    uiThread.post(new Runnable() {
                        @Override
                        public void run() {

                            galgelegLogik.startSpil();
                            skjultOrd.setText(galgelegLogik.getSynligtOrd());
                            startWatch();

                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                    skjultOrd.setText("kunne ikke hente data prøve igen!");
                }
            }
        });
    }

    public void startWatch(){
        time1=SystemClock.elapsedRealtime();
        chronometer.setBase(time1);
        chronometer.start();
    }
    public long getTotalPoints(){
        int maxpoint = 30000;  // max tid points en kan nå at have udover bonus.
        int wordlength = galgelegLogik.getOrdet().length();  //jo større er længde jo mere bonus fås-
        time2 = SystemClock.elapsedRealtime();
        long timesec = (time2-chronometer.getBase())/1000;
        long timepoints = maxpoint-(timesec*100);
        long bonuspoints = wordlength*5;
        long points = timepoints+bonuspoints;
        chronometer.stop();
        return points;
    }





    @Override
    public void onClick(View v) {
        if(v == home){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else {
            int id = v.getId();
            if (id >= 97 && id <= 122) {
                time2 = SystemClock.elapsedRealtime();
                System.out.println("time: "+ (time2-chronometer.getBase())/1000);
                String IndtastOrd = String.valueOf(Character.toChars(id));
                galgelegLogik.gætBogstav(IndtastOrd);
                if(galgelegLogik.isSidsteBogstavVarKorrekt()){
                    String ord = galgelegLogik.getSynligtOrd();
                    skjultOrd = findViewById(R.id.SkjultOrd);
                    skjultOrd.setText(ord);
                }else {
                    int wrongs = galgelegLogik.getAntalForkerteBogstaver();
                    changePicsAccordingly(wrongs);
                }
            } else if (id >= 123 && id <= 125) {  // gæt hvis der trykkes på danske bogstav
                String IndtastOrd = danskOrd.get(id);
                galgelegLogik.gætBogstav(IndtastOrd);
                if(galgelegLogik.isSidsteBogstavVarKorrekt()){
                    String ord = galgelegLogik.getSynligtOrd();
                    skjultOrd = findViewById(R.id.SkjultOrd);
                    skjultOrd.setText(ord);
                }else {
                    int wrongs = galgelegLogik.getAntalForkerteBogstaver();
                    changePicsAccordingly(wrongs);
                }
            }
            //https://stackoverflow.com/questions/13842447/android-set-button-background-programmatically
            v.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryLight), PorterDuff.Mode.MULTIPLY);
            v.setClickable(false);
            if(galgelegLogik.erSpilletSlut()){
                if(galgelegLogik.isSpilletErVundet()){
                    Intent intent = new Intent(this, WinActivity.class);
                    Long l = new Long(getTotalPoints());
                    int points = l.intValue();
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String date = df.format(new Date());
                   Score score = new Score(name,galgelegLogik.getOrdet(),date,points);
                   scorelist = PrefManager.getInstance().getScoresfromPref(getApplicationContext());
                   if(scorelist.getScoreslist().get(0).getPoints()==0){
                       scorelist=new ScoreList();
                   }
                   scorelist.getScoreslist().add(score);
                   PrefManager.getInstance().saveScoreList(getApplicationContext(),scorelist);
                   if(points>Integer.parseInt(highestscore.getText().toString())){
                       PrefManager.getInstance().saveHighestScore(getApplicationContext(),points);
                       intent.putExtra("newhighscore",points);
                   }
                   intent.putExtra("totalpoints",points);
                   intent.putExtra("nrforsøg",galgelegLogik.getBrugteBogstaver().size());
                   intent.putExtra("multiplayer",multiplayer);
                   startActivity(intent);
                   finish();
                }
                else if(galgelegLogik.isSpilletErTabt()){
                    Intent End = new Intent(this, LoseActivity.class);
                    String rigtigOrd = galgelegLogik.getOrdet();
                    End.putExtra("rigtig ord",rigtigOrd);
                    End.putExtra("multiplayer2",multiplayer);
                    startActivity(End);
                    finish();
                }
            }
        }
        if(v==help){
            Toast.makeText(this,"Not implemented",Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SpilActivity extends AppCompatActivity implements View.OnClickListener {
    GridLayout gridLayout;
    Button home,help;
    TextView skjultOrd, highestscore;
    GalgelegLogik galgelegLogik;
    ImageView wrongimage;
    HashMap<Integer,String> danskOrd;
    Executor backThread;
    Handler uiThread;
    Chronometer chronometer;
    long time1,time2;
    HighScoreList scorelist;
    String text;
    public SpilActivity(){
        galgelegLogik = new GalgelegLogik();
        backThread = Executors.newSingleThreadExecutor();
        uiThread = new Handler();
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
        danskOrd.put(123,"Æ");
        danskOrd.put(124,"Ø");
        danskOrd.put(125,"Å");
        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException e){}


        gridLayout = findViewById(R.id.grid);
        gridLayout.setRowCount(3);
        gridLayout.setColumnCount(10);
        addButtons();

        String highscore = Integer.toString(PrefManager.getInstance().getHighestScore(getApplicationContext()));
        highestscore.setText(highscore);
        hentOnlineOrd();  // henter og samtidlige initialisere spillet
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
            layoutParams.height = 150;
            layoutParams.width = 107;
            button.setLayoutParams(layoutParams);
            button.setOnClickListener(this);
            //https://stackoverflow.com/questions/13842447/android-set-button-background-programmatically
            button.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
            gridLayout.addView(button);
            alphaAscii++;
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
        }

    }

    public void hentOnlineOrd (){
        backThread.execute(new Runnable() {
            @Override
            public void run() {
                OnlineOrd onlineOrd = new OnlineOrd();
                uiThread.post(new Runnable() {
                    @Override
                    public void run() {
                        skjultOrd.setText("Henter Ord fra nettet...");
                    }
                });

                try {
                    onlineOrd.hentOrdFraDr(galgelegLogik.muligeOrd);
                    uiThread.post(new Runnable() {
                        @Override
                        public void run() {
                            galgelegLogik.startNytSpil();
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
        int maxpoint = 30000;
        int wordlength = galgelegLogik.getOrdet().length();
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
                if(galgelegLogik.erSidsteBogstavKorrekt()){
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
                if(galgelegLogik.erSidsteBogstavKorrekt()){
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
                if(galgelegLogik.erSpilletVundet()){
                    Intent intent = new Intent(this, EndActivity.class);
                   Long l = new Long(getTotalPoints());
                   int points = l.intValue();
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String date = df.format(new Date());
                   Score score = new Score(date,points);
                   scorelist = PrefManager.getInstance().getScoresfromPref(getApplicationContext());
                   if(scorelist.getScoreslist().get(0).getPoints()==0){
                       scorelist=new HighScoreList();
                   }
                   scorelist.getScoreslist().add(score);
                   PrefManager.getInstance().saveScoreList(getApplicationContext(),scorelist);
                   if(points>Integer.parseInt(highestscore.getText().toString())){
                       PrefManager.getInstance().saveHighestScore(getApplicationContext(),points);
                       intent.putExtra("newhighscore",points);
                   }
                   startActivity(intent);
                   finish();
                }
                else if(galgelegLogik.erSpilletTabt()){
                    Intent End = new Intent(this, LoseActivity.class);
                    String rigtigOrd = galgelegLogik.getOrdet();
                    End.putExtra("rigtig ord",rigtigOrd);
                    startActivity(End);
                    finish();
                }
            }
        }
    }
}
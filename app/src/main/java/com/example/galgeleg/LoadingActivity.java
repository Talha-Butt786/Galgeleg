package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;

import com.airbnb.lottie.LottieAnimationView;
import com.example.galgeleg.logic.Statelogic.GalgeSpilContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoadingActivity extends AppCompatActivity {
    GalgeSpilContext spilContext;
    Executor backThread;
    Handler uiThread;
    LottieAnimationView lottieAnimationView;
    public LoadingActivity(){
        spilContext = new GalgeSpilContext();
        backThread = Executors.newSingleThreadExecutor();
        uiThread = new Handler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        lottieAnimationView = findViewById(R.id.lottie_loadig);
    }

    @Override
    protected void onStart() {
        super.onStart();
        backThread.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final ArrayList<String> ord = spilContext.hentOrdFraRegneark("2");
                    uiThread.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getBaseContext(), SpilActivity.class);
                            intent.putStringArrayListExtra("ord_list",ord);
                            startActivity(intent);
                        }
                    },2000);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("not getting data");
                }
            }
        });
    }
}
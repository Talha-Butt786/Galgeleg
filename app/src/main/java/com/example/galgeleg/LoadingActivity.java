package com.example.galgeleg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;

import com.airbnb.lottie.LottieAnimationView;
import com.example.galgeleg.logic.Statelogic.GalgeSpilContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoadingActivity extends AppCompatActivity {
    RecyclerView wordlist_view;
    GalgeSpilContext spilContext;
    Executor backThread;
    Handler uiThread;
    Runnable runnable1,runnable2;
    AlertDialog alertDialog;
    public LoadingActivity(){
        spilContext = new GalgeSpilContext();
        backThread = Executors.newSingleThreadExecutor();
        uiThread = new Handler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        wordlist_view = findViewById(R.id.recycle_view);
        startdialogbox();
        fetchData();
    }

    private void fetchData() {
        backThread.execute(runnable1=new Runnable() {
            @Override
            public void run() {
                try {
                    final ArrayList<String> ord = spilContext.hentOrdFraRegneark("2");
                    uiThread.postDelayed(runnable2 = new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getBaseContext(), SpilActivity.class);
                            intent.putStringArrayListExtra("ord_list",ord);
                            startActivity(intent);
                            finish();
                        }
                    },2000);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("not getting data");
                }
            }
        });
    }

    public void startdialogbox(){
        AlertDialog.Builder dialogbox = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        dialogbox.setView(inflater.inflate(R.layout.downloadind_view,null));
        dialogbox.setCancelable(false);
        alertDialog = dialogbox.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.select_button);
    }
}
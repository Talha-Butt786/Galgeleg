package com.example.galgeleg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;

import com.example.galgeleg.logic.Statelogic.GalgeSpilContext;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListActivity extends AppCompatActivity {
    RecyclerView wordlist_view;
    AlertDialog alertDialog;
    Executor backThread;
    Handler uiThread;
    GalgeSpilContext spilContext;
    ArrayList<String> words;

    public ListActivity(){
        spilContext = new GalgeSpilContext();
        backThread = Executors.newSingleThreadExecutor();
        uiThread = new Handler();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        startdialogbox();
        fetchData();
    }

    public void fetchData (){
        backThread.execute(new Runnable() {
            @Override
            public void run() {
                try {
                   final ArrayList<String> words_online = spilContext.hentOrdFraRegneark("2");
                    uiThread.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ListordAdapter liste =new ListordAdapter(getApplicationContext(),words_online);
                            wordlist_view = findViewById(R.id.recycle_view);
                            wordlist_view.setHasFixedSize(true);
                            wordlist_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            wordlist_view.setAdapter(liste);
                            alertDialog.dismiss();
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
    }

}
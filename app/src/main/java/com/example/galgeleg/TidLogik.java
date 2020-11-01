package com.example.galgeleg;

public class TidLogik {
    long time1;
    long time2;
    int min;
    int sec;

    public void startTime(){
       time1 = System.currentTimeMillis();
    }
    public void stopTime(){
        time2 = System.currentTimeMillis();
    }
    public long getTotalTime(){
        sec = (int) ((time2-time1)/1000);
        return (time2-time1)/1000;
    }
    public void actualTime(){
        if(getTotalTime()/60==0){
        }
    }
}

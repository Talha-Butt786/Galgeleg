package com.example.galgeleg.Statelogic;

import java.util.ArrayList;

public class GalgeSpilContext {
    private GalgelegState state;
    private String ordet;
    private boolean gameOn;
    private boolean spilletErVundet;
    private boolean spilletErTabt;
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private ArrayList<String> muligeOrd = new ArrayList<String>();
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();


    public GalgeSpilContext(){
        muligeOrd.add("bil");
        muligeOrd.add("computer");
        muligeOrd.add("programmering");
        muligeOrd.add("motorvej");
        muligeOrd.add("busrute");
        muligeOrd.add("gangsti");
        muligeOrd.add("skovsnegl");
        muligeOrd.add("solsort");
        muligeOrd.add("tyve");
        this.state = new InitialState();
        gameOn = false;
    }

    public void opdaterSynligtOrd() {
        synligtOrd = "";
        spilletErVundet = true;
        for (int n = 0; n < ordet.length(); n++) {
            String bogstav = ordet.substring(n, n + 1);
            if (brugteBogstaver.contains(bogstav)) {
                synligtOrd = synligtOrd + bogstav;
            } else {
                synligtOrd = synligtOrd + "*";
                spilletErVundet = false;
            }
        }
    }

    public void logStatus() {
        System.out.println("---------- ");
        System.out.println("- ordet (skult) = " + ordet);
        System.out.println("- synligtOrd = " + synligtOrd);
        System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
        System.out.println("- brugeBogstaver = " + brugteBogstaver);
        if (spilletErTabt) System.out.println("- SPILLET ER TABT");
        if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
        System.out.println("---------- ");
    }

    public void startSpil(){
        state.startSpil(this);
    }
    public void gætBogstav(String bogstav){
        state.gætBogstav(this,bogstav);
    }

    public boolean erSpilletSlut() {
        return spilletErTabt || spilletErVundet;
    }




    public String getOrdet() {
        return ordet;
    }

    public void setOrdet(String ordet) {
        this.ordet = ordet;
    }

    public ArrayList<String> getMuligeOrd() {
        return muligeOrd;
    }

    public void setMuligeOrd(ArrayList<String> muligeOrd) {
        this.muligeOrd = muligeOrd;
    }

    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }

    public void setBrugteBogstaver(ArrayList<String> brugteBogstaver) {
        this.brugteBogstaver = brugteBogstaver;
    }


    protected void setState(GalgelegState state){
        this.state=state;
    }
    public GalgelegState getState(){
        return state;
    }


    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

    public boolean isSpilletErVundet() {
        return spilletErVundet;
    }

    public void setSpilletErVundet(boolean spilletErVundet) {
        this.spilletErVundet = spilletErVundet;
    }

    public boolean isSpilletErTabt() {
        return spilletErTabt;
    }

    public void setSpilletErTabt(boolean spilletErTabt) {
        this.spilletErTabt = spilletErTabt;
    }

    public String getSynligtOrd() {
        return synligtOrd;
    }

    public void setSynligtOrd(String synligtOrd) {
        this.synligtOrd = synligtOrd;
    }

    public int getAntalForkerteBogstaver() {
        return antalForkerteBogstaver;
    }

    public void setAntalForkerteBogstaver(int antalForkerteBogstaver) {
        this.antalForkerteBogstaver = antalForkerteBogstaver;
    }

    public boolean isSidsteBogstavVarKorrekt() {
        return sidsteBogstavVarKorrekt;
    }

    public void setSidsteBogstavVarKorrekt(boolean sidsteBogstavVarKorrekt) {
        this.sidsteBogstavVarKorrekt = sidsteBogstavVarKorrekt;
    }
}

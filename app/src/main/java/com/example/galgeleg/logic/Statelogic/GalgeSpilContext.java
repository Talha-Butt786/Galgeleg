package com.example.galgeleg.logic.Statelogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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


    public GalgeSpilContext() {
        this.state = new StartState();
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

    public ArrayList<String> hentOrdFraRegneark(String sværhedsgrader) throws Exception {
        ArrayList<String> muligeOrd = new ArrayList<>();
        String id = "1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M";
        System.out.println("Henter data som kommasepareret CSV fra regnearket https://docs.google.com/spreadsheets/d/"+id+"/edit?usp=sharing");

        String data = hentUrl("https://docs.google.com/spreadsheets/d/" + id + "/export?format=csv&id=" + id);
        int linjeNr = 0;
            muligeOrd.clear();
        for (String linje : data.split("\n")) {
            if (linjeNr<20) System.out.println("Læst linje = " + linje); // udskriv de første 20 linjer
            if (linjeNr++ < 1 ) continue; // Spring første linje med kolonnenavnene over
            String[] felter = linje.split(",", -1);// -1 er for at beholde tomme indgange, f.eks. bliver ",,," splittet i et array med 4 tomme strenge
            String sværhedsgrad = felter[0].trim();
            String ordet = felter[1].trim().toLowerCase();
            if (sværhedsgrad.isEmpty() || ordet.isEmpty()) continue; // spring over linjer med tomme ord
            if (!sværhedsgrader.contains(sværhedsgrad)) continue; // filtrér på sværhedsgrader
            System.out.println("Tilføjer "+ordet+", der har sværhedsgrad "+sværhedsgrad);
            muligeOrd.add(ordet);
        }
        System.out.println("muligeOrd = " + muligeOrd);
        return muligeOrd;
    }
    public static String hentUrl(String url) throws IOException {
        System.out.println("Henter data fra " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
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

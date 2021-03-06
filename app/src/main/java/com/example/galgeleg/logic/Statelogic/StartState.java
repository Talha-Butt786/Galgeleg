package com.example.galgeleg.logic.Statelogic;

import java.util.Random;

public class StartState extends GalgelegStateAdapter{
    @Override
    public void startSpil(GalgeSpilContext context) {
        context.getBrugteBogstaver().clear();
        context.setAntalForkerteBogstaver(0);
        context.setSpilletErVundet(false);
        context.setSpilletErTabt(false);
        if(context.getGivenWord()!=null){
            context.setOrdet(context.getGivenWord());
            context.setGameOn(true);
            context.opdaterSynligtOrd();
            context.setState(new PlayingState());
        }
        else if (context.getMuligeOrd().isEmpty()) throw new IllegalStateException("Listen over mulige ord er tom!");
        else {
            context.setOrdet(context.getMuligeOrd().get(new Random().nextInt(context.getMuligeOrd().size())));
        System.out.println("Nyt spil - det skjulte ord er: "+context.getOrdet());
        context.setGameOn(true);
        context.opdaterSynligtOrd();
        context.setState(new PlayingState());
        }
    }
}


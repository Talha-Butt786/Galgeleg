package com.example.galgeleg.Statelogic;

import java.util.Scanner;

public class PlayingState extends GalgelegStateAdapter {

    public void gætBogstav(GalgeSpilContext context,String bogstav) {
            if (bogstav.length() != 1) return;
            System.out.println("Der gættes på bogstavet: " + bogstav);
            if (context.getBrugteBogstaver().contains(bogstav)) return;
            if (context.isSpilletErVundet() || context.isSpilletErTabt()) {
                context.setState(new FinishState());
            }

            context.getBrugteBogstaver().add(bogstav);

            if (context.getOrdet().contains(bogstav)) {
                context.setSidsteBogstavVarKorrekt(true);
                System.out.println("Bogstavet var korrekt: " + bogstav);
            } else {
                // Vi gættede på et bogstav der ikke var i ordet.
                context.setSidsteBogstavVarKorrekt(false);
                System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
                context.setAntalForkerteBogstaver(context.getAntalForkerteBogstaver() + 1);
                if (context.getAntalForkerteBogstaver() > 6) {
                    context.setSpilletErTabt(true);
                    context.setState(new FinishState());
                }
            }
            context.opdaterSynligtOrd();
            if(context.erSpilletSlut()){
                context.setState(new FinishState());
            }
        }
    }

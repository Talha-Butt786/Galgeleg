package com.example.galgeleg.Statelogic;

public interface GalgelegState {
    void startSpil(GalgeSpilContext context);
    void gætBogstav(GalgeSpilContext context, String bogstav);
}

package com.example.galgeleg.Statelogic;

public class FinishState extends GalgelegStateAdapter {
    public void startSpil(GalgeSpilContext context){
        context.setState(new InitialState());
    }
}

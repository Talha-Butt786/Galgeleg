package com.example.galgeleg.State_logic.Statelogic;

public class FinishState extends GalgelegStateAdapter {
    public void startSpil(GalgeSpilContext context){
        context.setState(new InitialState());
    }
}

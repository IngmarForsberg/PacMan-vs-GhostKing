package no.uib.inf101.sem2.gamestates;

import no.uib.inf101.sem2.view.Game;

public class State {
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
    
}

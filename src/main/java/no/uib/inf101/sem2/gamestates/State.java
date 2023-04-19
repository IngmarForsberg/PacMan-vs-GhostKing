package no.uib.inf101.sem2.gamestates;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.sem2.main.Game;

public class State {
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public boolean collisionDetected(Rectangle2D.Float hitBox1, Rectangle2D.Float hitBox2) {
        if(hitBox1.intersects(hitBox2)) {
            return true;
        }
        return false;
    } 


    
}

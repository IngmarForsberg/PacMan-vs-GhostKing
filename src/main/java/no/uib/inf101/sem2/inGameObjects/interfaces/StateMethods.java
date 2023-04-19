package no.uib.inf101.sem2.inGameObjects.interfaces;

import java.awt.Graphics;

public interface StateMethods {
    /**
     * updates the gamestates
     */
    void update();

    /**
     * draws the game state animations
     * @param g
     */
    void draw(Graphics g);
    
}

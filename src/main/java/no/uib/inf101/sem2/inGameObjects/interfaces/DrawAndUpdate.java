package no.uib.inf101.sem2.inGameObjects.interfaces;
import java.awt.Graphics;

public interface DrawAndUpdate {
    /**
     * draws the animations
     * @param g
     */
    void draw(Graphics g);

    /**
     * updates the animations
     */
    void update();

    /**
     * updates the animation position
     */
    void updatePosition();
    
}

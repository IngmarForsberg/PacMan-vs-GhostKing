package no.uib.inf101.sem2.inGameObjects.interfaces;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public interface HitBox {

    /**
     * initializes hitbox
     */
    void initHitBox(float x, float y, float width, float height);
    
    /**
     * gets hitbox
     * @return
     */
    Rectangle2D.Float getHitBox();

    void resetHitBox();

    

    /**
     * updates hitbox to match the position and size of entity
     * @param x
     * @param y
     * @param width
     * @param height
     */
    void updateHitbox(float x, float y, float width, float height);

    /**
     * draws hitbox, used for debugging purposes
     * @param g
     */
    void drawHitBox(Graphics g);

    
}

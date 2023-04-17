package no.uib.inf101.sem2.inGameObjects.interfaces;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public interface HitBox {

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

    /**
     * detects the colission of two hitboxes and adjusts the position of entity
     * @param hitBox
     * @param xOffSet number of pixels from beginning of sprite to the animation actually starts, in x direction
     * @param yOffSet number of pixels from beginning of sprite to the animation actually starts, in y direction
     */
    //void positionAfterCollision(Rectangle2D.Float hitBox, int xOffSet, int yOffSet);
    
}

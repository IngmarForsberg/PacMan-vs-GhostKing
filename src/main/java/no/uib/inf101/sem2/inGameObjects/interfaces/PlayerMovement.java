package no.uib.inf101.sem2.inGameObjects.interfaces;

import java.awt.geom.Rectangle2D;

public interface PlayerMovement {
    
    /**
     * sets jump movement either true or false
     * @param jump
     */
    void setJump(boolean jump);

    /**
     * sets crouch movement either true or false
     * @param crouch
     */
    void setCrouch(boolean crouch);

}
    

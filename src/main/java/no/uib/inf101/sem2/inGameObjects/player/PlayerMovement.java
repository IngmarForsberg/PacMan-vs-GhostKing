package no.uib.inf101.sem2.inGameObjects.player;

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

    /**
     * true if player is on ground, false if in air
     * @return
     */
    boolean playerOnGround();
}
    

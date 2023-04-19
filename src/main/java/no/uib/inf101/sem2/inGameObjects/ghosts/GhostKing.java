package no.uib.inf101.sem2.inGameObjects.ghosts;

import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;
import static no.uib.inf101.sem2.constants.sprites.GhostKingSprite.GhostKingAction.*;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;
import java.awt.Graphics;
import no.uib.inf101.sem2.inGameObjects.GameObjects;
import no.uib.inf101.sem2.inGameObjects.interfaces.DrawAndUpdate;
import no.uib.inf101.sem2.main.Game;

import static no.uib.inf101.sem2.constants.sprites.PlayerSprite.PlayerAction.*;


public class GhostKing extends GameObjects implements DrawAndUpdate {
    private int ghostKingAction;
    private boolean retreat = false;
    
    
    public GhostKing(float x, float y, float width, float height, Game game) {
        super(x, y, width, height, game);
        loadAnimations(GHOST_KING_SPRITE,1, 5, GHOST_KING_PIXEL_WIDTH, GHOST_KING_PIXEL_HEIGHT);
        initHitBox(x + X_OFFSET, y + Y_OFFSET, width + WIDTH_OFFSET, height + HEIGHT_OFFSET); 
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(animationArray[ghostKingAction][animationIndex], (int)x, (int)y, (int)width, (int)height, null);
    }

    

    @Override
    public void update() {
        updatePosition();
        updateHitbox(X_OFFSET, Y_OFFSET, WIDTH_OFFSET, HEIGHT_OFFSET);
        updateAnimationTick(ANIMATION_SPEED, ghostKingSpriteSize(ghostKingAction));  
    }

    @Override
    public void updatePosition() {
        ghostKingAction = DEFAULT; 
        hunt();
        if(retreat) {
            retreat();
        }
    }

    private void hunt() {
        // if the player collides with a ghost, the ghostking moves forward
        for(Ghost ghost : game.activeGame().getGhostList()) {
            if(collisionDetected(game.activeGame().getPlayer().getHitBox(), ghost.getHitBox()) && !retreat) {
                x += GHOST_KING_SPEED;
            }
        } 
       
        // if the ghostking collides with player, it enters retreat mode, and moves backwards
        // retreat mode ends when ghostking is at starting position
       if(collisionDetected(game.activeGame().getPlayer().getHitBox(), this.hitBox) && !game.activeGame().getLifeList().isEmpty()) {
                game.activeGame().getLifeList().remove(0);
                retreat = true;
                game.activeGame().getPlayer().setIsScreaming(true); // player screams when it collides with ghost king
                x -= GHOST_KING_SPEED;
        }
    }

    private void retreat() {
        // ghost king moves back to starting position
        x -= GHOST_KING_SPEED;
        if(x <= GHOST_KING_X) {
            x = GHOST_KING_X;
            retreat = false;
            game.activeGame().getPlayer().setIsScreaming(false);
        }
    }

}

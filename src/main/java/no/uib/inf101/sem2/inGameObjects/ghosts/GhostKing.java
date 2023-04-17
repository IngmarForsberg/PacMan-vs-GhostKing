package no.uib.inf101.sem2.inGameObjects.ghosts;

import no.uib.inf101.sem2.view.Game;

import static no.uib.inf101.sem2.constants.ObjectConstants.ObjectDimensions.*;
import static no.uib.inf101.sem2.constants.sprites.GhostKingSprite.GhostKingAction.*;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;
import java.awt.Graphics;
import no.uib.inf101.sem2.inGameObjects.GameObjects;
import no.uib.inf101.sem2.inGameObjects.interfaces.DrawAndUpdate;
import static no.uib.inf101.sem2.constants.sprites.PlayerSprite.PlayerAction.*;


public class GhostKing extends GameObjects implements DrawAndUpdate {
    private int ghostKingAction;
    private final float X_OFFSET = GHOST_KING_WIDTH *1/2;
    private final float Y_OFFSET = GHOST_KING_HEIGHT*1/4;
    private final float WIDTH_OFFSET = - GHOST_KING_WIDTH*2/3;
    private final float HEIGHT_OFFSET = - GHOST_KING_HEIGHT * 1/2;
    public final float GHOST_KING_SPEED = 10;
    //private float xSpeed;
    private boolean retreat = false;
    private boolean isScreaming = false;
    
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
        System.out.println(ghostKingAction);
        updateHitbox(X_OFFSET, Y_OFFSET, WIDTH_OFFSET, HEIGHT_OFFSET);
        updateAnimationTick(5, ghostKingSpriteSize(ghostKingAction));  
        if(isScreaming) {
            game.activeGame().getPlayer().setPlayerAction(SCREAMING);
       }
    }

    @Override
    public void updatePosition() {
        ghostKingAction = DEFAULT; 
        // if the player collides with a ghost, the ghostking moves forward
        for(Ghost ghost : game.activeGame().getGhostList()) {
            if(ghost.collisionDetected(game.activeGame().getPlayer().getHitBox()) && !retreat) {
                x += GHOST_KING_SPEED;
            }
        } 
       
        // if the ghostking collides with player, it enters retreat mode, and moves backwards
        // retreat mode ends when ghostking is at starting position
       if(collisionDetected(game.activeGame().getPlayer().getHitBox()) && !game.activeGame().getLifeList().isEmpty()) {
               
                game.activeGame().getLifeList().remove(0);
                retreat = true;
                isScreaming = true;
                x -= GHOST_KING_SPEED;
        }
        
        if(retreat) {
            x -= GHOST_KING_SPEED;
            if(x <= GHOST_KING_X) {
                x = GHOST_KING_X;
                retreat = false;
                isScreaming = false;
            }
        }

     
    }

}

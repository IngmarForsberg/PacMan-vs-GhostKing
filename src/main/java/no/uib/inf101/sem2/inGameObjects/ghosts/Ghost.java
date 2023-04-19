package no.uib.inf101.sem2.inGameObjects.ghosts;

import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;
import static no.uib.inf101.sem2.constants.sprites.GhostSprite.GhostColors.*;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import no.uib.inf101.sem2.gamestates.Difficulty;
import no.uib.inf101.sem2.inGameObjects.GameObjects;
import no.uib.inf101.sem2.inGameObjects.interfaces.DrawAndUpdate;
import no.uib.inf101.sem2.main.Game;



public class Ghost extends GameObjects implements DrawAndUpdate {
    private int ghostColor;
    private Random rand;
    
    public Ghost(float x, float y, float width, float height, Game game) { 
        super(x, y, width, height, game);
        loadAnimations(GHOST_SPRITE, 4,5,GHOST_PIXEL_WIDTH, GHOST_PIXEL_HEIGHT);
    }

        
    @Override
    public void draw(Graphics g) {
        g.drawImage(animationArray[ghostColor][animationIndex], (int)x, (int)y, (int)width, (int)height, null);
    }
    
    private void adjustSpeed() {
        // adjust speed based on difficulty
        if(Difficulty.difficulty == Difficulty.MEDIUM) {
            xSpeed = GHOST_SPEED + MEDIUM_SPEED_ADDER;
        }
        else if(Difficulty.difficulty == Difficulty.HARD) {
            xSpeed = GHOST_SPEED + HARD_SPEED_ADDER;
        }
        // if collision with player, the ghost stops 
        if(collisionDetected(game.activeGame().getPlayer().getHitBox(), this.hitBox) && !game.activeGame().getPlayer().isFlying()) {
            xSpeed = 0;
        }

        if(game.activeGame().getPlayer().isFlying()) {
            xSpeed += ROCKET_SPEED;
        }
    }
    
    @Override
    public void updatePosition() {
        xSpeed = GHOST_SPEED;
        adjustSpeed();
        x -= xSpeed;
    } 

    /**
     * creates a random ghost color
     */
    public void randomGhost() {
        rand = new Random();
        ArrayList<Integer> ghostColors = new ArrayList<>();
        // the four ghost colors
        ghostColors.add(BLACK);
        ghostColors.add(YELLOW);
        ghostColors.add(GREEN);
        ghostColors.add(PINK);

        int randomIndex = rand.nextInt(ghostColors.size());
        int randomColor = ghostColors.get(randomIndex);
        ghostColor = randomColor;
    }

    

    
    @Override
    public void update() {
       
        updateHitbox(X_OFFSET, Y_OFFSET, WIDTH_OFFSET, HEIGHT_OFFSET);
        updatePosition();
        updateAnimationTick(ANIMATION_SPEED, ghostSpriteSize(ghostColor)); 
        
    }
    
}

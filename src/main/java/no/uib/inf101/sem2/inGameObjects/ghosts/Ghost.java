package no.uib.inf101.sem2.inGameObjects.ghosts;

import no.uib.inf101.sem2.view.Game;

import static no.uib.inf101.sem2.constants.ObjectConstants.ObjectDimensions.*;
import static no.uib.inf101.sem2.constants.sprites.GhostSprite.GhostColors.*;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;

import java.awt.Graphics;
import java.awt.List;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import no.uib.inf101.sem2.gamestates.ActiveGame;
import no.uib.inf101.sem2.inGameObjects.GameObjects;
import no.uib.inf101.sem2.inGameObjects.interfaces.DrawAndUpdate;
import no.uib.inf101.sem2.inGameObjects.player.Player;


public class Ghost extends GameObjects implements DrawAndUpdate {
    private int ghostColor;
    private static Player player;
    private Random rand;
    //private Rectangle2D.Float hitBox;

    private final float X_OFFSET = GHOST_WIDTH * 1/8;
    private final float Y_OFFSET = GHOST_HEIGHT*1/6;
    private  final float WIDTH_OFFSET = - GHOST_WIDTH * 5/8;
    private  final float HEIGHT_OFFSET = - GHOST_WIDTH * 1/2;

    private final float EASY_GHOST_SPEED = 15;
    private final float MEDIUM_GHOST_SPEED = 20;
    private final float HARD_GHOST_SPEED = 25;
    private final float MEDIUM_GHOST_SPEED_THRESHOLD = 3000;
    private final float HARD_GHOST_SPEED_THRESHOLD = 6000;
  
    

    public Ghost(float x, float y, float width, float height, Game game) { 
        super(x, y, width, height, game);
        //initHitBox(x*Game.SCALE, y*Game.SCALE, width, height);
        loadAnimations(GHOST_SPRITE, 4,5,PIXEL_WIDTH, PIXEL_HEIGHT );
       
    }

        
    @Override
    public void draw(Graphics g) {
        //playerAction = RUNNING;
        g.drawImage(animationArray[ghostColor][animationIndex], (int)x, (int)y, (int)width, (int)height, null);
            
        //drawHitBox(g);
        
            //g.drawString("x: " + this.x, 100, 100);
            
    }
    
    
    @Override
    public void updatePosition() {
        float deltaX = EASY_GHOST_SPEED;
    
        if(game.activeGame().getScore() > MEDIUM_GHOST_SPEED_THRESHOLD && game.activeGame().getScore() <= HARD_GHOST_SPEED_THRESHOLD) {
            deltaX = MEDIUM_GHOST_SPEED;
        }
        else if(game.activeGame().getScore() > HARD_GHOST_SPEED_THRESHOLD) {
            deltaX = HARD_GHOST_SPEED;
        }
          
        if(collisionDetected(game.activeGame().getPlayer().getHitBox()) && !game.activeGame().getPlayer().flying()) {
            deltaX = 0;
        }
        if(game.activeGame().getPlayer().flying()) {
            deltaX +=10;
        }
        x -= deltaX;

    } 

    public int randomGhostPosition() {
        Random random = new Random();
        ArrayList<Integer> randomXPosition = new ArrayList<>();
        randomXPosition.add(500);
        randomXPosition.add(800); 
        randomXPosition.add(2000);
        randomXPosition.add(1500);
        int randomIndex = random.nextInt(randomXPosition.size());
        int xDelta = randomXPosition.get(randomIndex);
        return xDelta;
    }

    public void randomGhost() {
        rand = new Random();
        ArrayList<Integer> ghostColors = new ArrayList<>();
        ghostColors.add(BLACK);
        ghostColors.add(YELLOW);
        ghostColors.add(GREEN);
        ghostColors.add(PINK);

        int randomIndex = rand.nextInt(ghostColors.size());
        int randomColor = ghostColors.get(randomIndex);

        //playerAction = randomColor;
        ghostColor = randomColor;
    }

    

    
    @Override
    public void update() {
       
        
        
        updateHitbox(X_OFFSET, Y_OFFSET, WIDTH_OFFSET, HEIGHT_OFFSET);
        updatePosition();
            
        updateAnimationTick(5, ghostSpriteSize(ghostColor)); 
        //System.out.println(player.getHitBox());
        

    }


    


   


    
    
        
        
    
    
        
    
    
}

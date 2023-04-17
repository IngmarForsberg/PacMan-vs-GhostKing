package no.uib.inf101.sem2.inGameObjects.player;
import static no.uib.inf101.sem2.constants.ObjectConstants.ObjectDimensions.*;
import static no.uib.inf101.sem2.constants.sprites.ItemSprite.ItemType.*;
import static no.uib.inf101.sem2.constants.sprites.PlayerSprite.PlayerAction.*;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.PLAYER_SPRITE;

import java.awt.Graphics;
import java.awt.Robot;
import java.awt.geom.Rectangle2D.Float;

import javax.swing.text.html.parser.Entity;

import no.uib.inf101.sem2.gamestates.ActiveGame;
import no.uib.inf101.sem2.inGameObjects.GameObjects;

import no.uib.inf101.sem2.inGameObjects.ghosts.Ghost;
import no.uib.inf101.sem2.inGameObjects.interfaces.DrawAndUpdate;
import no.uib.inf101.sem2.inGameObjects.items.Items;
import no.uib.inf101.sem2.terrain.Ground;
import no.uib.inf101.sem2.view.Game;

public class Player extends GameObjects implements PlayerMovement, DrawAndUpdate {
    private int playerAction;
    
    private static final int ROCKET_TIMER_THRESHOLD = 5; //5 seconds in air
    private float rocketTimer = 0; 
   



    
    
    private static final int ANIMATION_SPEED = 5;
    /* int rocketThreshold = 400;
    int rocketValue = 0; */
    private boolean canFly = false;
    private boolean isFlying = false;

    
    //offset; number of pixels between start of image and where the player animation actually starts
    private final float X_OFFSET = PLAYER_WIDTH *1/4;
    private final float Y_OFFSET = PLAYER_HEIGHT*1/8;
    private final float WIDTH_OFFSET = - PLAYER_WIDTH*0.5f;
    private final float HEIGHT_OFFSET = - PLAYER_HEIGHT * 3/8;

    // for crouching, the offset changes
    private final float Y_OFFSET_CROUCHING= PLAYER_HEIGHT*1/3; 
    private final float HEIGHT_OFFSET_CROUCHING= - PLAYER_HEIGHT*1/2;

    private boolean jump, crouch, left, right;
    private int[][] levelData;
    private static final int RUNNING_SPEED = 10;


    // constants for jumping/gravity
    public final float JUMP_SPEED = -12f;
    private static final float GRAVITY = 0.8f;

    // player pixel dimensions
    private static final int PLAYER_PIXEL_WIDTH = 32;
    private static final int PLAYER_PIXEL_HEIGHT = 32;

    

    private boolean canJump = true;
    private boolean isJumping = false;
    

    public Player(float x, float y, float width, float height, Game game) {
        super(x, y, width, height, game);
        loadAnimations(PLAYER_SPRITE,5,2,PLAYER_PIXEL_HEIGHT, PLAYER_PIXEL_WIDTH);
        
        initHitBox(x + X_OFFSET, y + Y_OFFSET, width + WIDTH_OFFSET, height + HEIGHT_OFFSET);
    }

    public void setYSpeed(int value) {
        ySpeed = value;
    }

    
    @Override
    public void setJump(boolean jump) {
        this.jump = jump;
        
    }
    @Override
    public void setCrouch(boolean crouch) {
		this.crouch = crouch;
	}

    @Override
    public boolean playerOnGround() {
        hitBox.y ++; // moves hitbox one down
        // if hitbox intersects ground, player is on ground
            if(collisionDetected(game.activeGame().getGround().getHitBox())) {
                hitBox.y--;
                return true;
            }
        
        hitBox.y--;
        return false;
    } 

    
    
    @Override
    public void updatePosition() {
        ySpeed += GRAVITY; 
        
        updateHitbox(X_OFFSET, Y_OFFSET, WIDTH_OFFSET, HEIGHT_OFFSET);
        positionAfterCollision(X_OFFSET, Y_OFFSET);
  
        if(jump && canJump && !crouch){
            canJump = false;
            isJumping = true;
            ySpeed = JUMP_SPEED;
        }
        

        if(playerOnGround()) {
            
            isJumping = false;
            isFlying = false;
            canJump = true;
        
            if(crouch && !jump) {
                updateHitbox(X_OFFSET, Y_OFFSET_CROUCHING, WIDTH_OFFSET, HEIGHT_OFFSET_CROUCHING);
                playerAction = CROUCHING;
            }
            else {
                playerAction = RUNNING;
            
            }
        }
        
        else if(!playerOnGround() && !isFlying) {

            if(crouch) {
                    ySpeed = 15;
            }
            else{   
                    playerAction = JUMPING;
            }
            
             
        }
        
        y += ySpeed;
    }

    

    
    
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(animationArray[playerAction][animationIndex], (int)x, (int)y, (int)width, (int)height, null);
        //drawHitBox(g);
    
        //g.drawString("x: " + this.x, 100, 100);
        
    }
    @Override
    public void update() {
        updatePosition();
        updateAnimationTick(ANIMATION_SPEED, playerSpriteSize(playerAction)); 
    }
  

    private void positionAfterCollision(float xOffSet, float yOffSet) {
        // checks collision in vertical direction, and updates the player position

        this.hitBox.y += ySpeed; // moves the hitbox according to speed
        if(collisionDetected(game.activeGame().getGround().getHitBox())) {
            this.hitBox.y -= ySpeed; // if player intersects an obstacle, the hitbox moves back
        
            while(!collisionDetected(game.activeGame().getGround().getHitBox())) {
                this.hitBox.y += Math.signum(ySpeed); // while player is not colliding with obstacle, it moves 1 pixel closer
            }
            this.hitBox.y -= Math.signum(ySpeed); //if a collition is detected, it moves one pixel back again
            ySpeed = 0; // the player stops
            this.y = this.hitBox.y - yOffSet;
        }

    }

   

    /**
     * 
     * @return true if player is flying, false otherwise
     */
    public boolean flying() {
        return isFlying;
    }

    /**
     * changes the isFlying boolean to desired value
     * @param flying
     */
    public void setFlying(boolean flying) {
        isFlying = flying;
    }

    public void setPlayerAction(int newPlayerAction) {
        this.playerAction = newPlayerAction;
    }


}

    
        

    






    

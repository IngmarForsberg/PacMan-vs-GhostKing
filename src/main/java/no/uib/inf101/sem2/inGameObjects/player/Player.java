package no.uib.inf101.sem2.inGameObjects.player;
import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;
import static no.uib.inf101.sem2.constants.sprites.PlayerSprite.PlayerAction.*;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.PLAYER_SPRITE;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import no.uib.inf101.sem2.inGameObjects.GameObjects;
import no.uib.inf101.sem2.inGameObjects.interfaces.DrawAndUpdate;
import no.uib.inf101.sem2.inGameObjects.interfaces.PlayerMovement;
import no.uib.inf101.sem2.main.Game;

public class Player extends GameObjects implements PlayerMovement, DrawAndUpdate {
    private int playerAction;
    private boolean isFlying = false;
    private boolean jump, crouch;
    private boolean canJump = true;
    private boolean isJumping = false;
    private boolean isScreaming = false;
    
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
    public void updatePosition() {
        // default ySpeed is gravity
        ySpeed += GRAVITY; 
        updateHitbox(X_OFFSET, Y_OFFSET, WIDTH_OFFSET, HEIGHT_OFFSET);
        positionAfterCollision(Y_OFFSET, game.activeGame().getGround().getHitBox());
        // when player is on ground
        if(onGround(game.activeGame().getGround().getHitBox())) {
            isJumping = false;
            isFlying = false;
            canJump = true; 

            if(jump && canJump && !crouch){
                canJump = false;
                isJumping = true;
                ySpeed = JUMP_SPEED;
            }
        
            else if(crouch && !jump) {
                // updates hitbox for crouching animation
                updateHitbox(X_OFFSET, PLAYER_Y_OFFSET_CROUCHING, WIDTH_OFFSET, PLAYER_HEIGHT_OFFSET_CROUCHING);
                playerAction = CROUCHING;
            }
            else {
                // running is the default player action
                playerAction = RUNNING;
            
            }
        }
        // when player is in air
        else if(!onGround(game.activeGame().getGround().getHitBox()) && !isFlying) {
            if(crouch) {
                ySpeed = FALLING_BOOST;
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
        
    }

    @Override
    public void update() {
        updatePosition();
        updateAnimationTick(ANIMATION_SPEED, playerSpriteSize(playerAction)); 
        if(isScreaming) {
            playerAction = SCREAMING;
        }
    }
  
    /**
     * Stops the player from moving through terrain in vertical direction, by stopping it if collision is detected
     * @param yOffSet 
     * @param terrain
     */
    public void positionAfterCollision(float yOffSet, Rectangle2D.Float terrain) {
        // checks collision in vertical direction, and updates the player position

        this.hitBox.y += ySpeed; // moves the hitbox according to speed
        if(collisionDetected(terrain, this.hitBox)) {
            this.hitBox.y -= ySpeed; // if player intersects an obstacle, the hitbox moves back
        
            while(!collisionDetected(terrain, this.hitBox)) {
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
    public boolean isFlying() {
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

    /**
     * 
     * @return true if player is jumping, false otherwise
     */
    public boolean isJumping() {
        return isJumping;
    } 
    /**
     * 
     * @return true if player can jump, false otherwise
     */
    public boolean canJump() {
        return canJump;
    }

    /**
     * 
     * @return true if player is screaming, false otherwise
     */
    public boolean isScreaming() {
        return isScreaming;
    }

    /**
     * change the isScreaming boolean
     * @param bool
     */
    public void setIsScreaming(boolean bool) {
        isScreaming = bool;
    }
   

    

}

    
        

    






    

package no.uib.inf101.sem2.inGameObjects.items;

import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;
import static no.uib.inf101.sem2.constants.sprites.ItemSprite.ItemType.*;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;
import java.awt.Graphics;
import static no.uib.inf101.sem2.constants.sprites.PlayerSprite.PlayerAction.*;
import no.uib.inf101.sem2.gamestates.Difficulty;
import no.uib.inf101.sem2.inGameObjects.GameObjects;
import no.uib.inf101.sem2.inGameObjects.interfaces.DrawAndUpdate;
import no.uib.inf101.sem2.inGameObjects.player.Player;
import no.uib.inf101.sem2.main.Game;

public class Items extends GameObjects implements DrawAndUpdate {

    private float rocketTimer = 0; 
    private int itemType;
    public static int COIN_SCORE_BONUS = 100;
    public static float ROCKET_SCORE_BONUS = 1f;
    
    public Items(float x, float y, float width, float height, Game game) {
        super(x, y, width, height, game);
        loadAnimations(ITEM_SPRITE, 3,1,ITEM_PIXEL_WIDTH, ITEM_PIXEL_HEIGHT );
        initHitBox(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(animationArray[itemType][animationIndex], (int)x, (int)y, (int)width, (int)height, null);
    }

    private void adjustSpeed() {
         // increasing item speed based on difficulty
        if(Difficulty.difficulty == Difficulty.MEDIUM) {
            xSpeed = ITEM_SPEED + MEDIUM_SPEED_ADDER;
        }
        else if(Difficulty.difficulty == Difficulty.HARD) {
            xSpeed = ITEM_SPEED + HARD_SPEED_ADDER;
        }
        
        if(game.activeGame().getPlayer().isFlying()) {
            xSpeed += ROCKET_SPEED;
        }
    }

    @Override
    public void updatePosition(){
        xSpeed = ITEM_SPEED;
        adjustSpeed();
        x -= xSpeed;
    }


    /**
     * 
     * @return itemtype
     */
    public int getItemType() {
        return itemType;
    }

    /**
     * sets item to desired itemtype
     * @param itemType
     */
    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    private void updateItemHitboxes() {
        // updates the hitboxex based on itemtype
        if(itemType == COIN) {
            updateHitbox(X_OFFSET_COIN, Y_OFFSET_COIN, WIDTH_OFFSET_COIN, HEIGHT_OFFSET_COIN);
        }
        else if(itemType == ROCKET) {
            updateHitbox(X_OFFSET_ROCKET, Y_OFFSET_ROCKET, ROCKET_WIDTH_OFFSET, ROCKET_HEIGHT_OFFSET);

        }
        else if(itemType == HEART) {
            updateHitbox(X_OFFSET_HEART, Y_OFFSET_HEART, WIDTH_OFFSET_HEART, HEIGHT_OFFSET_HEART);

        }
    }
    private void resetRocketTimer() {
        // sets rocketimer to 0 once player hits ground after rocket launch
        if(game.activeGame().getPlayer().onGround(game.activeGame().getGround().getHitBox())) {
            rocketTimer = 0;
        }
    }

    @Override
    public void update() {
        updateItemHitboxes();
        resetRocketTimer();
        initItemActions();

        if(game.activeGame().getPlayer().isFlying()) {
            rocketLaunch(game.activeGame().getPlayer());
            game.activeGame().updateScoreTick((int)ROCKET_SCORE_BONUS);
        } 
        updatePosition();
    }

    private void initItemActions() {
        // initializes item action if collision with player
        if(collisionDetected(game.activeGame().getPlayer().getHitBox(), this.hitBox)) { 
            switch(itemType) {
            case ROCKET:
                // inits rocketLaunch() by setting isFlying = true
                game.activeGame().getPlayer().setFlying(true);
                break;
            case COIN:
                // increases score by certain amount
                game.activeGame().updateScore(COIN_SCORE_BONUS);
                game.activeGame().updateCoins();
                break;
            case HEART:
                // adds a life to the life list
                game.activeGame().getLifeList().add(1);
                break;
                    
                }
                
        }
    }

    /**
     * launches rocket after collision between player and rocket item
     * @param player
     */
    public void rocketLaunch(Player player) {
        // sets the flying speed
        int FLYING_SPEED = -10;
        player.setPlayerAction(ROCKET_LAUNCH);

        // player moves up to a certain point
        if(player.getY() <= ROCKET_FLYING_HEIGHT) {
            player.setY(ROCKET_FLYING_HEIGHT);
            FLYING_SPEED = 0;
        }
        // when the player reaches maximum height, the player stays there until rocketTimer reaches threshold
        rocketTimer += 0.017f; // since the game is 60 FPS, it takes 5 seconds to reach threshold
        if(rocketTimer >= ROCKET_TIMER_THRESHOLD) {
            // player moves down when threshold is reached
            FLYING_SPEED = 10; 
           if(player.onGround(game.activeGame().getGround().getHitBox())) {
                FLYING_SPEED = 0; 
                
            } 
        }
        player.setYSpeed(FLYING_SPEED);
   
    }
    
}

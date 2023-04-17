package no.uib.inf101.sem2.inGameObjects.items;

import no.uib.inf101.sem2.view.Game;

import static no.uib.inf101.sem2.constants.ObjectConstants.ObjectDimensions.*;
import static no.uib.inf101.sem2.constants.sprites.ItemSprite.ItemType.*;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;
//import static constants.sprites.ItemEnum.*;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.Random;
import static no.uib.inf101.sem2.constants.sprites.PlayerSprite.PlayerAction.*;

import no.uib.inf101.sem2.inGameObjects.GameObjects;
import no.uib.inf101.sem2.inGameObjects.interfaces.DrawAndUpdate;
import no.uib.inf101.sem2.inGameObjects.player.Player;

public class Items extends GameObjects implements DrawAndUpdate {

    private final float X_OFFSET_COIN = ITEM_WIDTH * 1/4;
    private final float Y_OFFSET_COIN= ITEM_WIDTH* 1/3;
    private  final float WIDTH_OFFSET_COIN = - ITEM_WIDTH * 3/4;
    private  final float HEIGHT_OFFSET_COIN = - ITEM_HEIGHT * 3/4;

    private final float X_OFFSET_ROCKET = ITEM_WIDTH * 1/4;
    private final float Y_OFFSET_ROCKET= ITEM_WIDTH* 1/4;
    private  final float WIDTH_OFFSET_ROCET = - ITEM_WIDTH * 1/2;
    private  final float HEIGHT_OFFSET_ROCKET = - ITEM_HEIGHT * 1/2;

    private final float X_OFFSET_HEART = ITEM_WIDTH * 1/4;
    private final float Y_OFFSET_HEART= ITEM_WIDTH* 3/8;
    private  final float WIDTH_OFFSET_HEART = - ITEM_WIDTH * 5/8;
    private  final float HEIGHT_OFFSET_HEART = - ITEM_HEIGHT * 5/8;

    private static final int ROCKET_TIMER_THRESHOLD = 5; //5 seconds in air
    private float rocketTimer = 0; 

    private Player player;



    private int itemType;
    private Random rand;

    public static int COIN_SCORE_BONUS = 100;
    public static float ROCKET_SCORE_BONUS = 1f;
    public static float ROCKET_SPEED = 10;

    private final float EASY_ITEM_SPEED = 15;
    private final float MEDIUM_ITEM_SPEED = 20;
    private final float HARD_ITEM_SPEED = 25;
    private final float MEDIUM_ITEM_SPEED_THRESHOLD = 3000;
    private final float HARD_ITEM_SPEED_THRESHOLD = 6000;
   

    public Items(float x, float y, float width, float height, Game game) {
        super(x, y, width, height, game);
        loadAnimations(ITEM_SPRITE, 3,1,PIXEL_WIDTH, PIXEL_HEIGHT );
        initHitBox(x, y, width, height);

        //player = game.activeGame().getPlayer();
    }
    @Override
    public void draw(Graphics g) {
        //item = COIN;
        g.drawImage(animationArray[itemType][animationIndex], (int)x, (int)y, (int)width, (int)height, null);
        
        //drawHitBox(g);
    
        //g.drawString("x: " + this.x, 100, 100);
        
    }
    @Override
    public void updatePosition(){
        float deltaX = EASY_ITEM_SPEED;
    
        if(game.activeGame().getScore() > MEDIUM_ITEM_SPEED_THRESHOLD && game.activeGame().getScore() <= HARD_ITEM_SPEED_THRESHOLD) {
            deltaX = MEDIUM_ITEM_SPEED;
        }
        else if(game.activeGame().getScore() > HARD_ITEM_SPEED_THRESHOLD) {
            deltaX = HARD_ITEM_SPEED;
        }
          
        if(collisionDetected(game.activeGame().getPlayer().getHitBox()) && !game.activeGame().getPlayer().flying()) {
            deltaX = 0;
        }
        if(game.activeGame().getPlayer().flying()) {
            deltaX += 10;
        }
        x -= deltaX;

      
    }



    public int getItemType() {
        return itemType;
    }

    

    public boolean rocketDestroyed() {
        if(game.activeGame().getPlayer().getHitBox().intersects(hitBox)) {
            return true;
        }
        return false;
    }
    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    



    @Override
    public void update() {
        System.out.println(rocketTimer);
        player = game.activeGame().getPlayer();
        if(itemType == COIN) {
            updateHitbox(X_OFFSET_COIN, Y_OFFSET_COIN, WIDTH_OFFSET_COIN, HEIGHT_OFFSET_COIN);
        }
        else if(itemType == ROCKET) {
            updateHitbox(X_OFFSET_ROCKET, Y_OFFSET_ROCKET, WIDTH_OFFSET_ROCET, HEIGHT_OFFSET_ROCKET);

        }
        else if(itemType == HEART) {
            updateHitbox(X_OFFSET_HEART, Y_OFFSET_HEART, WIDTH_OFFSET_HEART, HEIGHT_OFFSET_HEART);

        }

        if(player.playerOnGround()) {
            rocketTimer = 0;
        }

    
        if(collisionDetected(game.activeGame().getPlayer().getHitBox())) { 

                switch(itemType) {
                    case ROCKET:
                        //game.activeGame().getPlayer().setYSpeed((int) -ROCKET_SPEED);
                        game.activeGame().getPlayer().setFlying(true);
                        
                        break;
                    case COIN:
                        game.activeGame().updateScore(COIN_SCORE_BONUS);
                        game.activeGame().updateCoins();
                        break;
                    case HEART:
                        game.activeGame().getLifeList().add(1);
                    
                }
                //game.getItem().remove(this);
        }
        if(game.activeGame().getPlayer().flying()) {
            rocketLaunch();
            game.activeGame().updateScoreTick((int)ROCKET_SCORE_BONUS);
        
            
        } 
        
       
        updatePosition();
        
    }

    private void rocketLaunch() {
        // gets called after collision with rocket item
        int ROCKET_SPEED = -10;
        int ROCKET_FLYING_HEIGHT = 200;
        
        game.activeGame().getPlayer().setPlayerAction(ROCKET_LAUNCH);

        // player moves up to a certain point
        if(player.getY() <= ROCKET_FLYING_HEIGHT) {
            player.setY(ROCKET_FLYING_HEIGHT);
            ROCKET_SPEED = 0;
             
        }
        // when the player reaches maximum height, the player stays there until rocketTimer reaches threshold
        rocketTimer += 0.017f; // since the game is 60 FPS, it takes 5 seconds to reach threshold
        if(rocketTimer >= ROCKET_TIMER_THRESHOLD) {
            // player moves down when threshold is reached
            ROCKET_SPEED = 10; 
           if(player.playerOnGround()) {
                ROCKET_SPEED = 0; 
            } 
        }
        player.setYSpeed(ROCKET_SPEED);
   
    }

   
   
    

    
}

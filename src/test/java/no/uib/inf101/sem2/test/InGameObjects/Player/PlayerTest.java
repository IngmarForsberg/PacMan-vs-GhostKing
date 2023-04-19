package no.uib.inf101.sem2.test.InGameObjects.Player;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.geom.Rectangle2D;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.inGameObjects.GameObjects;
import no.uib.inf101.sem2.inGameObjects.player.Player;
import no.uib.inf101.sem2.inGameObjects.terrain.Ground;
import no.uib.inf101.sem2.main.Game;

import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;



public class PlayerTest {
    Game game;
    @Test
    void testCollisionDetected() {
        // testing the collisionDetected() method

        // ground 1 and 2 should intersect
        Ground  ground1 = new Ground(0, 0, 10, 10,game);
        Ground  ground2 = new Ground(5, 5, 10, 10,game);
        // ground 3 and 4 touch, but doesn't interesect
        Ground  ground3 = new Ground(0, 0, 10, 10,game);
        Ground  ground4 = new Ground(10, 10, 10, 10,game);
        // ground 5 and 6 doesn't intersect
        Ground  ground5 = new Ground(0, 0, 10, 10,game);
        Ground  ground6 = new Ground(10, 10, 10, 10,game);
        
        
        assertTrue(ground1.collisionDetected(ground1.getHitBox(), ground2.getHitBox()));
        assertFalse(ground3.collisionDetected(ground3.getHitBox(), ground4.getHitBox()));
        assertFalse(ground5.collisionDetected(ground5.getHitBox(), ground6.getHitBox()));

    }

    @Test
    void onGroundTest() {
        // testing the onGround() method
        Ground ground = new Ground(0,10,50,5, new Game());
        // object 1 is on ground
        Ground object1 = new Ground(0, 0, 10, 10, new Game());
        // object 2 is 1 pixel under ground
        Ground object2 = new Ground(0, 0, 10, 11, new Game());
        // object 3 is 1 pixel over ground
        Ground object3 = new Ground(0, 0, 5, 9, new Game());
        assertTrue(object1.onGround(ground.getHitBox()));
        assertFalse(object2.onGround(ground.getHitBox()));
        assertFalse(object3.onGround(ground.getHitBox()));

        
    }
    @Test
    void positionAfterCollision() {
        // testing the positionAfterCollision method
        Ground ground = new Ground(GROUND_START_X, GROUND_Y_POSITION, GROUND_WIDTH, GROUND_HEIGHT, new Game());
        Player player = new Player(PLAYER_START_X_POSITION, PLAYER_START_Y_POSITION, PLAYER_WIDTH, PLAYER_HEIGHT, new Game()); 
        
        // I set a ySpeed that is greater than the distance between player and ground
        player.setYSpeed(100);
        // if I simply add the ySpeed to the player position, the player should be under ground, and onGround() should be false
        player.setY(player.getY() + player.getYSpeed());
        assertFalse(player.onGround(ground.getHitBox()));
        // now I reset the y position
        player.setY(PLAYER_START_Y_POSITION);

        // the positionAfterCollision() method should make the player stop falling with the current ySpeed
        // once a collision is detected
        player.positionAfterCollision(Y_OFFSET, ground.getHitBox());
        // player should now be on ground
        assertTrue(player.onGround(ground.getHitBox()));
        // the ySpeed should now be 0
        assertTrue(player.getYSpeed() == 0);
    }

    @Test
    void jumpTest() {
        // testing the jumping motion, from start to finish
        Ground ground = new Ground(GROUND_START_X, GROUND_Y_POSITION, GROUND_WIDTH, GROUND_HEIGHT, new Game());
        Player player = new Player(PLAYER_START_X_POSITION, PLAYER_START_Y_POSITION, PLAYER_WIDTH, PLAYER_HEIGHT, new Game()); 
        // make sure player is on ground
        while(!player.onGround(ground.getHitBox())) {
            player.updatePosition();
        } 
        assertTrue(player.onGround(ground.getHitBox()));
        // when on ground, the canJump booelan should be true
        assertTrue(player.canJump());
        // now I simulate pressing both crouch and jump at the same time. The ySpeed should'nt change
        player.setJump(true);
        player.setCrouch(true);
        assertTrue(player.getYSpeed() == 0);

        // when setJump is true, and canJump is true,  and not crouch, the ySpeed should be directed upwards (negative direction)
        player.setCrouch(false);
        player.updatePosition();
        assertTrue(player.getYSpeed() < 0);

        // even though setJump is true, the player is still on ground, and isJumping should still be false
        assertFalse(player.isJumping());
        
        // updates until player leaves ground
        while(player.onGround(ground.getHitBox())) {
            player.updatePosition();
        }
        // now, the isJumping boolean should be true, and canJump should be false
        assertTrue(player.isJumping());
        assertFalse(player.canJump());
        // updates the player until it reaches ground again
        while(!player.onGround(ground.getHitBox())) {
            player.updatePosition();
        }
        //now isJumping should be false, and canJump should be true again
        assertFalse(player.isJumping());
        assertTrue(player.canJump());
        
    }

    
}

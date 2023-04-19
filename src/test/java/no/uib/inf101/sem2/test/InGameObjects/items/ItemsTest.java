package no.uib.inf101.sem2.test.InGameObjects.items;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.gamestates.ActiveGame;
import no.uib.inf101.sem2.inGameObjects.items.Items;
import no.uib.inf101.sem2.inGameObjects.player.Player;
import no.uib.inf101.sem2.inGameObjects.terrain.Ground;
import no.uib.inf101.sem2.main.Game;

import java.util.ArrayList;

import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;
import static no.uib.inf101.sem2.constants.sprites.ItemSprite.ItemType.*;

public class ItemsTest {
    
    @Test
    void ItemListTest() {
          // check the update and reset of the item list
          // almost same method for ghost list
        ActiveGame activeGame = new ActiveGame(new Game());
        activeGame.update();

        // check if size of itemlist is equal to rockets, coins and hearts added together
        assertEquals(NUM_ITEMS*3, activeGame.getItemList().size());

        // now I set all the items with an out of bounds x value
        for(Items item : activeGame.getItemList()) {
          item.setX(-200);
        }
        // the resetItems() method should set the x position to a random positive value
        activeGame.resetItems();
        assertFalse(outOfBounds(activeGame.getItemList()));
        // the list size should stay the same
        assertEquals(NUM_ITEMS*3, activeGame.getItemList().size());
        
    }
    boolean outOfBounds(ArrayList<Items> itemsList) {
      // method that checks if item is out of bounds, if their x value is negative
      for(Items item : itemsList) {
        if(item.getX() < 0) {
          return true;
        }

      }
      return false;
    }

    @Test 
    void rocketLaunchTest() {
      // testing rocketLaunch() method
      Player player = new Player(PLAYER_START_X_POSITION, PLAYER_START_Y_POSITION, PLAYER_WIDTH, PLAYER_PIXEL_HEIGHT, new Game());
      Items item = new Items(0,0,50,50,new Game());

      // immediately after rocketLaunch() is called, the player's ySpeed should be upwards
      item.rocketLaunch(player);
      assertTrue(player.getYSpeed() < 0);

      // the player should stop at ROCKET_FLYING_HEIGHT
      while(player.getYSpeed() < 0) {
        item.rocketLaunch(player);
        player.setY(player.getY() + player.getYSpeed());
      }
     
      assertEquals(player.getY(), ROCKET_FLYING_HEIGHT);

      // when the rocket descends, it should stop when it hits ground
      while(player.getXSpeed() >= 0) {
        item.rocketLaunch(player);
        player.setY(player.getY() + player.getYSpeed());
      }

      assertEquals(player.getY(), PLAYER_START_Y_POSITION);
      

      

    }
    
}

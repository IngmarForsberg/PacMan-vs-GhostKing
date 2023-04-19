package no.uib.inf101.sem2.test.InGameObjects.Ghosts;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.gamestates.ActiveGame;
import no.uib.inf101.sem2.inGameObjects.ghosts.Ghost;
import no.uib.inf101.sem2.inGameObjects.items.Items;
import no.uib.inf101.sem2.inGameObjects.player.Player;
import no.uib.inf101.sem2.inGameObjects.terrain.Ground;
import no.uib.inf101.sem2.main.Game;

import java.util.ArrayList;

import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;
import static no.uib.inf101.sem2.constants.sprites.ItemSprite.ItemType.*;


public class GhostTest {

    @Test
    void ghostStopTest() {
        ActiveGame activeGame = new ActiveGame(new Game());
        Ghost ghost = activeGame.getGhostList().get(0);
        ghost.updatePosition();
        // the ghost should start with a certain xSpeed
        assertTrue(ghost.getXSpeed() > 0);

        // when ghost collides with player, I want it to stop
        ghost.getHitBox().x = activeGame.getPlayer().getHitBox().x - 5;
        ghost.updatePosition();
        
        assertEquals(ghost.getXSpeed(),0);

    }


    
}

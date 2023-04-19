package no.uib.inf101.sem2.test.GameOver;
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

public class GameOverTest {

    @Test
    void gameOverTest() {
        ActiveGame activeGame = new ActiveGame(new Game());
        // I want the game to end right after collision with ghost king. 
        // so even though lifelist is empty, the game shouldnt end unless collision with ghost king
        activeGame.getLifeList().remove(0);
        activeGame.getLifeList().remove(0);
        activeGame.getLifeList().remove(0);
        assertFalse(activeGame.gameOver());
        
        // I want the game to end when player and ghost king collide, and when there is only one life left
        activeGame.getLifeList().add(1);
        activeGame.getGhostKing().getHitBox().x = activeGame.getPlayer().getHitBox().x + 10;
        assertTrue(activeGame.gameOver());
    }

    
}

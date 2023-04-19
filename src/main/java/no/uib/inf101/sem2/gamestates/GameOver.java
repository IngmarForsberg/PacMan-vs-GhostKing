package no.uib.inf101.sem2.gamestates;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.Rectangle2D;
import static no.uib.inf101.sem2.estethics.Fonts.*;

import no.uib.inf101.sem2.inGameObjects.interfaces.StateMethods;
import no.uib.inf101.sem2.main.Game;

import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;

public class GameOver extends State implements StateMethods {

    public GameOver(Game game) {
        super(game);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        // the strings which are displayed on game over screen
        String[] strings = {
            "GAME OVER",
            "Total score: " + game.activeGame().getScore(),
            "Press SPACE to play again",
            "Press ESC to return to menu"
          };
          // creates a black canvas
          Rectangle2D canvas = new Rectangle2D.Double(0, 0, GAME_WIDTH, GAME_HEIGHT);
          g2D.setColor(Color.BLACK);
          g2D.fill(canvas);
          g2D.setColor(Color.YELLOW);
          g2D.setFont(customFont());
          String newHighScore = "NEW HIGHSCORE!";
          int stringWidth = g2D.getFontMetrics().stringWidth(newHighScore);

          // draws new highscore
          if(game.activeGame().isNewHighscore()) {
            g2D.drawString(newHighScore, ((int)GAME_WIDTH - stringWidth) / 2, (int)60*SCALE);
          }
          
      
          int gapBetweenStrings = 0;
          
          for (String string : strings) {
            stringWidth = g2D.getFontMetrics().stringWidth(string);
            //drawing the string in the middle position
            g2D.drawString(string, ((int)GAME_WIDTH - stringWidth) / 2, (int)GAME_HEIGHT / 2 - 12*SCALE + gapBetweenStrings);
            
            gapBetweenStrings += 24*SCALE;
      
          }
        
    }

   

}
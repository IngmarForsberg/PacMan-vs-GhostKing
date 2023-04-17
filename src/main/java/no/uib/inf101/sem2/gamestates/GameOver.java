package no.uib.inf101.sem2.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import static no.uib.inf101.sem2.estethics.Fonts.*;

import no.uib.inf101.sem2.view.Game;
import static no.uib.inf101.sem2.constants.ObjectConstants.ObjectDimensions.*;

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
        String[] strings = {
            "GAME OVER",
            "Total score: " + game.activeGame().getScore(),
            "Press SPACE to play again",
            "Press ESC to return to menu"
          };
      
          Rectangle2D canvas = new Rectangle2D.Double(0, 0, GAME_WIDTH, GAME_HEIGHT);
          g2D.setColor(Color.BLACK);
          g2D.fill(canvas);
          g2D.setColor(Color.YELLOW);
          g2D.setFont(customFont());
          String newHighScore = "NEW HIGHSCORE!";
          int stringWidth = g2D.getFontMetrics().stringWidth(newHighScore);

          if(game.activeGame().isNewHighscore()) {
            g2D.drawString(newHighScore, ((int)GAME_WIDTH - stringWidth) / 2, 150);
          }
          
      
          int gapBetweenStrings = 0;
      
          for (String string : strings) {
            stringWidth = g2D.getFontMetrics().stringWidth(string);
            //drawing the string in the middle position
            g2D.drawString(string, ((int)GAME_WIDTH - stringWidth) / 2, (int)GAME_HEIGHT / 2 - 30 + gapBetweenStrings);
            //g2D.setFont(new Font("Verdana", Font.BOLD, 30));
            gapBetweenStrings += 60;
      
          }
        
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

}
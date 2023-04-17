package no.uib.inf101.sem2.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import no.uib.inf101.sem2.view.Game;
import static no.uib.inf101.sem2.constants.ObjectConstants.ObjectDimensions.*;
import static no.uib.inf101.sem2.estethics.Fonts.*;
import static no.uib.inf101.sem2.ui.MenuButton.ButtonHitbox.*;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;

public class HowTo extends State implements StateMethods {
    
    private BufferedImage backButton;
   

    public HowTo(Game game) {
        super(game);
        backButton = getSprite(BACK_SPRITE).getSubimage(0, 0, BACK_PIXEL_WIDTH, BACK_PIXEL_HEIGHT);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        String[] strings = {
            "Oh no!",
            "Pacman is on the run again,",
            "and this time he is chased by the mighty Ghost King!",
            "Help him escape by avoiding his little ghost soldiers!",
            "Press DOWN arrow to duck and UP arrow or SPACE to jump.",
            "Pick up coins on the way to gain extra points.",
            "Extra lives will also help you on your path.",
            "Need a brake? Press ESC to pause.",
            "Are things going too slow? Pick up a rocket!",
            "Good luck!"
          };
      
          Rectangle2D canvas = new Rectangle2D.Double(0, 0, GAME_WIDTH, GAME_HEIGHT);
          g2D.setColor(Color.BLACK);
          g2D.fill(canvas);
          g2D.setColor(Color.YELLOW);
          Font font = customFont().deriveFont(20f);
          g2D.setFont(font);
          g.drawImage(backButton, BACK_X, BACK_Y, BACK_WIDTH, BACK_HEIGHT, null);

        
      
          int gapBetweenStrings = 0;
      
          for (String string : strings) {
            int stringWidth = g2D.getFontMetrics().stringWidth(string);
            //drawing the string in the middle position
            g2D.drawString(string, (int)100, (int)120 + gapBetweenStrings);
            //g2D.setFont(new Font("Verdana", Font.BOLD, 30));
            gapBetweenStrings += 50;
      
          }
        
       
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }
    
}

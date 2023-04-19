package no.uib.inf101.sem2.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import static no.uib.inf101.sem2.estethics.Fonts.*;

import no.uib.inf101.sem2.inGameObjects.interfaces.StateMethods;
import no.uib.inf101.sem2.main.Game;

import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;


public class Pause extends State implements StateMethods { 
    private Game game;
    public Pause(Game game) {
        super(game);
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void draw(Graphics g) {
        
        Graphics2D g2D = (Graphics2D) g;
        Rectangle2D canvas = new Rectangle2D.Double(0,0,GAME_WIDTH, GAME_HEIGHT);
        g2D.setColor(new Color(0,0,0,200));
        g2D.fill(canvas);

        String string = "PAUSE";
        int stringWidth = g2D.getFontMetrics().stringWidth(string);
        
        g.setColor(Color.YELLOW);
        g.setFont(customFont());
        g.drawString(string, (int)GAME_WIDTH/2 - stringWidth/2, (int)GAME_HEIGHT/2);
    
    
    }

    
}

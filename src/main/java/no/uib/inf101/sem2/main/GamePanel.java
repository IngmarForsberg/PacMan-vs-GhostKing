package no.uib.inf101.sem2.main;
import javax.imageio.ImageIO;
import javax.swing.*;

import no.uib.inf101.sem2.gamestates.GameStates;
import no.uib.inf101.sem2.inputs.KeyBoardInputs;
import no.uib.inf101.sem2.inputs.MouseInputs;

import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;

import java.awt.*;
import java.util.ArrayList;

/* import static view.Game.GAME_HEIGHT;
import static view.Game.GAME_WIDTH; */






public class GamePanel extends JPanel{
    
    private static final Dimension SIZE = new Dimension((int) GAME_WIDTH, (int) GAME_HEIGHT);
    private Game game;
    private GameStates gameState;

    JLabel scoreLabel;
    
  
    public GamePanel(Game game) {
        this.game = game;
        setPreferredSize(SIZE);
        setBackground(Color.BLACK);
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(new MouseInputs(this));
        setFocusable(true);
        
    }

    public Game getGame() {
        return game;
    }
    public void paintComponent(Graphics g) {   
        super.paintComponent(g);
        game.render(g);
    }

    
}

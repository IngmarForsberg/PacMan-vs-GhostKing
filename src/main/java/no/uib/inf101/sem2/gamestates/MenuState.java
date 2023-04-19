package no.uib.inf101.sem2.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import no.uib.inf101.sem2.inGameObjects.interfaces.StateMethods;
import no.uib.inf101.sem2.main.Game;

import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;
import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;
import static no.uib.inf101.sem2.constants.Menu.MenuLayout.*;

public class MenuState extends State implements StateMethods {
    private BufferedImage playButton;
    private BufferedImage howToButton;
    
    private BufferedImage title;
    private Rectangle2D backround;
   
    private BufferedImage pacman;
    private BufferedImage ghostKing;
    

    public MenuState(Game game) {
        super(game);
        backround = new Rectangle2D.Double(0, 0, GAME_WIDTH, GAME_HEIGHT);
        
        playButton = getSprite(PLAY_SPRITE).getSubimage(0, 0, PLAY_PIXEL_WIDTH, PLAY_PIXEL_HEIGHT);
        howToButton = getSprite(HOW_TO_SPRITE).getSubimage(0, 0, HOW_TO_PIXEL_WIDTH, HOW_TO_PIXEL_HEIGHT);
        title = getSprite(TITLE_SPRITE).getSubimage(0, 0, TITLE_PIXEL_WIDTH, TITLE_PIXEL_HEIGHT);  
        pacman = getSprite(PACMAN_MENU_SPRITE).getSubimage(0, 0, PACMAN_PIXEL_WIDTH, PACMAN_PIXEL_HEIGHT);
        ghostKing = getSprite(GHOST_KING_MENU_SPRITE).getSubimage(0, 0, GHOST_KING_PIXEL_WIDTH, GHOST_KING_PIXEL_HEIGHT);
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);
        g2D.fill(backround);

        g2D.drawImage(playButton, PLAY_X, PLAY_Y, PLAY_WIDTH, PLAY_HEIGHT, null);
        g2D.drawImage(howToButton, HOW_TO_X, HOW_TO_Y, HOW_TO_WIDTH, HOW_TO_HEIGHT, null);
        g2D.drawImage(title, TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT, null);
        g2D.drawImage(pacman, PACMAN_MENU_X, PACMAN_MENU_Y, PACMAN_MENU_WIDTH, PACMAN_MENU_HEIGHT, null);
        g2D.drawImage(ghostKing, GHOST_KING_MENU_X, GHOST_KING_MENU_Y, GHOST_KING_WIDTH_MENU, GHOST_KING_HEIGHT_MENU, null);
        
    }
    
}

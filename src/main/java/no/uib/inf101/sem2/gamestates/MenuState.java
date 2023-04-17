package no.uib.inf101.sem2.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import static no.uib.inf101.sem2.ui.MenuButton.ButtonHitbox.*;

import no.uib.inf101.sem2.view.Game;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;
import static no.uib.inf101.sem2.constants.ObjectConstants.ObjectDimensions.*;

public class MenuState extends State implements StateMethods {
    private BufferedImage playButton;
    private BufferedImage howToButton;
    //private BufferedImage quitButton;
    private BufferedImage backButton;
    private BufferedImage title;
    private Rectangle2D backround;
    private Game game;
    private BufferedImage pacman;
    private BufferedImage ghostKing;
    
    private BufferedImage imageArray[];
   



    public MenuState(Game game) {
        super(game);
        backround = new Rectangle2D.Double(0, 0, GAME_WIDTH, GAME_HEIGHT);
       /*  imageArray = new BufferedImage[4];
        BufferedImage image = getSprite(MENU_SPRITE);
        for(int i = 0; i< imageArray.length;i++) {
            imageArray[i] = image.getSubimage(0, i*PIXEL_HEIGHT, PIXEL_WIDTH, PIXEL_HEIGHT); 
        } */ 
        playButton = getSprite(PLAY_SPRITE).getSubimage(0, 0, PLAY_PIXEL_WIDTH, PLAY_PIXEL_HEIGHT);
        howToButton = getSprite(HOW_TO_SPRITE).getSubimage(0, 0, HOW_TO_PIXEL_WIDTH, HOW_TO_PIXEL_HEIGHT);
        /* quitButton = imageArray[2];
        backButton = imageArray[3]; */
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

        g2D.drawImage(playButton, PLAY_X, BUTTON_START_Y, PLAY_WIDTH, PLAY_HEIGHT, null);
        g2D.drawImage(howToButton, HOW_TO_X, HOW_TO_Y, HOW_TO_WIDTH, HOW_TO_HEIGHT, null);
        //g.drawImage(quitButton, BUTTON_X, QUIT_Y, BUTTON_WIDTH, BUTTON_HEIGHT, null);
        g2D.drawImage(title, TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT, null);
        g2D.drawImage(pacman, 20, 140, PACMAN_WIDTH, PACMAN_HEIGHT, null);
        g2D.drawImage(ghostKing, 550, 80, GHOST_KING_WIDTH_MENU, GHOST_KING_HEIGHT_MENU, null);
        
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }
    
}

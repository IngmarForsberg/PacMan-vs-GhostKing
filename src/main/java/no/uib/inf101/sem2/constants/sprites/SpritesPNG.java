package no.uib.inf101.sem2.constants.sprites;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class SpritesPNG {

    public static final int PIXEL_WIDTH = 32, PIXEL_HEIGHT = 32;
    public static final String PLAYER_SPRITE = "pacman.png";
    public static final String GHOST_SPRITE = "ghosts.png";
    public static final String GHOST_KING_SPRITE = "ghost_king.png";
    public static final String ITEM_SPRITE = "items.png";
    //public static final String MENU_SPRITE = "menu.png";
    public static final String TITLE_SPRITE = "title.png";
    public static final String PLAY_SPRITE = "play.png";
    public static final String HOW_TO_SPRITE = "how_to.png";
    public static final String BACK_SPRITE = "back.png";
    public static final String PACMAN_MENU_SPRITE = "pacman_menu.png";
    public static final String GHOST_KING_MENU_SPRITE = "ghost_king_menu.png";
    

    public static BufferedImage getSprite(String fileName) {
        BufferedImage image = null;
        File file = new File("src/main/resources/" + fileName);
        try {
            image = ImageIO.read(file); 
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

        return image;
        
    }

    
}

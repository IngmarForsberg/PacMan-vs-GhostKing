package no.uib.inf101.sem2.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;
import static no.uib.inf101.sem2.constants.ObjectConstants.ObjectDimensions.*;


import no.uib.inf101.sem2.gamestates.GameStates;

public class MenuButton {
    public static class ButtonHitbox {
        public static final int PIXEL_WIDTH = 26;
        public static final int PIXEL_HEIGHT = 9;
        public static final int TITLE_PIXEL_WIDTH = 712;
        public static final int TITLE_PIXEL_HEIGHT = 53;
        public static final int TITLE_WIDTH = (int)(TITLE_PIXEL_WIDTH *SCALE*3/5);
        public static final int TITLE_HEIGHT = (int)(TITLE_PIXEL_HEIGHT*SCALE* 3/5);

        public static final int PLAY_PIXEL_WIDTH = 168;
        public static final int PLAY_PIXEL_HEIGHT = 53;
        public static final int PLAY_WIDTH = (int)(PLAY_PIXEL_WIDTH * SCALE*2/5);
        public static final int PLAY_HEIGHT = (int)(PLAY_PIXEL_HEIGHT* SCALE*2/5);

        public static final int HOW_TO_PIXEL_WIDTH = 252;
        public static final int HOW_TO_PIXEL_HEIGHT = 53;
        public static final int HOW_TO_WIDTH = (int)(HOW_TO_PIXEL_WIDTH * SCALE*2/5);
        public static final int HOW_TO_HEIGHT = (int)(HOW_TO_PIXEL_HEIGHT*SCALE*2/5);

        public static final int TITLE_Y = (int)SCALE * 45;
        public static final int TITLE_X = (int)SCALE*50;
        public static final int BUTTON_WIDTH = 5*(int)SCALE * PIXEL_WIDTH;
        public static final int BUTTON_HEIGHT = 5*(int)SCALE * PIXEL_HEIGHT;

        
        public static final int PLAY_CENTER = PLAY_WIDTH/2;
        public static final int HOW_TO_CENTER = HOW_TO_WIDTH/2;
        public static final int PLAY_X = (int)(GAME_WIDTH/2 - PLAY_CENTER);
        public static final int HOW_TO_X = (int)(GAME_WIDTH/2 - HOW_TO_CENTER);
        public static final int BUTTON_START_Y = 150*(int) SCALE;
        public static final int BUTTON_SPACING = 60*(int)SCALE;
        public static final int HOW_TO_Y = BUTTON_START_Y + BUTTON_SPACING;
        public static final int QUIT_Y = HOW_TO_Y + BUTTON_SPACING;
        //public static Graphics g;
        public static final int BACK_PIXEL_WIDTH = 168;
        public static final int BACK_PIXEL_HEIGHT = 53;
        public static final int BACK_WIDTH = (int)(BACK_PIXEL_WIDTH*SCALE*1/5);
        public static final int BACK_HEIGHT = (int)(BACK_PIXEL_HEIGHT*SCALE*1/5);
        public static final int BACK_X = (int)(SCALE * 20);
        public static final int BACK_Y = (int)(SCALE * 15);

        public static final int GHOST_KING_PIXEL_WIDTH_MENU= 50;
        public static final int GHOST_KING_PIXEL_HEIGHT_MENU = 50;
        public static final int PACMAN_PIXEL_WIDTH = 32;
        public static final int PACMAN_PIXEL_HEIGHT = 32;

        public static final int GHOST_KING_WIDTH_MENU = (int)(SCALE*GHOST_KING_PIXEL_WIDTH*5);
        public static final int GHOST_KING_HEIGHT_MENU = (int)(SCALE*GHOST_KING_PIXEL_HEIGHT*5);
        public static final int PACMAN_WIDTH = (int)(SCALE * PACMAN_PIXEL_WIDTH)*5;
        public static final int PACMAN_HEIGHT = (int)(SCALE * PACMAN_PIXEL_HEIGHT*5);
    

        

    }
    

}
 
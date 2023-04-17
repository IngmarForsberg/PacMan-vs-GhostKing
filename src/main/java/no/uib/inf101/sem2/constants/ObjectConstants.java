package no.uib.inf101.sem2.constants;

public class ObjectConstants {
    
    public static class ObjectDimensions{
         public static final int GHOST_KING_PIXEL_WIDTH = 50;
        public static final int GHOST_KING_PIXEL_HEIGHT = 50;
        /* public static final int PACMAN_PIXEL_WIDTH = 32;
        public static final int PACMAN_PIXEL_HEIGHT = 32; */
 
        public final static float SCALE = 2.5f;
        // frame dimensions
        public final static float GAME_WIDTH = 500 * SCALE;
        public final static float GAME_HEIGHT = 240* SCALE;

        public static final float GROUND_Y_POSITION = GAME_HEIGHT * 2/3;

        //player dimensions
        public static final float PLAYER_START_X_POSITION = GAME_WIDTH*3/8 + 50;
        public static final float PLAYER_HEIGHT = 40 * SCALE, PLAYER_WIDTH = 40 * SCALE;

        // ghost king dimensions
        public static final float GHOST_KING_WIDTH = (160*SCALE), GHOST_KING_HEIGHT = (160 * SCALE) ;
        public static final float GHOST_KING_X = -20, GHOST_KING_Y = 200;
        
    
        // ghost dimensions and positions
        public static final float GHOST_HEIGHT = 40*SCALE, GHOST_WIDTH = 40*SCALE;
        public static final float GHOST_BOTTOM_POSTION = GROUND_Y_POSITION -25, GHOST_TOP_POSITION = GROUND_Y_POSITION + 20;
        public static final float GHOST_MAX_X = 700, GHOST_MIN_X = 400;
        public static final int NUM_GHOSTS = 10;

        // items dimensions and positions
        public static final float ITEM_WIDTH = 40 * SCALE, ITEM_HEIGHT = 40 * SCALE;
        public static final float ITEM_Y_POSITION = GROUND_Y_POSITION;
        
        public static final float ROCKET_MIN_X = 10000, ROCKET_MAX_X = 20000;
        public static final float COIN_MIN_X = 2000, COIN_MAX_X =  3000;
        public static final float HEART_MIN_X = 15000, HEART_MAX_X =  25000;
        public static final int STARTING_LIFE = 3;
        


        public static final int NUM_ITEMS = 10;
  
        
        
    
    }
    
}

package no.uib.inf101.sem2.constants;

public class InGameObjects {
    
    public static class DimensionsAndSpeeds{
        // animation tick threshold
        public static final int ANIMATION_SPEED = 5;

        //score
        public static final float SCORE_TICK_INTERVAL = 0.2f; // 0.2 seconds between score tick

        // FPS
        public static final int UPDATE_INTERVAL = 17; // gives FPS of 60

        // frame 
        public final static float SCALE = 2.5f;
        
        public final static float GAME_WIDTH = 500 * SCALE;
        public final static float GAME_HEIGHT = 240* SCALE;

        // ground

        public static final float GROUND_Y_POSITION = GAME_HEIGHT -50*SCALE;
        public static final float GROUND_START_X = - SCALE;
        public static final float GROUND_WIDTH = GAME_WIDTH + SCALE;
        public static final float GROUND_HEIGHT = 4*SCALE;

        //player 
        public static final int PLAYER_PIXEL_WIDTH = 32, PLAYER_PIXEL_HEIGHT = 32;
        public static final float PLAYER_START_X_POSITION = 200*SCALE; 
        public static final float PLAYER_HEIGHT = 40 * SCALE , PLAYER_WIDTH = 40 * SCALE;
        public static final float JUMP_SPEED = -5*SCALE;
        public static final float GRAVITY = 0.3f * SCALE;
        public static final int FALLING_BOOST = 6*(int) SCALE;
       
        public static final float X_OFFSET = PLAYER_WIDTH *1/4;
        public static final float Y_OFFSET = PLAYER_HEIGHT*1/8;
        public static final float WIDTH_OFFSET =  - PLAYER_WIDTH*0.5f;
        public static final float HEIGHT_OFFSET = - PLAYER_HEIGHT * 3/8; 

        public static final float PLAYER_Y_OFFSET_CROUCHING = PLAYER_HEIGHT*1/3; 
        public static final float PLAYER_HEIGHT_OFFSET_CROUCHING= - PLAYER_HEIGHT*1/2;
        public static final float PLAYER_START_Y_POSITION = GROUND_Y_POSITION - PLAYER_HEIGHT;

        // ghost king 
        public static final int GHOST_KING_PIXEL_WIDTH = 50;
        public static final int GHOST_KING_PIXEL_HEIGHT = 50;
        public static final float GHOST_KING_WIDTH = (160*SCALE), GHOST_KING_HEIGHT = (160 * SCALE) ;
        public static final float GHOST_KING_X = -5*SCALE, GHOST_KING_Y = 80*SCALE;
        public static final float GHOST_KING_SPEED = 4 *SCALE;
    
        // ghost 
        public static final int GHOST_PIXEL_WIDTH = 32, GHOST_PIXEL_HEIGHT = 32;
        public static final float GHOST_HEIGHT = 40*SCALE, GHOST_WIDTH = 40*SCALE;

        public static final float GHOST_X_OFFSET = GHOST_WIDTH * 1/8;
        public static final float GHOST_GHOST_WIDTH_OFFSET = - GHOST_WIDTH * 5/8;
        public static final float GHOST_HEIGHT_OFFSET = - GHOST_WIDTH * 1/2;

        public static final float GHOST_SPEED = 6 * SCALE;
        public static final float GHOST_BOTTOM_Y = GROUND_Y_POSITION - 0.8f*GHOST_HEIGHT;
        public static final float GHOST_TOP_Y = GROUND_Y_POSITION - 1.2f*GHOST_HEIGHT;
        public static final float GHOST_MAX_X = 280*SCALE, GHOST_MIN_X = 160*SCALE;
        public static final int NUM_GHOSTS = 10;

        // items
        public static final int ITEM_PIXEL_WIDTH = 32, ITEM_PIXEL_HEIGHT = 32;
        public static final float ITEM_WIDTH = 40 * SCALE, ITEM_HEIGHT = 40 * SCALE;
        public static final float ITEM_Y_POSITION = GROUND_Y_POSITION - ITEM_HEIGHT;
        public static final int NUM_ITEMS = 10;
        public static final float ITEM_SPEED = 6*SCALE;

        // rocket
        public static final float ROCKET_MIN_X = 4000*SCALE, ROCKET_MAX_X = 8000*SCALE;
        public static final float ROCKET_FLYING_HEIGHT = 80*SCALE;
        public static final float X_OFFSET_ROCKET = ITEM_WIDTH * 1/4;
        public static final float Y_OFFSET_ROCKET= ITEM_WIDTH* 1/4;
        public static final float ROCKET_WIDTH_OFFSET = - ITEM_WIDTH * 1/2;
        public static final float ROCKET_HEIGHT_OFFSET = - ITEM_HEIGHT * 1/2;
        public static final int ROCKET_TIMER_THRESHOLD = 5; //5 seconds in air
        public static final float ROCKET_SPEED = 4*SCALE;

        // coin
        public static final float COIN_MIN_X = 800*SCALE, COIN_MAX_X = 1200*SCALE;
        
        public static final float X_OFFSET_COIN = ITEM_WIDTH * 1/4;
        public static final float Y_OFFSET_COIN= ITEM_WIDTH* 1/3;
        public static final float WIDTH_OFFSET_COIN = - ITEM_WIDTH * 3/4;
        public static final float HEIGHT_OFFSET_COIN = - ITEM_HEIGHT * 3/4;

        // heart
        
        public static final float HEART_MIN_X = 6000*SCALE, HEART_MAX_X = 10000*SCALE;
        public static final int STARTING_LIFE = 3;
        public static final float X_OFFSET_HEART = ITEM_WIDTH * 1/4;
        public static final float Y_OFFSET_HEART= ITEM_WIDTH* 3/8;
        public static final float WIDTH_OFFSET_HEART = - ITEM_WIDTH * 5/8;
        public static final float HEIGHT_OFFSET_HEART = - ITEM_HEIGHT * 5/8;

        

        // difficulty 

        public static final float MEDIUM_SPEED_ADDER = 2*SCALE;
        public static final float HARD_SPEED_ADDER = 4*SCALE;

        public static final float MEDIUM_DISTANCE_MULTIPLIER = 20/15;
        public static final float HARD_DISTANCE_MULTIPLIER = 25/15;

        public static final float MEDIUM_SCORE_THRESHOLD = 3000;
        public static final float HARD_SCORE_THRESHOLD = 6000;


       
        

    }
    

  
    
}

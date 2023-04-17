package no.uib.inf101.sem2.gamestates;

import no.uib.inf101.sem2.view.Game;
import static no.uib.inf101.sem2.constants.ObjectConstants.ObjectDimensions.*;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;
import java.util.ArrayList;
import java.util.Random;
import static no.uib.inf101.sem2.constants.sprites.ItemSprite.ItemType.*;
import no.uib.inf101.sem2.inGameObjects.ghosts.Ghost;
import no.uib.inf101.sem2.inGameObjects.items.Items;
import no.uib.inf101.sem2.inGameObjects.ghosts.GhostKing;
import no.uib.inf101.sem2.inGameObjects.player.Player;
import no.uib.inf101.sem2.terrain.Ground;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static no.uib.inf101.sem2.estethics.Fonts.*;

public class ActiveGame extends State implements StateMethods {
   
    private ArrayList<Ghost> ghostList;
    private int score;
    private int highScore;
    private float scoreCounter = 0;
    private Player player;
    private GhostKing ghostKing;
    private ArrayList<Items> itemsList;
    private ArrayList<Integer> lifeList;
    private ArrayList<Integer> coinList;
    private Ground ground;

    private boolean isNewHighscore;

    private static final float SCORE_TICK_INTERVAL = 0.2f; //0.2 seconds between score tick
    
    public ActiveGame(Game game) {
        super(game);
        initGameObjects();  
    }

    private void initGameObjects() {
        player = new Player(PLAYER_START_X_POSITION, GROUND_Y_POSITION-50, PLAYER_WIDTH, PLAYER_HEIGHT, game);
        ghostKing = new GhostKing(GHOST_KING_X, GHOST_KING_Y, GHOST_KING_WIDTH, GHOST_KING_HEIGHT, game); 
        ground = new Ground(-20f, GROUND_Y_POSITION + player.getHeight(), GAME_WIDTH + 100f, (float)10, game);
        createItemList();
        createGhosts();
        defaultLife(STARTING_LIFE);
        coinList = new ArrayList<>();
    }

    /**
     * adds an integer with value 1 to the coin list. The value represents a single coin.
     */
    public void updateCoins() {
        coinList.add(1);
    }

    private void defaultLife(int lifeAmount) {
        // creates an array with the number of starting lives the player has
        lifeList = new ArrayList<>();
        for(int i = 0; i < lifeAmount; i++) {
            lifeList.add(1); // integer with value 1 represents a single life
        }

    }

    private float randomXPosition(float max, float min) {
        // returns a random float between max and min
        Random rand = new Random();
        return rand.nextInt((int) max - (int)min) + min;
    }

    private float randomGhostYPosition() {
        // returns a random y position of the two possible positions the ghosts have
        float[] randomYValues = {GHOST_BOTTOM_POSTION, GHOST_TOP_POSITION};
        return randomYValues[new Random().nextInt(randomYValues.length)];
    }

    private void createItems(int itemType, float xMax,float xMin) {
        // the item's starting position is always passed the width of the frame
        float startX = GAME_WIDTH + randomXPosition(xMax, xMin);
        
        // the loop creates NUM_ITEMS amount of items, all who have a random distance between eachother
        for(int i = 0; i < NUM_ITEMS; i++) {
            Items newItem = new Items(startX, ITEM_Y_POSITION, ITEM_WIDTH, ITEM_HEIGHT, game);
            newItem.setItemType(itemType);
            itemsList.add(newItem); // the different items are added to an item list
            startX += ITEM_WIDTH + randomXPosition(xMax, xMin); // the starting position for the next item
        }
    } 


    private void createItemList() {
        itemsList = new ArrayList<>(); 
        // when the program starts, the different items are added to the item list
        createItems(COIN, COIN_MAX_X, COIN_MIN_X);
        createItems(ROCKET, ROCKET_MAX_X, ROCKET_MIN_X);
        createItems(HEART, HEART_MAX_X, HEART_MIN_X);
    } 
    
    private void createGhosts() {
        // the ghosts are created the same way as the items
        ghostList = new ArrayList<>();
        float x = GAME_WIDTH + randomXPosition(GHOST_MAX_X, GHOST_MIN_X);
        
       for(int i = 0; i < NUM_GHOSTS; i++) {
            Ghost newGhost = new Ghost(x, randomGhostYPosition(), GHOST_WIDTH, GHOST_HEIGHT, game);
            newGhost.randomGhost();
            ghostList.add(newGhost);
            x += GHOST_WIDTH;
            x += randomXPosition(GHOST_MAX_X, GHOST_MIN_X);
        }
    }

    /**
     * 
     * @return the arraylist of ghosts
     */
    public ArrayList<Ghost> getGhostList() {
        return ghostList;
    } 
   
    
    private boolean gameOver() {
        // Every time the player collides with a ghost, it removes a life from the life list.
        // If ghost king collides with player, and there are no lives left, the game is over.
        if(ghostKing.collisionDetected(player.getHitBox()) && lifeList.isEmpty()) {
            return true;
        }
        return false;
    }

    private void resetItems() {
        // the method starts with defining the item with the largest x value
        // this is the last item to enter the frame
        float greatestX = 0;
        Items lastItem = itemsList.get(0); 
        for(Items item : itemsList) {
            if(item.getX() > greatestX) {
                greatestX = item.getX();
                lastItem = item;
            }
            
        }
        // if the item is out of bounds (x < 0) or it has collided with player, a new x position is set
        // the new x position is a random distance from the last item in the game
        // the item with the new position is now the last item
        for(Items item : itemsList) {
            if(item.getX() + ITEM_WIDTH < 0 || item.collisionDetected(player.getHitBox())) {
                switch(item.getItemType()) {
                case COIN:
                    item.setX(lastItem.getX() + randomXPosition(COIN_MAX_X, COIN_MIN_X));
                    lastItem = item;
        
                case ROCKET:
                    item.setX(lastItem.getX() + randomXPosition(ROCKET_MAX_X, ROCKET_MIN_X));
                    lastItem = item;
    
                case HEART:
                    item.setX(lastItem.getX() + randomXPosition(HEART_MAX_X, HEART_MIN_X));
                    lastItem = item;
    
                }
            }
        }
        
    }

    private void resetGhosts() {

        float greatestX = 0;
        Ghost lastGhost = ghostList.get(0); 
        for(Ghost ghost : ghostList) {
            if(ghost.getX() > greatestX) {
                greatestX = ghost.getX();
                lastGhost = ghost;
            }
            
        }
       // when the ghost is out of bounds, a new position is set
       for(Ghost ghost : ghostList) {
            if(ghost.getX() + GHOST_WIDTH < 0) {
                ghost.setX(lastGhost.getX() + randomXPosition(GHOST_MAX_X, GHOST_MIN_X));
                lastGhost = ghost;
            }
        }
    }

    @Override
    public void update() {
        
        updateScoreTick(1);
        
        player.update(); 
        ghostKing.update();
            
        for(Items item : itemsList) {
            item.update();
        } 
                
        for(Ghost ghost : ghostList) {
            ghost.update();
        }
    
        resetItems();
        resetGhosts(); 

        if(gameOver()) {
            GameStates.state = GameStates.GAME_OVER;
        }
    }

    /**
     * //increases score by a certain value if the scoreCounter is equal or greater to a set threshold
     * @param value
     */
    public void updateScoreTick(int value) {
    
        scoreCounter += 0.017; // because of 60 FPS
        if(scoreCounter>=SCORE_TICK_INTERVAL) {
            score += value;
            if(score >= highScore) {
                isNewHighscore = true; 
                highScore += value;
            }
            else {
                isNewHighscore = false; 
            }
            scoreCounter = 0; // resets the score counter after each tick
        }
    }      

    /**
     * 
     * @return true if there is a new highscore, false otherwise
     */
    public boolean isNewHighscore() {
        return isNewHighscore;
    }
    

    /**
     * Increases score by given value
     * @param value
     */
    public void updateScore(int value) {
        this.score += value;
        if(score >= this.highScore) {
            this.highScore += value;
        }
        
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public void render(Graphics g) {
       
        ground.draw(g);
        ghostKing.draw(g);
        for(Ghost ghost : ghostList) {
             ghost.draw(g);
        } 
        player.draw(g);
    
        for(Items item : itemsList) {
             item.draw(g);
        }
        lifeCheck(g);
        coinCheck(g);
        createScoreLabel(g);
    
    }

    /**
     * resets the game to default values
     */
    public void reset() {
        initGameObjects();
        setScore(0);
        GameStates.state = GameStates.ACTIVE_GAME;
       
    } 

   
    private void createScoreLabel(Graphics g) { 
        // draws the strings that show score and highscore
        g.setColor(Color.YELLOW);
        g.setFont(customFont().deriveFont(20f));
        g.drawString("Score: " + score + "   Highscore: " + highScore, 400,50 );
    }
    

    /**
     * 
     * @return the player object
     */
    public Player getPlayer() {
        return player;
    }

     /**
     * 
     * @return the score value
     */
    public int getScore() {
        return this.score;
    }

    /**
     * sets score to a given value
     * @param value
     */
    public void setScore(int value) {
        this.score = value;
    } 
    
    /**
     *
     * @return the Ground object
     */
    public Ground getGround() {
        return ground;
    }  


    public void lifeCheck(Graphics g) {
        // creates a heart image with the number of lives left, which are displayed on screen
        BufferedImage heartImage = getSprite(ITEM_SPRITE).getSubimage(0, 2*PIXEL_HEIGHT, PIXEL_WIDTH, PIXEL_HEIGHT);
        g.drawImage(heartImage, (int)(GAME_WIDTH - 200), (int) 50, (int)4*PIXEL_WIDTH, (int)4*PIXEL_HEIGHT, null);
        g.setColor(Color.RED);
        g.setFont(new Font("Verdana", Font.BOLD, (int)SCALE*10));
        g.drawString("x " + lifeList.size(), (int)GAME_WIDTH - 100, (int)GAME_HEIGHT*1/5);
    }
    public void coinCheck(Graphics g) {
        // creates a coin image with the number of coins collected, which are displayed on screen
        BufferedImage heartImage = getSprite(ITEM_SPRITE).getSubimage(0, 0, PIXEL_WIDTH, PIXEL_HEIGHT);
        g.drawImage(heartImage, (int)(GAME_WIDTH - 200), (int) 120, (int)4*PIXEL_WIDTH, (int)4*PIXEL_HEIGHT, null);
        g.setColor(new Color(255, 204,51));
        g.setFont(new Font("Verdana", Font.BOLD, (int)SCALE*10));
        g.drawString("x " + coinList.size(), (int)GAME_WIDTH - 100, (int)GAME_HEIGHT*1/5 + 70);
    }

    /**
     * 
     * @return the arraylist of coins collected
     */
    public ArrayList<Integer> getCoinList() {
        return coinList;
    }

    /**
     * 
     * @return the arraylist with number of lives left
     */
    public ArrayList<Integer> getLifeList() {
        return lifeList;

    } 

}
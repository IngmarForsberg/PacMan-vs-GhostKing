package no.uib.inf101.sem2.gamestates;

import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;
import static no.uib.inf101.sem2.constants.sprites.SpritesPNG.*;
import java.util.ArrayList;
import java.util.Random;
import static no.uib.inf101.sem2.constants.sprites.ItemSprite.ItemType.*;
import no.uib.inf101.sem2.inGameObjects.ghosts.Ghost;
import no.uib.inf101.sem2.inGameObjects.items.Items;
import no.uib.inf101.sem2.inGameObjects.ghosts.GhostKing;
import no.uib.inf101.sem2.inGameObjects.interfaces.StateMethods;
import no.uib.inf101.sem2.inGameObjects.player.Player;
import no.uib.inf101.sem2.inGameObjects.terrain.Ground;
import no.uib.inf101.sem2.main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
    
    public ActiveGame(Game game) {
        super(game);
        initGameObjects();  
    }

    private void initGameObjects() {
        player = new Player(PLAYER_START_X_POSITION, PLAYER_START_Y_POSITION, PLAYER_WIDTH, PLAYER_HEIGHT, game);
        ghostKing = new GhostKing(GHOST_KING_X, GHOST_KING_Y, GHOST_KING_WIDTH, GHOST_KING_HEIGHT, game); 
        ground = new Ground(GROUND_START_X, GROUND_Y_POSITION , GROUND_WIDTH, GROUND_HEIGHT, game);
        createItemList();
        createGhosts();
        defaultLife(STARTING_LIFE);
        coinList = new ArrayList<>();
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
        difficultyUpdate();
    }
    @Override
    public void draw(Graphics g) {
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
        float[] randomYValues = {GHOST_BOTTOM_Y, GHOST_TOP_Y};
        return randomYValues[new Random().nextInt(randomYValues.length)];
    }

    private void createItems(int itemType, float xMax,float xMin) {
        // the item's starting position is always beyond the width of the frame
        float startX = GAME_WIDTH + randomXPosition(xMax, xMin);
        
        // the loop creates NUM_ITEMS amount of items, all who have a random distance between eachother
        for(int i = 0; i < NUM_ITEMS; i++) {
            Items newItem = new Items(startX, ITEM_Y_POSITION, ITEM_WIDTH, ITEM_HEIGHT, game);
            newItem.setItemType(itemType);
            itemsList.add(newItem); // the different items are added to an item list
            startX += ITEM_WIDTH + randomXPosition(xMax, xMin); // the starting position for the next item
        }
    } 


    public void createItemList() {
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

    private float distanceUpdate() {
        // returns the distance multipler for the different difficulites
        // as the speed increases, we also want the distance between the ghosts and items to increase
        if(Difficulty.difficulty == Difficulty.MEDIUM) {
            return MEDIUM_DISTANCE_MULTIPLIER;
        }
        else if(Difficulty.difficulty == Difficulty.HARD) {
            return HARD_DISTANCE_MULTIPLIER;
        }
        else {
            return 1;
        }
    }
    /**
     * 
     * @return the itemlist
     */
    public ArrayList<Items> getItemList() {
        return itemsList;
    }
   
    
    public boolean gameOver() {
        // Every time the player collides with a ghost, it removes a life from the life list.
        // If ghost king collides with player, and there are only one life left, the game is over.
        if(collisionDetected(player.getHitBox(), ghostKing.getHitBox()) && lifeList.size() == 1) {
            return true;
        }
        return false;
    }

    public void resetItems() {
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
            if(item.getX() + ITEM_WIDTH < 0 || collisionDetected(player.getHitBox(), item.getHitBox())) {
                switch(item.getItemType()) {
                case COIN:
                    item.setX(lastItem.getX() + randomXPosition(COIN_MAX_X, COIN_MIN_X)*distanceUpdate());
                    lastItem = item;
        
                case ROCKET:
                    item.setX(lastItem.getX() + randomXPosition(ROCKET_MAX_X, ROCKET_MIN_X)*distanceUpdate());
                    lastItem = item;
    
                case HEART:
                    item.setX(lastItem.getX() + randomXPosition(HEART_MAX_X, HEART_MIN_X)*distanceUpdate());
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
                ghost.setY(randomGhostYPosition());
                lastGhost = ghost;
            }
        }
    }

    

    private void difficultyUpdate() {
        // the score decides the game difficulty
        if(score >= MEDIUM_SCORE_THRESHOLD) {
            Difficulty.difficulty = Difficulty.MEDIUM;
        }
        else if(score >= HARD_SCORE_THRESHOLD) {
            Difficulty.difficulty = Difficulty.HARD;
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


    

    /**
     * resets the game to default values
     */
    public void reset() {
        initGameObjects();
        setScore(0);
        GameStates.state = GameStates.ACTIVE_GAME;
        Difficulty.difficulty = Difficulty.EASY;
    } 

   
    private void createScoreLabel(Graphics g) { 
        // draws the strings which show score and highscore
        g.setColor(Color.YELLOW);
        g.setFont(customFont().deriveFont(8*SCALE));
        g.drawString("Score: " + score + "   Highscore: " + highScore, (int)(160*SCALE), (int)(20*SCALE) );
    }
    
    public void lifeCheck(Graphics g) {
        // creates a heart image with the number of lives left, which are displayed on screen
        BufferedImage heartImage = getSprite(ITEM_SPRITE).getSubimage(0, 2*ITEM_PIXEL_HEIGHT, ITEM_PIXEL_WIDTH, ITEM_PIXEL_HEIGHT);
        g.drawImage(heartImage, (int)(GAME_WIDTH - 80*SCALE), (int) (20*SCALE), (int)(2*SCALE*ITEM_PIXEL_WIDTH), (int)(2*SCALE*ITEM_PIXEL_HEIGHT), null);
        g.setColor(Color.RED);
        g.setFont(new Font("Verdana", Font.BOLD, (int)SCALE*10));
        g.drawString("x " + lifeList.size(), (int)(GAME_WIDTH - 30*SCALE), (int)(55*SCALE));
    }
    public void coinCheck(Graphics g) {
        // creates a coin image with the number of coins collected, which are displayed on screen
        BufferedImage heartImage = getSprite(ITEM_SPRITE).getSubimage(0, 0, ITEM_PIXEL_WIDTH, ITEM_PIXEL_HEIGHT);
        g.drawImage(heartImage, (int)(GAME_WIDTH - 80*SCALE), (int) (48*SCALE), (int)(2*SCALE*ITEM_PIXEL_WIDTH), (int)(2*SCALE*ITEM_PIXEL_HEIGHT), null);
        g.setColor(new Color(255, 204,51));
        g.setFont(new Font("Verdana", Font.BOLD, (int)SCALE*10));
        g.drawString("x " + coinList.size(), (int)(GAME_WIDTH - 30*SCALE), (int)(83*SCALE));
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

    /**
     * 
     * @return the ghostking object
     */
    public GhostKing getGhostKing() {
        return ghostKing;
    } 
    
    /**
     *
     * @return the Ground object
     */
    public Ground getGround() {
        return ground;
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


}
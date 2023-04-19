package no.uib.inf101.sem2.inGameObjects;

import static no.uib.inf101.sem2.constants.sprites.PlayerSprite.PlayerAction.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import no.uib.inf101.sem2.constants.sprites.SpritesPNG;
import no.uib.inf101.sem2.gamestates.ActiveGame;
import no.uib.inf101.sem2.inGameObjects.ghosts.Ghost;
import no.uib.inf101.sem2.inGameObjects.interfaces.Animations;
import no.uib.inf101.sem2.inGameObjects.interfaces.HitBox;
import no.uib.inf101.sem2.inGameObjects.terrain.Ground;
import no.uib.inf101.sem2.main.Game;

public abstract class GameObjects implements HitBox, Animations{
    protected int animationTick, animationIndex;
    private Ground ground;
    
    
    protected float x;

    protected float y;
    protected float width;

    protected float height; 
    protected float xSpeed;
    protected float ySpeed;
    protected Rectangle2D.Float hitBox;
    protected Game game;
    //protected ActiveGame activeGame;


    protected BufferedImage[][] animationArray;
   
    public GameObjects(float x, float y, float width, float height, Game game ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.game = game;

        initHitBox(x, y, width, height);
    }

   

    @Override
    public void drawHitBox(Graphics g) {
        // For debugging the hitbox
        g.setColor(Color.PINK);
        g.drawRect((int)hitBox.x, (int)hitBox.y, (int)hitBox.width, (int)hitBox.height);
        

    }

    @Override
    public void initHitBox(float x, float y, float width, float height) {
		hitBox = new Rectangle2D.Float(x, y, width, height);
	} 

    
    @Override
    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    public boolean onGround(Rectangle2D.Float ground) {
        if(hitBox.y + hitBox.height == ground.y) {
            return true;
        }
        /* this.hitBox.y ++; // moves hitbox one down
        // if hitbox intersects ground, player is on ground
            if(collisionDetected(ground, this.hitBox)) {
                hitBox.y--;
                return true;
            }
        
        hitBox.y--; */
        return false;
    } 
    

    
       
    public void resetHitBox() {
        hitBox.x = (int) this.x;
        hitBox.y = (int) this.y;
        hitBox.width = this.width;
        hitBox.height = this.height;
    }

    
    public void updateHitbox(float x, float y, float width, float height) {
        resetHitBox();
        hitBox.x += x;
        hitBox.y += y;;
        hitBox.width += width;
        hitBox.height  += height; 
    }

    
    @Override
    public void loadAnimations(String spriteName, int rows, int cols, int width, int height) {
        BufferedImage image = SpritesPNG.getSprite(spriteName);
        animationArray = new BufferedImage[rows][cols]; 
        for(int i = 0; i < animationArray.length; i++) {
            for(int j = 0; j < animationArray[i].length; j++) {
                animationArray[i][j] = image.getSubimage(j*width, i*height, width, height); 
            }
        } 
    } 

    @Override
    public void updateAnimationTick(int animationSpeed, int spriteSize) { 
        
        animationTick++;
        if(animationTick >= animationSpeed) { //waiting for the tick threshold
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= spriteSize) {
                animationIndex = 0;
            }
        }
    }

    /**
     * 
     * @param hitBoxx
     * @return true if a collision is detected, false otherwise
     */
    public boolean collisionDetected(Rectangle2D.Float hitBox1, Rectangle2D.Float hitBox2) {
        if(hitBox1.intersects(hitBox2)) {
            return true;
        }
        return false;
    }

    public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }
    public float getXSpeed() {
        return this.xSpeed;
    }
    public float getYSpeed() {
        return this.ySpeed;
    }
    public float getWidth() {
        return this.width;
    }
    public float getHeight() {
        return this.height;
    }

    
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public void setXSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }
    public void setYSpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public void setHeight(float heigth) {
        this.height = heigth;
    }

    


    
}

package no.uib.inf101.sem2.terrain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import no.uib.inf101.sem2.inGameObjects.GameObjects;
import no.uib.inf101.sem2.inGameObjects.interfaces.DrawAndUpdate;
import no.uib.inf101.sem2.view.Game;

public class Ground extends GameObjects implements DrawAndUpdate{
    /* public int x;
    private int y;
    private int width;
    private int height;  */
    int startX;

    private Rectangle2D.Float hitBox; 

    public Ground(float x, float y, float width, float height, Game game) {
        super(x, y, width, height, game);
        initHitBox(x, y, width, height); 
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect((int)x, (int)y, (int)width, (int)height);
        //drawHitBox(g);
        
    }

    

    public int set(int cameraX) {
        this.x = startX + cameraX; 
        hitBox.x = x;
        return (int)x;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void updatePosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePosition'");
    }

}

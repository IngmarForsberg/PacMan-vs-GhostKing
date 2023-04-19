package no.uib.inf101.sem2.inGameObjects.terrain;

import java.awt.Color;
import java.awt.Graphics;

import no.uib.inf101.sem2.inGameObjects.GameObjects;
import no.uib.inf101.sem2.inGameObjects.interfaces.DrawAndUpdate;
import no.uib.inf101.sem2.main.Game;

public class Ground extends GameObjects implements DrawAndUpdate{
    public Ground(float x, float y, float width, float height, Game game) {
        super(x, y, width, height, game);
        initHitBox(x, y, width, height); 
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect((int)x, (int)y, (int)width, (int)height);
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

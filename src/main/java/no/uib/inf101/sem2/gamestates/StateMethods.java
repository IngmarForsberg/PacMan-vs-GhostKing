package no.uib.inf101.sem2.gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {

    void update();

    void draw(Graphics g);

    void render(Graphics g);
    
}

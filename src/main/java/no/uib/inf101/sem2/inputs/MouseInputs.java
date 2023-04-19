package no.uib.inf101.sem2.inputs;

import static no.uib.inf101.sem2.constants.Menu.MenuLayout.*;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import no.uib.inf101.sem2.gamestates.GameStates;
import no.uib.inf101.sem2.main.GamePanel;

public class MouseInputs implements MouseListener {
    private GamePanel gamePanel;
   
    GameStates gamestates;


    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        switch(GameStates.state) {
            // Back button
            case HOW_TO:
            if(mouseX >= BACK_X && mouseX <= BACK_X + BACK_WIDTH) {
                if(mouseY >= BACK_Y && mouseY <= BACK_Y + BACK_HEIGHT) {
                    GameStates.state = GameStates.MENU;
                } 
            }
                break;
            
            case MENU:
                // play button
                if(mouseX >= PLAY_Y && mouseX <= PLAY_X + PLAY_WIDTH) {
                    if(mouseY >= PLAY_Y && mouseY <= PLAY_Y + PLAY_HEIGHT) {
                        GameStates.state = GameStates.ACTIVE_GAME;
                    }
                } 
                // how to button
                if(mouseX >= HOW_TO_X && mouseX <= HOW_TO_X + HOW_TO_WIDTH ) {
                    if(mouseY >= HOW_TO_Y && mouseY <= HOW_TO_Y + HOW_TO_HEIGHT) {
                        GameStates.state = GameStates.HOW_TO;
                    }
                }
                break;
            default:
                break;
            
        }

       


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}

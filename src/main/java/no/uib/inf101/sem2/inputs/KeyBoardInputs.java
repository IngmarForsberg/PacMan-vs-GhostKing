package no.uib.inf101.sem2.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import no.uib.inf101.sem2.gamestates.GameStates;
import no.uib.inf101.sem2.inGameObjects.interfaces.PlayerMovement;
import no.uib.inf101.sem2.main.GamePanel;

public class KeyBoardInputs implements KeyListener{
    private GamePanel gamePanel;
    GameStates gamestates;

    public KeyBoardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(GameStates.state) {
            case ACTIVE_GAME:
                // Up or space for jumping
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP  ) {
                    gamePanel.getGame().activeGame().getPlayer().setJump(true);
                    
                }
                // Down for crouching
                else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    gamePanel.getGame().activeGame().getPlayer().setCrouch(true);
                }
                else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    GameStates.state = GameStates.PAUSE;
                }
                break;
            case PAUSE:
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    GameStates.state = GameStates.ACTIVE_GAME;
                    break;
                }

            case GAME_OVER:
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    gamePanel.getGame().activeGame().reset();
                }
                else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    gamePanel.getGame().activeGame().reset();
                    GameStates.state = GameStates.MENU;
                }

                break;
            default:
                break;
            
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) { 
            // sets jump/crouch to false when key is released
            case KeyEvent.VK_UP:
            case KeyEvent.VK_SPACE:
                gamePanel.getGame().activeGame().getPlayer().setJump(false);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.getGame().activeGame().getPlayer().setCrouch(false);
                break;
            
        }   
    }
}

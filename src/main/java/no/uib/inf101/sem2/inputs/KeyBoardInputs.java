package no.uib.inf101.sem2.inputs;



import no.uib.inf101.sem2.view.Game;
import no.uib.inf101.sem2.view.GamePanel;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import no.uib.inf101.sem2.gamestates.GameStates;
import no.uib.inf101.sem2.inGameObjects.GameObjects;
import no.uib.inf101.sem2.inGameObjects.player.Player;
import no.uib.inf101.sem2.inGameObjects.player.PlayerMovement;



public class KeyBoardInputs implements KeyListener{
    private GamePanel gamePanel;
    //Player player;
    PlayerMovement playerMovement;
    GameStates gamestates;


    public KeyBoardInputs(GamePanel gamePanel, PlayerMovement playerMovement) {
        this.gamePanel = gamePanel;
        this.playerMovement = playerMovement;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(GameStates.state) {
            case ACTIVE_GAME:
                
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP  ) {
                    gamePanel.getGame().activeGame().getPlayer().setJump(true);
                    
                }
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

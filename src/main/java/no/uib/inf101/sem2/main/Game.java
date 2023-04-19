package no.uib.inf101.sem2.main;

import no.uib.inf101.sem2.gamestates.ActiveGame;
import no.uib.inf101.sem2.gamestates.GameOver;
import no.uib.inf101.sem2.gamestates.GameStates;
import no.uib.inf101.sem2.gamestates.HowTo;
import no.uib.inf101.sem2.gamestates.MenuState;
import no.uib.inf101.sem2.gamestates.Pause;
import java.awt.*;
import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;

import java.util.Timer;
import java.util.TimerTask;


public class Game{
    private ActiveGame activeGame;
    private GameOver gameOver;
    private MenuState menu;
    private Pause pause;
    private HowTo howTo;

    
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    
    private Timer gameTimer;
    private GameStates gameState;

    public Game() {
        
        initGameStates();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        initTimer();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public GameStates getGameState() {
        return gameState;
    }

    public void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }

    private void initGameStates() {
        
        activeGame = new ActiveGame(this);
        gameOver = new GameOver(this);
        menu = new MenuState(this);
        pause  = new Pause(this);
        howTo = new HowTo(this);
    }

    

    public void update() {
        switch(GameStates.state) {
        case ACTIVE_GAME:
            activeGame.update();
                break;
        default:
            break;
           
        } 
    }

    public ActiveGame activeGame() {
        return activeGame;
    }

    public void render(Graphics g) {
        switch(GameStates.state) {
        case MENU:
            menu.draw(g);
            break;
        case HOW_TO:
            howTo.draw(g);
            break;
        case PAUSE:
            activeGame.draw(g);
            pause.draw(g);
            break;
        case ACTIVE_GAME:
            activeGame.draw(g);
            break;
        case GAME_OVER:
            gameOver.draw(g);
            break;
        default:
            break;
            
        }
         
    }

    
    public void initTimer() {
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
                gamePanel.repaint();

            }
        }, 0, UPDATE_INTERVAL); 

    }
    
}
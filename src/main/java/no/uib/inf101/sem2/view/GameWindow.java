package no.uib.inf101.sem2.view;
import javax.swing.*;

//import gamestates.GameStates;

import java.awt.*;

public class GameWindow extends JFrame {
    private CardLayout cardLayout;
    
    private static final String TITLE = "Pacman vs Ghost King";
    
   
    public GameWindow(GamePanel gamePanel) {
        setTitle(TITLE);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(gamePanel);
       
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        


    }
    
}
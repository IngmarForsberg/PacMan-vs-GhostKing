package no.uib.inf101.sem2.main;
import javax.swing.*;


public class GameWindow extends JFrame {
    
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
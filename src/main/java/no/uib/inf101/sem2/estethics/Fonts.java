package no.uib.inf101.sem2.estethics;
import static no.uib.inf101.sem2.constants.InGameObjects.DimensionsAndSpeeds.*;

import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
public class Fonts {
    
    public static Font customFont() {
        // creates the game font
        Font customFont;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2p.ttf")).deriveFont(12*SCALE);
            return customFont;
        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       return new Font("Verdana", Font.BOLD, 20);


    }
     

     
}

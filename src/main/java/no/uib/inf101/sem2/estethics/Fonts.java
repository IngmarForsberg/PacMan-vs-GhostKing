package no.uib.inf101.sem2.estethics;

import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;




public class Fonts {
    



    public static Font customFont() {
    
       
        Font customFont;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PressStart2p.ttf")).deriveFont(30f);
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

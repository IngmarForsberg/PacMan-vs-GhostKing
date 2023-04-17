package no.uib.inf101.sem2.constants.sprites;

public class GhostSprite {
    public static class GhostColors {
        //id for each animations, index
        
        public static final int GREEN  = 0;
        public static final int PINK = 1;
        public static final int BLACK = 2;
        public static final int YELLOW = 3;

        public static final int PICK_UP_ROCKET = 0;
        


        //amount of sprites per animation
        public static int ghostSpriteSize(int ghostColor) {

            switch(ghostColor) {
            case GREEN:
            case PINK:
            case BLACK:
            case YELLOW:
            default:
                return 5;
            }

        } 
    }
    
}

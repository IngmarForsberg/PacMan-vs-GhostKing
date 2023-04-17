package no.uib.inf101.sem2.constants.sprites;

public class PlayerSprite {

    // enums ? 
    
    public static class PlayerAction {
        //id for each animations, index
        public static final int RUNNING = 0;
        public static final int CROUCHING = 1;
        public static final int JUMPING = 2;
        public static final int ROCKET_LAUNCH = 3;
        public static final int SCREAMING = 4;


        //amount of sprites per animation
        public static int playerSpriteSize(int playerAction) {

            switch(playerAction) {
            case JUMPING:
                return 2;
            case RUNNING:
            case CROUCHING:
            case ROCKET_LAUNCH:
            case SCREAMING:
            default:
                return 2;
            }

        } 
    }
    
    
}

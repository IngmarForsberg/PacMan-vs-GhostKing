package no.uib.inf101.sem2.constants.sprites;

public class ItemSprite {
    public static class ItemType {
        //id for each animations, index
        
        public static final int COIN  = 0;
        public static final int ROCKET = 1;
        public static final int HEART = 2;

       
        //amount of sprites per animation
        public static int itemSpriteSize(int itemType) {

            switch(itemType) {
            case COIN:
            case ROCKET:
            case HEART:
            default:
                return 1;
            }

        } 
    }
    
    
}

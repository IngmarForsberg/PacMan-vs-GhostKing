package no.uib.inf101.sem2.constants.sprites;

public class GhostKingSprite {
    public static class GhostKingAction {
        // index of the animation array
        public static final int DEFAULT = 0;
    
        //amount of sprites per animation
        public static int ghostKingSpriteSize(int ghostKingAction) {

            switch(ghostKingAction) {
                case DEFAULT:
                default:
                    return 5;
            }

        } 
    }
    
    
}

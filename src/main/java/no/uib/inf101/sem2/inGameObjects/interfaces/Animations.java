package no.uib.inf101.sem2.inGameObjects.interfaces;

public interface Animations {

    /**
     * loads sprite animation
     * @param spriteName name of .PNG file
     * @param rows number of rows in sprite array
     * @param cols  number of columns in sprite array  
     * @param width width of animation sub image
     * @param height height of animation sub image
     */
    void loadAnimations(String spriteName, int rows, int cols, int width, int height);

    /**
     * sets the speed in how fast the program moves to the next image in a sprite
     */
    void updateAnimationTick(int animationSpeed, int spriteSize);
       
    
    

}

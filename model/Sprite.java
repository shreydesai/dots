package model;

import javafx.scene.shape.Circle;

/**
 * Defines the elements for a circular sprite, or graphics 
 * object, on the currently present canvas
 */
public interface Sprite {
    /**
     * Moves the sprite up
     */
    public void moveUp();
    
    /**
     * Moves the sprite down
     */
    public void moveDown();
    
    /**
     * Moves the sprite to the left
     */
    public void moveLeft();
    
    /**
     * Moves the sprite to the right
     */
    public void moveRight();
    
    /**
     * Getter method for the shape behind the sprite
     * @return circle node of the sprite 
     */
    public Circle getNode();
    
    /**
     * Getter method for the sprite radius
     * @return radius of the circular sprite
     */
    public double getR();
    
    /**
     * Getter method for the sprite X position
     * @return X position of the circular sprite
     */
    public double getX();
    
    /**
     * Getter method for the sprite Y position
     * @return Y position of the circular sprite
     */
    public double getY();
    
    /**
     * Sets the center X coordinate for the sprite
     * @param cx desired center X position
     */
    public void setX(double cx);
    
    /**
     * Sets the center Y coordinate for the sprite
     * @param cy desired center Y position
     */
    public void setY(double cy);
}

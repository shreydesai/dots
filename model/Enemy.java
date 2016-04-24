package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * Represents all of the enemies - red, green, and blue - in
 * the game - implements the Sprite and Animation interfaces
 */
public abstract class Enemy implements Sprite, Animation {
    
    protected static final int HEIGHT = 800;
    
    protected double cx, cy, r;
    protected Circle circle;
    protected Pane pane;
    
    /**
     * Default constructor
     */
    public Enemy() {
        
    }
    
    /**
     * Constructor
     * @param pane setting of the game
     * @param cx center X position of the enemy
     * @param cy center Y position of the enemy
     * @param r radius of the enemy
     */
    public Enemy(Pane pane, double cx, double cy, double r) {
        this.pane = pane;
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        
        circle = new Circle(this.cx, this.cy, this.r);
        start();
    }
    
    /**
     * Getter method for the points the enemy is worth
     * @return number of points
     */
    public abstract int getPoints();
    
    /**
     * Getter method for a string representation
     */
    public abstract String toString();
    
    @Override
    /**
     * Starts the animation loop of the enemy
     */
    public abstract void start();

    @Override
    /**
     * Getter method for the enemy circle object
     * @return node representation of enemy
     */
    public Circle getNode() {
        return circle;
    }
    
    @Override
    /**
     * Getter method for the enemy radius
     * @return radius of enemy
     */
    public double getR() {
        return circle.getRadius();
    }
    
    @Override
    /**
     * Getter method for the enemy X position
     * @return X position of enemy
     */
    public double getX() {
        return circle.getCenterX();
    }
    
    @Override
    /**
     * Getter method for the enemy Y position
     * @return Y position of enemy
     */
    public double getY() {
        return circle.getCenterY();
    }
    
    @Override
    /**
     * Sets the center X position of enemy
     * @param cx desired center X position
     */
    public void setX(double cx) {
        circle.setCenterX(cx);
    }
    
    @Override
    /**
     * Sets the center Y position of enemy
     * @param cy desired center Y position
     */
    public void setY(double cy) {
        circle.setCenterY(cy);
    }
    
    @Override
    @Deprecated
    /**
     * Moves the enemy up
     */
    public void moveUp() {
        return;
    }
    
    @Override
    @Deprecated
    /**
     * Moves the enemy down
     */
    public void moveDown() {
        return;
    }
    
    @Override
    @Deprecated
    /**
     * Moves the enemy to the left
     */
    public void moveLeft() {
        return;
    }
    
    @Override
    @Deprecated
    /**
     * Moves the enemy to the right
     */
    public void moveRight() {
        return;
    }
}

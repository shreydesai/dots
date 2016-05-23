package model;

import javafx.scene.shape.Circle;

/**
 * Represents the Player in the game - it implements the
 * Sprite interface for its functionalities
 */
public class Player implements Sprite {
    
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    private final static int DELTA = 20;
    
    private double cx, cy, r;
    private Circle circle;
    
    /**
     * Constructor
     * @param cx center x position
     * @param cy center y position
     * @param r radius of the circle node
     */
    public Player(double cx, double cy, double r) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        
        // Instantiates a new Circle node to represent the
        // Player on the canvas
        circle = new Circle(this.cx, this.cy, this.r);
    }
    
    @Override
    /**
     * Moves the Player in the north direction
     */
    public void moveUp() {
        if (getY() - getR() - DELTA < 0) {
            // Disable movement if the Player has reached
            // the top of the canvas
            return;
        } else {
            setY(getY() - DELTA);
        }
    }
    
    @Override
    /**
     * Moves the Player in the south direction
     */
    public void moveDown() {
        if (getY() + getR() + DELTA > HEIGHT) {
            // Disable movement if the Player has reached
            // the bottom of the canvas
            return;
        } else {
            setY(getY() + DELTA);
        }
    }
    
    @Override
    /**
     * Moves the Player in the west direction
     */
    public void moveLeft() {
        if (getX() - getR() - DELTA < 0) {
            // Disable movement if the Player has reached
            // the west side of the canvas
            return;
        } else {
            setX(getX() - DELTA);
        }
    }
    
    @Override
    /**
     * Moves the Player in the east direction
     */
    public void moveRight() {
        if (getX() + getR() + DELTA > WIDTH) {
            // Disable movement if the Player has reached
            // the east side of the canvas
            return;
        } else {
            setX(getX() + DELTA);
        }
    }
    
    @Override
    /**
     * Getter method for the Player element
     * @return circle node
     */
    public Circle getNode() {
        return circle;
    }
    
    @Override
    /**
     * Getter method for the Player's radius
     * @return circle node radius
     */
    public double getR() {
        return circle.getRadius();
    }
    
    @Override
    /**
     * Getter method for the Player's X coordinate
     * @return circle x coordinate
     */
    public double getX() {
        return circle.getCenterX();
    }
    
    @Override
    /**
     * Getter method for the Player's Y coordinate
     * @return circle y coordinate
     */
    public double getY() {
        return circle.getCenterY();
    }
    
    @Override
    /**
     * Sets the Player's X coordinate
     */
    public void setX(double cx) {
        circle.setCenterX(cx);
    }
    
    @Override
    /**
     * Sets the Player's Y coordinate
     */
    public void setY(double cy) {
        circle.setCenterY(cy);
    }

}

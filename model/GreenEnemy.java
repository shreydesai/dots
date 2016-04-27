package model;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

/**
 * Represents the Green Enemy - it is a child of the
 * Enemy abstract class
 */
public class GreenEnemy extends Enemy {
    
    private static final int POINTS = 10;
    private static final int DELTA = 2;
    
    /**
     * Default constructor
     */
    public GreenEnemy() {
        
    }
    
    /**
     * Constructor
     * @param pane setting of the game
     * @param cx center X position of the enemy
     * @param cy center Y position of the enemy
     * @param r radius of the enemy
     */
    public GreenEnemy(Pane pane, double cx, double cy, double r) {
        super(pane, cx, cy, r);
        this.getNode().getStyleClass().add("green-enemy");
        this.getNode().setId("Green");
    }
    
    @Override
    /**
     * Starts the animation loop of the enemy
     */
    public void start() {
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                // Stop the timer if the pane no longer contains
                // the node element of the enemy
                if (!pane.getChildren().contains(circle)) {
                    this.stop();
                }
                // Prevents the node element of the enemy
                // from going vertically off of the canvas
                if (getY() + getR() + DELTA >= HEIGHT) {
                    setY(0);
                }
                // Move the Y position of the node element of the
                // enemy by adding the DELTA variable
                setY(getY() + DELTA);
            }
            
        }.start();
    }

    @Override
    /**
     * Getter method for the points the enemy is worth
     * @return number of points
     */
    public int getPoints() {
        return POINTS;
    }
    
    @Override
    /**
     * Getter method for a string representation
     */
    public String toString() {
        return "Green Enemy";
    }

}

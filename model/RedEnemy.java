package model;

import javafx.scene.layout.Pane;

/**
 * Represents the Red Enemy - it is a child of the
 * Enemy abstract class
 */
public class RedEnemy extends Enemy {
    
    private static final int POINTS = 5;
    
    /**
     * Default constructor
     */
    public RedEnemy() {
        
    }
    
    /**
     * Constructor
     * @param pane setting of the game
     * @param cx center X position of the enemy
     * @param cy center Y position of the enemy
     * @param r radius of the enemy
     */
    public RedEnemy(Pane pane, double cx, double cy, double r) {
        super(pane, cx, cy, r);
        this.getNode().getStyleClass().add("red-enemy");
        this.getNode().setId("Red");
    }
    
    @Override
    /**
     * Starts the animation loop of the enemy
     */
    public void start() {
        return;
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
        return "Red Enemy";
    }

}

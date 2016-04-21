package model;

import javafx.scene.layout.Pane;

public class RedEnemy extends Enemy {
    
    private static final int POINTS = 5;
    
    public RedEnemy() {
        
    }

    public RedEnemy(Pane pane, double cx, double cy, double r) {
        super(pane, cx, cy, r);
        this.getNode().getStyleClass().add("red-enemy");
        this.getNode().setId("Red");
    }
    
    @Override
    public void start() {
        return;
    }

    @Override
    public int getPoints() {
        return POINTS;
    }
    
    @Override
    public String toString() {
        return "Red Enemy";
    }

}

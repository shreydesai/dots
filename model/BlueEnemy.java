package model;

import javafx.scene.layout.Pane;

public class BlueEnemy extends Enemy {
    
    private static final int POINTS = 15;

    public BlueEnemy(Pane pane, double cx, double cy, double r) {
        super(pane, cx, cy, r);
    }

    @Override
    public int getPoints() {
        return POINTS;
    }

}

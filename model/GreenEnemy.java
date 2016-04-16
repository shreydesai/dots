package model;

import javafx.scene.layout.Pane;

public class GreenEnemy extends Enemy {
    
    private static final int POINTS = 10;

    public GreenEnemy(Pane pane, double cx, double cy, double r) {
        super(pane, cx, cy, r);
    }

    @Override
    public int getPoints() {
        return POINTS;
    }

}

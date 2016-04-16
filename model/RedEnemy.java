package model;

import javafx.scene.layout.Pane;

public class RedEnemy extends Enemy {
    
    private static final int POINTS = 5;

    public RedEnemy(Pane pane, double cx, double cy, double r) {
        super(pane, cx, cy, r);
    }

    @Override
    public int getPoints() {
        return POINTS;
    }

}

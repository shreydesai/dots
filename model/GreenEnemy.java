package model;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class GreenEnemy extends Enemy {
    
    private static final int POINTS = 10;
    private static final int DELTA = 2;
    
    public GreenEnemy() {
        
    }

    public GreenEnemy(Pane pane, double cx, double cy, double r) {
        super(pane, cx, cy, r);
        this.getNode().getStyleClass().add("green-enemy");
        this.getNode().setId("Green");
    }
    
    @Override
    public void start() {
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (!pane.getChildren().contains(circle)) {
                    this.stop();
                }
                if (getY() + getR() + DELTA >= HEIGHT) {
                    setY(0);
                }
                setY(getY() + DELTA);
            }
            
        }.start();
    }

    @Override
    public int getPoints() {
        return POINTS;
    }
    
    @Override
    public String toString() {
        return "Green Enemy";
    }

}

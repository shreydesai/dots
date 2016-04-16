package model;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public abstract class Enemy implements Sprite, Animation {
    
    private final static int HEIGHT = 800;
    private static final double DELTA = 3;
    
    private double cx, cy, r;
    private Circle circle;
    private Pane pane;
    
    public Enemy(Pane pane, double cx, double cy, double r) {
        this.pane = pane;
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        
        circle = new Circle(this.cx, this.cy, this.r);
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
    
    public abstract int getPoints();

    public Circle getNode() {
        return circle;
    }
    
    public double getR() {
        return circle.getRadius();
    }
    
    public double getX() {
        return circle.getCenterX();
    }
    
    public double getY() {
        return circle.getCenterY();
    }
    
    public void setX(double cx) {
        circle.setCenterX(cx);
    }
    
    public void setY(double cy) {
        circle.setCenterY(cy);
    }
    
    public void moveUp() {
        return;
    }
    
    public void moveDown() {
        return;
    }
    
    public void moveLeft() {
        return;
    }
    
    public void moveRight() {
        return;
    }
}

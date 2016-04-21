package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public abstract class Enemy implements Sprite, Animation {
    
    protected static final int HEIGHT = 800;
    
    protected double cx, cy, r;
    protected Circle circle;
    protected Pane pane;
    
    public Enemy() {
        
    }
    
    public Enemy(Pane pane, double cx, double cy, double r) {
        this.pane = pane;
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        
        circle = new Circle(this.cx, this.cy, this.r);
        start();
    }
    
    public abstract int getPoints();
    public abstract String toString();
    
    @Override
    public abstract void start();

    @Override
    public Circle getNode() {
        return circle;
    }
    
    @Override
    public double getR() {
        return circle.getRadius();
    }
    
    @Override
    public double getX() {
        return circle.getCenterX();
    }
    
    @Override
    public double getY() {
        return circle.getCenterY();
    }
    
    @Override
    public void setX(double cx) {
        circle.setCenterX(cx);
    }
    
    @Override
    public void setY(double cy) {
        circle.setCenterY(cy);
    }
    
    @Override
    public void moveUp() {
        return;
    }
    
    @Override
    public void moveDown() {
        return;
    }
    
    @Override
    public void moveLeft() {
        return;
    }
    
    @Override
    public void moveRight() {
        return;
    }
}

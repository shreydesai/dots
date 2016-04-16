package model;

import javafx.scene.shape.Circle;

public class Player implements Sprite {
    
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    private final static int DELTA = 20;
    
    private double cx, cy, r;
    private Circle circle;
    
    public Player(double cx, double cy, double r) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        
        circle = new Circle(this.cx, this.cy, this.r);
    }
    
    @Override
    public void moveUp() {
        if (getY() - getR() - DELTA < 0) {
            return;
        } else {
            setY(getY() - DELTA);
        }
    }
    
    @Override
    public void moveDown() {
        if (getY() + getR() + DELTA > HEIGHT) {
            return;
        } else {
            setY(getY() + DELTA);
        }
    }
    
    @Override
    public void moveLeft() {
        if (getX() - getR() - DELTA < 0) {
            return;
        } else {
            setX(getX() - DELTA);
        }
    }
    
    @Override
    public void moveRight() {
        if (getX() + getR() + DELTA > WIDTH) {
            return;
        } else {
            setX(getX() + DELTA);
        }
    }
    
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

}

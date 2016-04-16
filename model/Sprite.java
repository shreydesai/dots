package model;

import javafx.scene.shape.Circle;

public interface Sprite {
    
    public void moveUp();
    public void moveDown();
    public void moveLeft();
    public void moveRight();
    
    public Circle getNode();
    
    public double getR();
    public double getX();
    public double getY();
    
    public void setX(double cx);
    public void setY(double cy);
}

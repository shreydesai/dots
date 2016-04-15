package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Player implements Sprite {
    
    private double cx, cy, r, dy, dx;
    private Circle circle;
    private Canvas canvas;

    public Player(double cx, double cy, double r, double dy, double dx) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        this.dy = dy;
        this.dx = dx;
        
        circle = new Circle(this.cx, this.cy, this.r);
        canvas = new Canvas();
    }
    
    @Override
    public void draw(Pane pane) {
        pane.getChildren().add(circle);
    }
    
    @Override
    public void moveUp() {
        if (circle.getCenterY() - circle.getRadius() - dy < 0) {
            return;
        } else {
            circle.setCenterY(circle.getCenterY() - dy);
        }
    }
    
    @Override
    public void moveDown() {
        if (circle.getCenterY() + circle.getRadius() + dy > canvas.getHeight()) {
            return;
        } else {
            circle.setCenterY(circle.getCenterY() + dy);
        }
    }
    
    @Override
    public void moveLeft() {
        if (circle.getCenterX() - circle.getRadius() - dx < 0) {
            return;
        } else {
            circle.setCenterX(circle.getCenterX() - dx);
        }
    }

    @Override
    public void moveRight() {
        if (circle.getCenterX() + circle.getRadius() + dx > canvas.getWidth()) {
            return;
        } else {
            circle.setCenterX(circle.getCenterX() + dx);
        }
    }
    
    @Override
    public String toString() {
        return "Player(" + cx + ", " + cy + ", " + r + ")";
    }

}

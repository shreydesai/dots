package model;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Bullet implements Animation {
    
    private final static int DELTA = 5;
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    
    private Pane pane;
    private List<Node> enemies;
    private Line line;
    private double[] coordinates;
    
    private boolean right;
    private double theta;
    
    public Bullet(Pane pane, List<Node> enemies, double[] coordinates) {
        this.enemies = enemies;
        this.pane = pane;
        this.coordinates = coordinates;
        
        theta = 0;
        right = true;
        
        line = getLine();
        pane.getChildren().add(line);
    }
    
    @Override
    public void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                handleOffCanvas(this);
                handleCollision(this);
                
                if (right) {
                    line.setStartX(line.getStartX() + DELTA * Math.cos(theta));
                    line.setEndX(line.getEndX() + DELTA * Math.cos(theta));
                    line.setStartY(line.getStartY() - DELTA * Math.sin(theta));
                    line.setEndY(line.getEndY() - DELTA * Math.sin(theta));
                } else {
                    line.setStartX(line.getStartX() - DELTA * Math.cos(theta));
                    line.setEndX(line.getEndX() - DELTA * Math.cos(theta));
                    line.setStartY(line.getStartY() + DELTA * Math.sin(theta));
                    line.setEndY(line.getEndY() + DELTA * Math.sin(theta));
                }
            }
        }.start();
    }
    
    public Line getLine() {
        double cx = coordinates[0];
        double cy = coordinates[1];
        double px = coordinates[2];
        double py = coordinates[3];
        double componentX, componentY;
        right = px > cx;
        
        if (right) {
            componentX = px - cx;
            componentY = cy - py;
            theta = Math.atan(componentY / componentX);
            return new Line(
                    cx, cy, 
                    cx + 10 * Math.cos(theta), 
                    cy - 10 * Math.sin(theta)
            );
        } else {
            componentX = cx - px;
            componentY = py - cy;
            theta = Math.atan(componentY / componentX);
            return new Line(
                    cx, cy, 
                    cx - 10 * Math.cos(theta), 
                    cy + 10 * Math.sin(theta)
            );
        }
    }
    
    private void handleOffCanvas(AnimationTimer timer) {
        if (disappeared()) {
            timer.stop();
            pane.getChildren().remove(line);
        }
    }
    
    private void handleCollision(AnimationTimer timer) {
        for (int i = 1; i < enemies.size(); i++) {
            Circle enemy = (Circle) enemies.get(i);
            if (collided(enemy) && pane.getChildren().contains(enemy)) {
                System.out.println(enemies.get(i).getId());
                timer.stop();
                pane.getChildren().removeAll(line, enemy);
            }
        }
    }
    
    private boolean disappeared() {
        return line.getEndX() <= 0 ||
                line.getEndX() >= WIDTH ||
                line.getEndY() <= 0 ||
                line.getEndY() >= HEIGHT;
    }
    
    private boolean collided(Circle enemy) {
        return line.getEndX() > enemy.getCenterX() - enemy.getRadius() &&
                line.getEndX() < enemy.getCenterX() + enemy.getRadius() &&
                line.getEndY() > enemy.getCenterY() - enemy.getRadius() &&
                line.getEndY() < enemy.getCenterY() + enemy.getRadius();
    }
}

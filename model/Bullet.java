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
    private Circle circle;
    private List<Node> enemies;
    private Line line;
    
    public Bullet(Circle circle, List<Node> enemies, Pane pane) {
        this.circle = circle;
        this.enemies = enemies;
        this.pane = pane;

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
                line.setStartX(line.getStartX() + DELTA);
                line.setEndX(line.getEndX() + DELTA);
            }
        }.start();
    }
    
    public Line getLine() {
        double x = circle.getCenterX();
        double y = circle.getCenterY();
        return new Line(x, y, x + 10, y);
    }
    
    private void handleOffCanvas(AnimationTimer timer) {
        if (disappeared()) {
            timer.stop();
            pane.getChildren().remove(line);
        }
    }
    
    private void handleCollision(AnimationTimer timer) {
        for (int i = 0; i < enemies.size(); i++) {
            Circle enemy = (Circle) enemies.get(i);
            if (collided(enemy)) {
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

package model;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Bullet implements Animation {
    
    private Canvas canvas;
    
    private Timer timer;
    private int delay;
    private int interval;
    
    private Circle circle;
    private Pane pane;
    private Line line;

    private double delta;
    
    public Bullet(Circle circle, Pane pane, Line line) {
        this.canvas = new Canvas();
        
        this.timer = null;
        this.delay = 0;
        this.interval = 50;
        
        this.circle = circle;
        this.pane = pane;
        this.line = line;
        
        this.delta = 10;
    }
    
    @Override
    public void initialize() {
        setTimer();
    }
    
    @Override
    public void setTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        createBullet();
                    }
                });
            }
        }, delay, interval);
    }
    
    @Override
    public Timer getTimer() {
        return timer;
    }
    
    @Override
    public void cancelTimer(Timer timer) {
        timer.cancel();
    }
    
    // TODO
    // make sure that the beginning of the bullets hit the guy
    // make sure that some line on the bullet hits the guy
    
    private void createBullet() {
        if (isOffCanvas()) {
            
            cancelTimer(timer);
            canvas.removeElement(pane, line);
        }
        
        if (line.getEndX() > circle.getCenterX() - circle.getRadius() &&
            line.getEndX() < circle.getCenterX() + circle.getRadius() &&
            line.getEndY() > circle.getCenterY() - circle.getRadius() &&
            line.getEndY() < circle.getCenterY() + circle.getRadius()) {
            
            cancelTimer(timer);
            canvas.removeElement(pane, line);
            canvas.removeElement(pane, circle);
        }
        
        line.setStartX(line.getStartX() + delta);
        line.setEndX(line.getEndX() + delta);
    }
    
    private boolean isOffCanvas() {
        return  line.getEndX() <= 0 ||
                line.getEndX() >= canvas.getWidth() ||
                line.getEndY() <= 0 ||
                line.getEndY() >= canvas.getHeight();
    }
}

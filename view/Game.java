package view;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
    
    private final int FRAME_HEIGHT = 700;
    private final int FRAME_WIDTH = 1000;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private void doSomething(Circle enemy) {
        double dy = 5;
        
        if (enemy.getCenterY() + enemy.getRadius() + dy > FRAME_HEIGHT ||
                enemy.getCenterY() - enemy.getRadius() - dy < 0) {
                dy = -dy;
            }
        enemy.setCenterY(enemy.getCenterY() + dy);
    }
    
    private void shoot(Circle circle, Pane pane, Line line) {
        double delta = 10;
        
        if (line.getEndX() > FRAME_WIDTH) {
            pane.getChildren().remove(line);
        }
        
        if (line.getEndX() > circle.getCenterX() - circle.getRadius() &&
            line.getEndX() < circle.getCenterX() + circle.getRadius()) {
            System.out.println("Hit!!!");
            pane.getChildren().remove(line);
        }
        
        line.setStartX(line.getStartX() + delta);
        line.setEndX(line.getEndX() + delta);
    }
    
    private void executeShoot(Circle circle, Pane pane, Line line) {
        Timer timer = new java.util.Timer();
        
        timer.schedule(new TimerTask() {
            public void run() {
                
                 Platform.runLater(new Runnable() {
                    public void run() {
                        shoot(circle, pane, line);
                    }
                });
            }
        }, 0, 50);
    }

    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        double xpos = 25;
        double ypos = 25;
        
        Pane root = new Pane();
        Circle circle = new Circle(xpos, ypos, 10);
        Circle circle2 = new Circle(FRAME_WIDTH - 25, FRAME_HEIGHT - 25, 10);
        
        Circle enemy = new Circle(400, 25, 10);
        Line line = new Line(25, 25, 35, 35);
        
        root.getChildren().addAll(circle, circle2, enemy);
        
        Scene scene = new Scene(root, FRAME_WIDTH, FRAME_HEIGHT);
        
        // sprites
        /*
        Timer timer = new java.util.Timer();
        
        timer.schedule(new TimerTask() {
            public void run() {
                
                 Platform.runLater(new Runnable() {
                    public void run() {
                        doSomething(enemy);
                    }
                });
            }
        }, 0, 10);
        */
        
        
        
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (circle.getCenterX() == enemy.getCenterX() && circle.getCenterY() == enemy.getCenterY()) {
                    System.out.println("You lose!!!");
                    return;
                }
                if (circle.getCenterX() == FRAME_WIDTH - 25 && circle.getCenterY() == FRAME_HEIGHT - 25)
                    System.out.println("You win!!!");
                switch (event.getCode()) {
                    case UP: moveUp(10, circle); break;
                    case DOWN: moveDown(10, circle); break;
                    case LEFT: moveLeft(10, circle); break;
                    case RIGHT: moveRight(10, circle); break;
                    case SPACE:
                        double x = circle.getCenterX();
                        double y = circle.getCenterY();
                        Line line2 = new Line(x, y, x + 10, y);
                        root.getChildren().add(line2);
                        executeShoot(enemy, root, line2); 
                        break;
                    default: break;
                }
                
                
            }
            
            public void moveUp(double dy, Circle circle) {
                if (circle.getCenterY() - circle.getRadius() - dy < 0) {
                    return;
                } else {
                    circle.setCenterY(circle.getCenterY() - dy);
                }
            }
            
            public void moveDown(double dy, Circle circle) {
                if (circle.getCenterY() + circle.getRadius() + dy > FRAME_HEIGHT) {
                    return;
                } else {
                    circle.setCenterY(circle.getCenterY() + dy);
                }
            }
            
            public void moveLeft(double dx, Circle circle) {
                if (circle.getCenterX() - circle.getRadius() - dx < 0) {
                    return;
                } else {
                    circle.setCenterX(circle.getCenterX() - dx);
                }
            }
            
            public void moveRight(double dx, Circle circle) {
                if (circle.getCenterX() + circle.getRadius() + dx > FRAME_WIDTH) {
                    return;
                } else {
                    circle.setCenterX(circle.getCenterX() + dx);
                }
            }
            
        });
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
}

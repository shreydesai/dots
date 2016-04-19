package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.BlueEnemy;
import model.Bullet;
import model.Enemy;
import model.GreenEnemy;
import model.Player;
import model.RedEnemy;
import view.Game;

public class Controller {
    
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    
    private Game game;
    private List<Node> nodes;
    private double elapsed = 0;
    
    public Controller() {
        game = new Game();
        nodes = new ArrayList<Node>();
    }

    public void start(Stage stage) {
        
        // Add player to the frame
        Player p = new Player(100, 100, 10);
        nodes.add(p.getNode());
        setKeyListener(game, p);
        game.setElements(nodes);
        
        new Timer().schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (elapsed >= 0 && elapsed < 20) {
                            setStage1();
                        } else if (elapsed >= 20 && elapsed < 30) {
                            setStage2();
                        } else {
                            setStage3();
                        }
                        elapsed += 5;
                    }
                });
                
            }
        }, 0, 5000);
        
        game.setMouseListener(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double[] coordinates = {p.getX(), p.getY(), event.getX(), event.getY()};
                new Bullet(game.getPane(), nodes, coordinates).start();
            }
        });

        // TODO: Finalize the motions for each enemy - perhaps make the
        // start method an abstract method and implement it differently
        // for each type of enemy
        
        // TODO: Log to the console whenever an enemy and what type of enemy
        // has been hit - also track the total number of points
        
        // TODO: Add a score board somewhere on the frame to track how many
        // points the player has and also the time as it's running out
        
        stage.setScene(game.getScene());
        stage.show();
    }
    
    private void setStage1() {
        for (int i = 0; i < 5; i++) {
            Enemy e = new RedEnemy(game.getPane(), getRandomX(), getRandomY(), 10);
            addEnemy(e);
        }
    }
    
    private void setStage2() {
        for (int i = 0; i < 6; i++) {
            Enemy e = null;
            if (i < 3) {
                e = new RedEnemy(game.getPane(), getRandomX(), getRandomY(), 10);
            } else {
                e = new GreenEnemy(game.getPane(), getRandomX(), getRandomY(), 10);
            }
            addEnemy(e);
        }
    }
    
    private void setStage3() {
        for (int i = 0; i < 8; i++) {
            Enemy e = null;
            if (i < 2) {
                e = new RedEnemy(game.getPane(), getRandomX(), getRandomY(), 10);
            } else if (i >= 2 && i <= 4) {
                e = new GreenEnemy(game.getPane(), getRandomX(), getRandomY(), 10);
            } else {
                e = new BlueEnemy(game.getPane(), getRandomX(), getRandomY(), 10);
            }
            addEnemy(e);
        }
    }
    
    private void addEnemy(Enemy e) {
        nodes.add(e.getNode());
        game.getPane().getChildren().add(e.getNode());  
    }
    
    private double getRandomX() {
        return Math.random() * (WIDTH - 2*20 + 1) + 20;
    }
    
    private double getRandomY() {
        return Math.random() * (HEIGHT - 2*20 + 1) + 20;
    }
    
    private void setKeyListener(Game game, Player p) {
        game.setKeyListener(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W: 
                    case UP: 
                        p.moveUp(); 
                        break;
                    case A: 
                    case LEFT: 
                        p.moveLeft(); 
                        break;
                    case S: 
                    case DOWN: 
                        p.moveDown(); 
                        break;
                    case D: 
                    case RIGHT: 
                        p.moveRight(); 
                        break;
                    default: 
                        break;
                }
            }
        });
    }

}

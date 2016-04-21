package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import view.InfoBox;
import view.EndBox;
import view.Game;

public class Frame {
    
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    
    private Game game;
    private InfoBox infobox;
    private EndBox endbox;
    private List<Node> nodes;
    private int points = 0;
    
    private Background background;
    
    public Frame() {
        game = new Game();
        infobox = new InfoBox();
        nodes = new ArrayList<Node>();
        background = new Background(this, game);
    }
    
    private int sec = 0;
    private boolean loop = true;
    
    public void start(Stage stage) {
        
        // Add player to the frame
        Player p = new Player(100, 100, 10);
        nodes.add(p.getNode());
        
        // Set key listener
        game.setKeyListener(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W: case UP: 
                        p.moveUp(); 
                        break;
                    case A: case LEFT: 
                        p.moveLeft(); 
                        break;
                    case S: case DOWN: 
                        p.moveDown(); 
                        break;
                    case D: case RIGHT: 
                        p.moveRight(); 
                        break;
                    case T:
                        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
                        System.out.println(threadSet.size());
                    default: 
                        break;
                }
            }
        });
        
        // Set mouse listener
        game.setMouseListener(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double[] coordinates = {p.getX(), p.getY(), event.getX(), event.getY()};
                new Bullet(game.getPane(), nodes, coordinates).start();
            }
        });
        
        // Set elements to frame
        game.setElements(nodes);
        
        // Set dialog box
        infobox.setTitle("Dots");
        infobox.setHeaderText("Information and Instructions");
        infobox.show();
         
        // Set timers
        background.run();
    
        // Create JavaFX window
        stage.setTitle(game.getTitle());
        stage.setScene(game.getScene());
        stage.show();
        
        Timer main = new Timer();
        main.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (loop && sec == 5) {
                            reset(stage);
                            main.cancel();
                            main.purge();
                            return;
                        }
                        sec++;
                    }
                });
            }
        }, 0, 1000);
    }
    
    private void reset(Stage stage) {
        loop = false;
        stage.close();
        
        for (int i = 0; i < background.getTimers().size(); i++) {
            background.getTimers().get(i).cancel();
            background.getTimers().get(i).purge();
        }
        
        endbox = new EndBox(stage, points);
        endbox.setTitle("Dots");
        endbox.setHeaderText("Game Over!");
        endbox.show();
    }
    
    public void calculatePoints(Node node) {
        switch (node.getId()) {
            case "Red":
                points += new RedEnemy().getPoints();
                break;
            case "Green":
                points += new GreenEnemy().getPoints();
                break;
            case "Blue":
                points += new BlueEnemy().getPoints();
            default:
                break;
        }
        game.configPoints(points);
    }
    
    public void setStage1() {
        for (int i = 0; i < 5; i++) {
            Enemy e = new RedEnemy(game.getPane(), getRandomX(), getRandomY(), 10);
            addEnemy(e);
        }
    }
    
    public void setStage2() {
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
    
    public void setStage3() {
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
}

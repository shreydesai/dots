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

import view.InfoBox;
import view.EndBox;
import view.Game;

/**
 * Represents the main controller of the canvas - its
 * responsibilities include running the background threads,
 * setting up the key and mouse listeners, and resetting the
 * canvas for a new game accordingly
 */
public class Frame {
    
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    
    private Game game;
    private InfoBox infobox;
    private EndBox endbox;
    private List<Node> nodes;
    private int points = 0;
    private int sec = 0;
    private boolean loop = true;
    
    private Background background;
    
    /**
     * Constructor
     */
    public Frame() {
        game = new Game();
        infobox = new InfoBox();
        nodes = new ArrayList<Node>();
        background = new Background(this, game);
    }
    
    /**
     * Starts the main stage of the application
     * @param stage instance of the JavaFX stage
     */
    public void start(Stage stage) {
        
        // Add player to the frame and update the class
        // element list with the player node
        Player p = new Player(100, 100, 10);
        nodes.add(p.getNode());
        
        // Set key listener - sets the W/A/S/D and
        // Up/Left/Down/Right keys for moving the player
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
                    default: 
                        break;
                }
            }
        });
        
        // Set mouse listener, creates and starts a new instance of
        // a bullet based on coordinates of the player position
        game.setMouseListener(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double[] coordinates = {p.getX(), p.getY(), event.getX(), event.getY()};
                new Bullet(game.getPane(), nodes, coordinates).start();
            }
        });
        
        // Set elements to main frame
        game.setElements(nodes);
        
        // Set information and instruction box at the beginning
        // of the game and pause main thread for a user response
        infobox.setTitle("Dots");
        infobox.setHeaderText("Information and Instructions");
        infobox.show();
         
        // Set and initialize all timers
        background.run();
    
        // Creates main JavaFX window - sets the title and 
        // scene of the application
        stage.setTitle(game.getTitle());
        stage.setScene(game.getScene());
        stage.show();
        
        // Runs the main timer of the game - resets the game after
        // 90 seconds and cancels the timer task for resetting
        Timer main = new Timer();
        main.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (loop && sec == 90) {
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
    
    /**
     * Responsible for resetting the frame - it closes the current window,
     * cancels and cleanses all background threads, and shows the dialog
     * box to display player points and game options
     * @param stage
     */
    private void reset(Stage stage) {
        // Closes the current running stage and sets 'loop' to false to
        // avoid resetting the game every second in the timer
        loop = false;
        stage.close();
        
        // Loops through all background timers, cancels them, and removes
        // the tasks from the timer queue
        for (int i = 0; i < background.getTimers().size(); i++) {
            background.getTimers().get(i).cancel();
            background.getTimers().get(i).purge();
        }
        
        // Initializes and displays the end dialog box
        endbox = new EndBox(stage, points);
        endbox.setTitle("Dots");
        endbox.setHeaderText("Game Over!");
        endbox.show();
    }
    
    /**
     * Calculates player points based on what type of enemy the
     * player has hit - updates the canvas accordingly
     * @param node enemy the player has hit
     */
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
    
    /**
     * Sets the first stage of the game - creates five red enemies with 
     * random X and Y positions and a radius of ten pixels
     */
    public void setStage1() {
        for (int i = 0; i < 5; i++) {
            Enemy e = new RedEnemy(game.getPane(), getRandomX(), getRandomY(), 10);
            addEnemy(e);
        }
    }
    
    /**
     * Sets the second stage of the game - creates three red enemies and four
     * green enemies with random X and Y positions and a radius of 10 pixels
     */
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
    
    /**
     * Sets the third stage of the game - creates two red enemies, three green
     * enemies and four blue enemies, all with random X and Y positions and 
     * a radius of 10 pixels
     */
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
    
    /**
     * Adds an enemy to the canvas - updates the class element list and 
     * game pane accordingly
     * @param e any enemy in the canvas - red, green, or blue
     */
    private void addEnemy(Enemy e) {
        nodes.add(e.getNode());
        game.getPane().getChildren().add(e.getNode());  
    }
    
    /**
     * Retrieves a random X position in the canvas - adjustment for
     * the width of the canvas and radius of the enemy
     * @return random X position
     */
    private double getRandomX() {
        return Math.random() * (WIDTH - 2*20 + 1) + 20;
    }
    
    /**
     * Retrieves a random Y position in the canvas - adjustment for
     * the height of the canvas and radius of the enemy
     * @return random Y position
     */
    private double getRandomY() {
        return Math.random() * (HEIGHT - 2*20 + 1) + 20;
    }
}

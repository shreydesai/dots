package view;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Represents the overall look and feel of the game which
 * includes all of the elements displayed on the canvas
 */
public class Game {
    
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    private final static String TITLE = "Dots";
    
    private final Pane pane;
    private final Scene scene;
    
    private Text timer;
    private Text pts;
    
    /**
     * Constructor
     */
    public Game() {
        pane = new Pane();
        scene = new Scene(pane, WIDTH, HEIGHT);
        
        // Set the timer and point panes
        timer = new Text(WIDTH - 175, 25, "Time: ");
        pts = new Text(WIDTH - 100, 25, "Points: 0");
        pane.getChildren().addAll(timer, pts);
        
        // Add the CSS file to set custom styles for
        // the enemy circles
        scene.getStylesheets().add("view/style.css");
    }
    
    /**
     * Sets up the timer pane and displays the time in the
     * format %s:%s%s, which sets the minute/tens/ones digits
     * @param value overall number of seconds to converted into
     *              the format for the timer pane
     */
    public void configTimer(int value) {
        int minutes = 0;
        int one, tenth;

        if (value > 60) {
            minutes = 1;
            value = value % 60;
        }
        
        // Extracts the ones and tens place
        one = value % 10;
        tenth = value / 10;
        
        timer.setText("Time: " + minutes + ":" + tenth + one);
    }
    
    /**
     * Sets up the point pane and displays the amount of points
     * the player has accumulated throughout the game
     * @param value number of points the player has
     */
    public void configPoints(int value) {
        pts.setText("Points: " + Integer.toString(value));
    }
    
    /**
     * Adds an element to the canvas
     * @param node generic JavaFX element
     */
    public void setElement(Node node) {
        pane.getChildren().add(node);
    }
    
    /**
     * Adds multiple elements to the canvas
     * @param node generic JavaFX element list 
     */
    public void setElements(List<Node> node) {
        pane.getChildren().addAll(node);
    }
    
    /**
     * Sets a key listener to the scene, which listens for user
     * input through pressing any key on the keyboard
     * @param event KeyListener event
     */
    public void setKeyListener(EventHandler<KeyEvent> event) {
        scene.setOnKeyPressed(event);
    }
    
    /**
     * Sets a mouse listener on the scene, which listens for wherever
     * the user clicks on a point in the game canvas
     * @param event MouseListener event
     */
    public void setMouseListener(EventHandler<MouseEvent> event) {
        scene.setOnMouseClicked(event);
    }
    
    /**
     * Getter method for the pane
     * @return pane for storing all game elements
     */
    public Pane getPane() {
        return pane;
    }
    
    /**
     * Getter method for the scene
     * @return scene for setting all game events
     */
    public Scene getScene() {
        return scene;
    }
    
    /**
     * Getter method for the canvas height
     * @return height of the canvas
     */
    public int getHeight() {
        return HEIGHT;
    }
    
    /**
     * Getter method for the canvas width
     * @return width of the canvas
     */
    public int getWidth() {
        return WIDTH;
    }
    
    /**
     * Getter method for the canvas title
     * @return title of the canvas
     */
    public String getTitle() {
        return TITLE;
    }
}

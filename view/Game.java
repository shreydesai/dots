package view;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Game {
    
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    private final static String TITLE = "Dots";
    
    private final Pane pane;
    private final Scene scene;
    
    public Game() {
        pane = new Pane();
        scene = new Scene(pane, WIDTH, HEIGHT);
        
        scene.getStylesheets().add("view/style.css");
    }
    
    public void setElement(Node node) {
        pane.getChildren().add(node);
    }
    
    public void setElements(List<Node> node) {
        pane.getChildren().addAll(node);
    }
    
    public void setKeyListener(EventHandler<KeyEvent> event) {
        scene.setOnKeyPressed(event);
    }
    
    public void setMouseListener(EventHandler<MouseEvent> event) {
        scene.setOnMouseClicked(event);
    }
    
    public Pane getPane() {
        return pane;
    }
    
    public Scene getScene() {
        return scene;
    }
    
    public int getHeight() {
        return HEIGHT;
    }
    
    public int getWidth() {
        return WIDTH;
    }
    
    public String getTitle() {
        return TITLE;
    }
}

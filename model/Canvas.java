package model;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Canvas {
    
    private final int HEIGHT = 700;
    private final int WIDTH = 1000;
    
    public int getHeight() {
        return HEIGHT;
    }
    
    public int getWidth() {
        return WIDTH;
    }
    
    public void addElement(Pane pane, Node node) {
        pane.getChildren().add(node);
    }
    
    public void addElements(Pane pane, List<Node> nodes) {
        pane.getChildren().addAll(nodes);
    }
    
    public void removeElement(Pane pane, Node node) {
        pane.getChildren().remove(node);
    }
    
    public void removeElements(Pane pane, List<Node> nodes) {
        pane.getChildren().removeAll(nodes);
    }
}

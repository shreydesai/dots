package model;

import javafx.scene.layout.Pane;

public interface Sprite {
    
    public void draw(Pane pane);
    
    public void moveUp();
    
    public void moveDown();
    
    public void moveLeft();
    
    public void moveRight();
    
    public String toString();
}

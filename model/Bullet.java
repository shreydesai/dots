package model;

import java.io.File;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Represents a bullet shot by the player in the
 * game - implements the Animation interface
 */
public class Bullet implements Animation {
    
    private final static int DELTA = 5;
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    
    private Pane pane;
    private List<Node> enemies;
    private Line line;
    private double[] coordinates;
    
    private boolean right;
    private double theta;
    
    private boolean playShot = true;
    
    /**
     * Constructors
     * @param pane setting of the game
     * @param enemies set of enemies currently in the game
     * @param coordinates X and Y position of the bullet
     */
    public Bullet(Pane pane, List<Node> enemies, double[] coordinates) {
        this.enemies = enemies;
        this.pane = pane;
        this.coordinates = coordinates;
        
        // Set the angle zero and the type of angle
        // trajectory as on the right side by default
        theta = 0;
        right = true;
        
        line = getLine();
        pane.getChildren().add(line);
    }
    
    @Override
    /**
     * Starts the animation sequence of the bullet by changing
     * the X and Y coordinates periodically
     */
    public void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Plays the sound of the bullet on the first instance
                // of the bullet being shot, and then exits
                if (playShot) {
                    playSound("src/sound/shot.wav");
                    playShot = false;
                }
                
                // Handles an instance where the bullet is off of the 
                // canvas or if it has collided with an enemy object
                handleOffCanvas(this);
                handleCollision(this);
                
                // Handles the X and Y positions depending on whether the
                // user has clicked on the left or right side of the player
                if (right) {
                    line.setStartX(line.getStartX() + DELTA * Math.cos(theta));
                    line.setEndX(line.getEndX() + DELTA * Math.cos(theta));
                    line.setStartY(line.getStartY() - DELTA * Math.sin(theta));
                    line.setEndY(line.getEndY() - DELTA * Math.sin(theta));
                } else {
                    line.setStartX(line.getStartX() - DELTA * Math.cos(theta));
                    line.setEndX(line.getEndX() - DELTA * Math.cos(theta));
                    line.setStartY(line.getStartY() + DELTA * Math.sin(theta));
                    line.setEndY(line.getEndY() + DELTA * Math.sin(theta));
                }
            }
        }.start();
    }
    
    /**
     * Calculates the initial trajectory of the bullet depending on where
     * the user has clicked on the screen; the algorithm finds the tangent of
     * the X and Y components between the user's mouse position and the player,
     * and positions the bullet in the direction of the mouse position
     * @return line node that represents the bullet with the correct
     *              X and Y position configurations
     */
    public Line getLine() {
        double cx = coordinates[0];
        double cy = coordinates[1];
        double px = coordinates[2];
        double py = coordinates[3];
        double componentX, componentY;
        
        // If the user's mouse X position is greater than the center 
        // X position of the player, then the if block is executed
        right = px > cx;
        
        if (right) {
            // Determines the X and Y components between the X and Y
            // coordinates of the mouse position and player in order to
            // find the tangent of the angle between them
            componentX = px - cx;
            componentY = cy - py;
            theta = Math.atan(componentY / componentX);
            
            // Points the new line in the direction of the mouse position
            // so it seems the bullet is going towards the direction
            // the user has set by clicking on the screen
            return new Line(
                    cx, cy, 
                    cx + 10 * Math.cos(theta), 
                    cy - 10 * Math.sin(theta)
            );
        } else {
            componentX = cx - px;
            componentY = py - cy;
            theta = Math.atan(componentY / componentX);
            return new Line(
                    cx, cy, 
                    cx - 10 * Math.cos(theta), 
                    cy + 10 * Math.sin(theta)
            );
        }
    }
    
    /**
     * Plays a sound effect defined by the game
     * @param path location where the .wav file is stored
     */
    private void playSound(String path) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(path)));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }   
    }
    
    /**
     * Handles an instance where the bullet has transitioned off
     * the canvas in the X or Y direction
     * @param timer current thread of the timer
     */
    private void handleOffCanvas(AnimationTimer timer) {
        if (disappeared()) {
            // The timer thread is stopped and the bullet node is
            // removed off of the scene
            timer.stop();
            pane.getChildren().remove(line);
        }
    }
    
    /**
     * Handles an instance where the bullet has hit one of the enemies
     * currently moving around in the canvas
     * @param timer current thread of the timer
     */
    private void handleCollision(AnimationTimer timer) {
        for (int i = 1; i < enemies.size(); i++) {
            Circle enemy = (Circle) enemies.get(i);
            if (collided(enemy) && pane.getChildren().contains(enemy)) {
                // Plays the sound of the bullet colliding with
                // the enemy's exterior boundaries
                playSound("src/sound/hit.wav");
                
                // The timer thread is stopped and the bullet/enemy nodes
                // are removed off of the scene
                timer.stop();
                pane.getChildren().removeAll(line, enemy);
            }
        }
    }
    
    /**
     * Determines whether the bullet has gone off of the canvas
     * @return if the bullet is or is not on the canvas
     */
    private boolean disappeared() {
        return line.getEndX() <= 0 ||
                line.getEndX() >= WIDTH ||
                line.getEndY() <= 0 ||
                line.getEndY() >= HEIGHT;
    }
    
    /**
     * Determines whether the bullet has collided with an enemy
     * @param enemy element node representing the enemy
     * @return if the bullet has or has not collided with an enemy
     */
    private boolean collided(Circle enemy) {
        return line.getEndX() > enemy.getCenterX() - enemy.getRadius() &&
                line.getEndX() < enemy.getCenterX() + enemy.getRadius() &&
                line.getEndY() > enemy.getCenterY() - enemy.getRadius() &&
                line.getEndY() < enemy.getCenterY() + enemy.getRadius();
    }
}

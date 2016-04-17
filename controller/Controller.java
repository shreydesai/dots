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
import javafx.stage.Stage;
import model.Bullet;
import model.Player;
import model.RedEnemy;
import view.Game;

public class Controller {
    
    private Game game;
    private List<Node> nodes;
    
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
                        RedEnemy r = new RedEnemy(game.getPane(), 200, 200, 10);
                        nodes.add(r.getNode());
                        game.getPane().getChildren().add(r.getNode());
                    }
                });
                
            }
        }, 0, 5000);

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
    
    public void setKeyListener(Game game, Player p) {
        game.setKeyListener(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP: 
                        p.moveUp(); 
                        break;
                    case DOWN: 
                        p.moveDown(); 
                        break;
                    case LEFT: 
                        p.moveLeft(); 
                        break;
                    case RIGHT: 
                        p.moveRight(); 
                        break;
                    case SPACE:
                        new Bullet(p.getNode(), nodes, game.getPane()).start();
                        break;
                    default: 
                        break;
                }
            }
        });
    }

}

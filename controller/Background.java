package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

import view.Game;

/**
 * Represents all of the background threads running when the
 * main game is in session. It includes timers to track points,
 * the clock, and the different stages of the game.
 */
public class Background {
    
    private Timer pointTimer, clockTimer, gameTimer;
    private List<Node> elements;
    private List<Timer> timers;
    private double elapsed = 0;
    private int seconds = 0;
    
    private Frame frame;
    private Game game;
    
    /**
     * Constructor
     * @param frame application frame
     * @param game application canvas
     */
    public Background(Frame frame, Game game) {
        this.frame = frame;
        this.game = game;
        elements = new ArrayList<Node>();
        timers = new ArrayList<Timer>();
    }
    
    /**
     * Schedules a timer in charge of setting the different
     * stages of the game depending on how much time has elapsed
     * @return timer task for game staging
     */
    private TimerTask getGameTimerTask() {
        return new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (elapsed >= 0 && elapsed < 20) {
                            frame.setStage1();
                        } else if (elapsed >= 20 && elapsed < 30) {
                            frame.setStage2();
                        } else {
                            frame.setStage3();
                        }
                        elapsed += 5;
                    }
                });
                
            }
        };
    }
    
    /**
     * Schedules a timer in charge of configuring the timer
     * pane on the game canvas and updating it at an interval
     * @return timer task for tracking time
     */
    private TimerTask getClockTimerTask() {
        return new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        game.configTimer(seconds);
                        seconds++;
                    }
                });
            }
        };
    }
    
    /**
     * Schedules a timer in charge of tracking points - it gets a
     * list of nodes in the current pane and updates a class list with
     * these nodes - if a node no longer exists in the canvas, then that
     * node is casted to a circle and the point pane is updated accordingly
     * @return timer task for tracking points
     */
    private TimerTask getPointTimerTask() {
        return new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        for (Node node : game.getPane().getChildren()) {
                            try {
                                Circle c = (Circle) node;
                                if (!elements.contains(c)) {
                                    elements.add(c);
                                }
                                for (Node node2 : elements) {
                                    if (!game.getPane().getChildren().contains(node2)) {
                                        frame.calculatePoints(node2);
                                        elements.remove(node2);
                                    }
                                }
                            } catch (Exception e) {
                                continue;
                            }
                        }
                    }
                });
            }
        };
    }
    
    /**
     * Sets the game timer and schedules a new thread for
     * running every five seconds with no delay
     */
    private void setGameTimer() {
        gameTimer = new Timer();
        gameTimer.schedule(getGameTimerTask(), 0, 5000);
    }
    
    /**
     * Sets a clock timer and schedules a new thread for
     * running every one second with no delay
     */
    private void setClockTimer() {
        clockTimer = new Timer();
        clockTimer.schedule(getClockTimerTask(), 0, 1000);
    }
    
    /**
     * Sets a point timer and schedules a new thread for
     * running every five hundred milliseconds with no delay
     */
    private void setPointTimer() {
        pointTimer = new Timer();
        pointTimer.schedule(getPointTimerTask(), 0, 500);
    }
    
    /**
     * Getter method for the game timer
     * @return current instance of the game timer
     */
    public Timer getGameTimer() {
        return gameTimer;
    }
    
    /**
     * Getter method for the clock timer
     * @return current instance of the clock timer
     */
    public Timer getClockTimer() {
        return clockTimer;
    }
    
    /**
     * Getter method for the point timer
     * @return current instance of the point timer
     */
    public Timer getPointTimer() {
        return pointTimer;
    }
    
    /**
     * Getter method for a generic list of all timers
     * @return list of all background timers
     */
    public List<Timer> getTimers() {
        return timers;
    }
    
    /**
     * Initializes all of the background threads and updates
     * a list of the timers with three of them - if the list has
     * timers already, then it should be cleared
     */
    public void run() {
        setGameTimer();
        setClockTimer();
        setPointTimer();
        
        if (timers.size() != 0) timers.clear();
        timers.add(gameTimer);
        timers.add(clockTimer);
        timers.add(pointTimer);
    }

}

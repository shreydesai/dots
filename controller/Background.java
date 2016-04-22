package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

import view.Game;

public class Background {
    
    private Timer pointTimer, clockTimer, gameTimer;
    private List<Node> elements;
    private List<Timer> timers;
    private double elapsed = 0;
    private int seconds = 0;
    
    private Frame frame;
    private Game game;
    
    public Background(Frame frame, Game game) {
        this.frame = frame;
        this.game = game;
        elements = new ArrayList<Node>();
        timers = new ArrayList<Timer>();
    }
    
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
    
    private void setGameTimer() {
        gameTimer = new Timer();
        gameTimer.schedule(getGameTimerTask(), 0, 5000);
    }
    
    private void setClockTimer() {
        clockTimer = new Timer();
        clockTimer.schedule(getClockTimerTask(), 0, 1000);
    }
    
    private void setPointTimer() {
        pointTimer = new Timer();
        pointTimer.schedule(getPointTimerTask(), 0, 500);
    }
    
    public Timer getGameTimer() {
        return gameTimer;
    }
    
    public Timer getClockTimer() {
        return clockTimer;
    }
    
    public Timer getPointTimer() {
        return pointTimer;
    }
    
    public List<Timer> getTimers() {
        return timers;
    }
    
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

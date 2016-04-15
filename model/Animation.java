package model;

import java.util.Timer;

public interface Animation {
    
    public void initialize();
    
    public void setTimer();
    
    public Timer getTimer();
    
    public void cancelTimer(Timer timer);
}

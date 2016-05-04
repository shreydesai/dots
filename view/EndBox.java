package view;

import java.util.Optional;

import controller.Frame;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * Represents the dialog box displayed at the end of the game to 
 * show the player's points and options to restart or exit the game
 */
public class EndBox implements DialogBox {
    
    private Alert alert;
    private Stage stage;
    private int points;
    
    private ButtonType yes;
    private ButtonType no;
    
    /**
     * Constructor
     * @param stage JavaFX primary stage
     * @param points number of points the player has
     */
    public EndBox(Stage stage, int points) {
        this.stage = stage;
        this.points = points;
        
        alert = new Alert(AlertType.CONFIRMATION);
        yes = new ButtonType("Yes", ButtonData.YES);
        no = new ButtonType("No", ButtonData.CANCEL_CLOSE);
    }
    
    /**
     * Sets the title of the dialog box
     * @param text title to be displayed
     */
    @Override
    public void setTitle(String text) {
        alert.setTitle(text);
    }
    
    /**
     * Sets the header of the dialog box
     * @param text header title to be displayed
     */
    @Override
    public void setHeaderText(String text) {
        alert.setHeaderText(text);
    }
    
    /**
     * Sets the content of the dialog box
     * @param points number of points the player has
     */
    public void setContentText(int points) {
        String prefix = "";
        String suffix = "You scored " + points + " points." + 
                        "\n\nDo you want to play again?";
        
        // Adds a different message to the content depending
        // on how many points the user has accumulated
        if (points < 300) {
            prefix = "Weak. ";
        } else if (points >= 300 && points < 500) {
            prefix = "Pretty good. ";
        } else if (points >= 500 && points < 700) {
            prefix = "Nice! ";
        } else {
            prefix = "Wow!!! ";
        }
        alert.setContentText(prefix += suffix);
    }
    
    /**
     * Displays the dialog box and sets the function of the
     * 'yes' and 'no' buttons on the box
     */
    @Override
    public void show() {
        setContentText(points);
        
        // Configures the 'yes' and 'no' buttons on the dialog box
        // and pauses the main thread for the user response
        alert.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == yes){
            // Start a new instance of the controller which 
            // functionally restarts the game
            new Frame().start(stage);
        } else {
            // Exit the platform completely with an exit code
            // of 0, which is 'successful'
            System.exit(0);
        }
    }

}

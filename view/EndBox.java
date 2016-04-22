package view;

import java.util.Optional;

import controller.Frame;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class EndBox implements DialogBox {
    
    private Alert alert;
    private Stage stage;
    private int points;
    
    private ButtonType yes;
    private ButtonType no;
    
    public EndBox(Stage stage, int points) {
        this.stage = stage;
        this.points = points;
        
        alert = new Alert(AlertType.CONFIRMATION);
        yes = new ButtonType("Yes", ButtonData.YES);
        no = new ButtonType("No", ButtonData.CANCEL_CLOSE);
    }

    @Override
    public void setTitle(String text) {
        alert.setTitle(text);
    }

    @Override
    public void setHeaderText(String text) {
        alert.setHeaderText(text);
    }

    public void setContentText(int points) {
        String prefix = "";
        String suffix = "You scored " + points + " points." + 
                        "\n\nDo you want to play again?";
        if (points < 100) {
            prefix = "Weak. ";
        } else if (points >= 100 && points < 300) {
            prefix = "Pretty good. ";
        } else if (points >= 300 && points < 500) {
            prefix = "Nice! ";
        } else {
            prefix = "Wow!!! ";
        }
        alert.setContentText(prefix += suffix);
    }

    @Override
    public void show() {
        setContentText(points);
        alert.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == yes){
            new Frame().start(stage);
        } else {
            System.exit(0);
        }
    }

}

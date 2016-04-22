package view;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Represents the information and instruction box displayed at the
 * beginning of the game when the user opens the application
 */
public class InfoBox implements DialogBox {
    
    private Alert alert;
    private Scanner in;
    private String raw, info;
    
    /**
     * Constructor
     */
    public InfoBox() {
        alert = new Alert(AlertType.INFORMATION);
        raw = info = "";
        
        // Reads in the contents of the 'content.txt' file, splits the
        // text on semicolons, and adds spaces between blocks
        try {
            in = new Scanner(new File("src/view/content.txt"));
            while (in.hasNext()) {
                raw += in.nextLine();
            }
            for (String p : raw.split(";")) {
                info += p + "\n\n";
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    /**
     * Sets the title of the info box
     * @param text title to be displayed
     */
    @Override
    public void setTitle(String text) {
        alert.setTitle(text);
    }
    
    /**
     * Sets the header of the info box
     * @param text header title to be displayed
     */
    @Override
    public void setHeaderText(String text) {
        alert.setHeaderText(text);
    }
    
    /**
     * Sets the content of the info box
     */
    public void setContentText() {
        alert.setContentText(info);
    }
    
    /**
     * Displays the dialog box and pauses the main thread
     * for the user to click the button
     */
    @Override
    public void show() {
        setContentText();
        alert.showAndWait();
    }
}

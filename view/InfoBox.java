package view;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InfoBox implements DialogBox {
    
    private Alert alert;
    private Scanner in;
    private String raw, info;
    
    public InfoBox() {
        alert = new Alert(AlertType.INFORMATION);
        raw = info = "";
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
    
    @Override
    public void setTitle(String text) {
        alert.setTitle(text);
    }
    
    @Override
    public void setHeaderText(String text) {
        alert.setHeaderText(text);
    }
    
    public void setContentText() {
        alert.setContentText(info);
    }
    
    @Override
    public void show() {
        setContentText();
        alert.showAndWait();
    }
}

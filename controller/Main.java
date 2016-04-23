package controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * Main method - launches the application
     * @param args JavaFX main thread
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Creates an instance of the Frame class and launches
     * the stage and its dependencies
     * @param stage instance of the JavaFX stage
     */
    @Override
    public void start(Stage stage) throws Exception {
        new Frame().start(stage);
    }
    
}

package Shared;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
public class SimpleGUI extends Application {
@Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
    // Create a button and place it in the scene
        Button btOK = new Button("OK");
        Scene scene = new Scene(btOK, 200, 250);
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setTitle("MyJavaFX"); // Set the stage title
        primaryStage.show(); // Display the stage
    }
    public static void main(String[] args) {
        launch(args);
    }
}


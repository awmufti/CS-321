package Shared;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class SimpleGUI extends Application {
    @Override
    public void start(Stage primaryStage) 
    {
        Parent root;
        try 
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dataentry.fxml"));
            fxmlLoader.setRoot(new AnchorPane());
            root = FXMLLoader.load(getClass().getResource("dataentry.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        } 
        catch (IOException e){}
}
 
 public static void main(String[] args) {
        launch(args);
    }
}


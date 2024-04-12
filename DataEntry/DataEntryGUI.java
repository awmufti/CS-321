package DataEntry;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DataEntryGUI extends Application {


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Extended Registration Page");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        

        //title
        grid.add(new Label("Data Entry"), 1, 0);
        
        // Requester First Name
        grid.add(new Label("Requester First Name:"), 0, 1);
        TextField requesterFirstNameTextField = new TextField();
        grid.add(requesterFirstNameTextField, 1, 1);
        
        // Requester Last Name
        grid.add(new Label("Requester Last Name:"), 0, 2);
        TextField requesterLastNameTextField = new TextField();
        grid.add(requesterLastNameTextField, 1, 2);

        // Requester Email Address
        grid.add(new Label("Requester Email Address:"), 0, 3);
        TextField requesterEmailTextField = new TextField();
        grid.add(requesterEmailTextField, 1, 3);

        // Immigrant First Name
        grid.add(new Label("Immigrant First Name:"), 0, 4);
        TextField immigrantFirstNameTextField = new TextField();
        grid.add(immigrantFirstNameTextField, 1, 4);


        // Immigrant Last Name
        grid.add(new Label("Immigrant Last Name:"), 0, 5);
        TextField immigrantLastNameTextField = new TextField();
        grid.add(immigrantLastNameTextField, 1, 5);

        // Immigrant Date of Birth
        grid.add(new Label("Immigrant DoB:"), 0, 6);
        DatePicker immigrantDoBPicker = new DatePicker();
        grid.add(immigrantDoBPicker, 1, 6);

        // Immigrant Place of Birth - State
        grid.add(new Label("Immigrant Place of Birth - State:"), 0, 7);
        TextField immigrantBirthStateTextField = new TextField();
        grid.add(immigrantBirthStateTextField, 1, 7);

        // Immigrant Place of Birth - City
        grid.add(new Label("Immigrant Place of Birth - City:"), 0, 8);
        TextField immigrantBirthCityTextField = new TextField();
        grid.add(immigrantBirthCityTextField, 1, 8);

        // Requested Form Dropdown
        grid.add(new Label("Requested Form:"), 0, 9);
        ComboBox<String> requestedFormComboBox = new ComboBox<>();
        requestedFormComboBox.getItems().addAll(
                "Naturalization Certificate File",
                "Non-standard C-File",
                "Alien Registration Record",
                "Visa File",
                "Registry File"
        );
        grid.add(requestedFormComboBox, 1, 9);

        // Submit Button
        Button submitButton = new Button("Submit");
        grid.add(submitButton, 1, 10);

        Scene scene = new Scene(grid, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

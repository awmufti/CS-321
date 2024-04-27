package DataEntry;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

import Shared.Address;
import Shared.Form;
import Shared.SharedDataQueue;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DataEntryGUI  {


    Form form = new Form();
    public Scene createDataEntryScene(Stage primaryStage, SharedDataQueue queue, Runnable onBackToMenu) {
        primaryStage.setTitle("Data Entry Page");

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
        Button backToMenuButton = new Button("Back to Menu");
        backToMenuButton.setOnAction(e -> onBackToMenu.run());  
        VBox layout = new VBox(10);
        layout.getChildren().add(backToMenuButton);
        layout.getChildren().add(grid);
        submitButton.setOnAction(event -> {
            if (validateFields(requesterFirstNameTextField, requesterLastNameTextField, requesterEmailTextField,
            immigrantFirstNameTextField, immigrantLastNameTextField, immigrantBirthStateTextField,
            immigrantBirthCityTextField, immigrantDoBPicker, requestedFormComboBox)) {
            // Populate the Form object with data from fields
            updateFormFromFields(form, requesterFirstNameTextField, requesterLastNameTextField, requesterEmailTextField,
                                 immigrantFirstNameTextField, immigrantLastNameTextField, immigrantBirthStateTextField,
                                 immigrantBirthCityTextField, immigrantDoBPicker, requestedFormComboBox);

            queue.enqueue(form);
            System.out.println(queue.size());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Form has been submitted to the reviewer!");

            alert.showAndWait();
            clearFormFields(requesterFirstNameTextField, requesterLastNameTextField, requesterEmailTextField, immigrantFirstNameTextField, immigrantLastNameTextField, immigrantBirthStateTextField, immigrantBirthCityTextField, immigrantDoBPicker, requestedFormComboBox);
            form = null;
            } else {
                // Show error message
                Alert alert = new Alert(Alert.AlertType.ERROR, "Validation failed. Please check the input.");
                alert.showAndWait();
            }

        });
        Scene scene = new Scene(layout, 600, 500);
        return scene;
    }

    private void clearFormFields(TextField requesterFirstName, TextField requesterLastName, TextField requesterEmail,
            TextField immigrantFirstName, TextField immigrantLastName, TextField immigrantBirthState,
            TextField immigrantBirthCity, DatePicker dobPicker, ComboBox<String> formComboBox) {
        requesterFirstName.clear();
        requesterLastName.clear();
        requesterEmail.clear();
        immigrantFirstName.clear();
        immigrantLastName.clear();
        immigrantBirthState.clear();
        immigrantBirthCity.clear();
        dobPicker.setValue(null);
        formComboBox.setValue(null);
    }
    private void updateFormFromFields(Form form,TextField requesterFirstName, TextField requesterLastName, TextField requesterEmail,
                                      TextField immigrantFirstName, TextField immigrantLastName, TextField immigrantBirthState,
                                      TextField immigrantBirthCity, DatePicker dobPicker, ComboBox<String> formComboBox) {
        form.setRequesterFirstName(requesterFirstName.getText());
        form.setRequesterLastName(requesterLastName.getText());
        form.setRequesterEmail(requesterEmail.getText());
        form.setImmigrantFirstName(immigrantFirstName.getText());
        form.setImmigrantLastName(immigrantLastName.getText());
        Address placeOfBirth = new Address();
        placeOfBirth.setCity(immigrantBirthCity.getText());
        placeOfBirth.setState(immigrantBirthState.getText());
        form.setPlaceOfBirth(placeOfBirth);
        LocalDate localDate = dobPicker.getValue();
        if (localDate != null) {
            form.setDateOfBirth(localDate);
        }
        form.setformType(formComboBox.getValue());
    }
    private boolean validateFields(TextField requesterFirstName, TextField requesterLastName, TextField requesterEmail,
                                   TextField immigrantFirstName, TextField immigrantLastName, TextField immigrantBirthState,
                                   TextField immigrantBirthCity, DatePicker dobPicker, ComboBox<String> formComboBox) {
        // Validate non-null and non-empty
        if (isFieldEmpty(requesterFirstName) || isFieldEmpty(requesterLastName) || isFieldEmpty(requesterEmail) ||
            isFieldEmpty(immigrantFirstName) || isFieldEmpty(immigrantLastName) || isFieldEmpty(immigrantBirthState) ||
            isFieldEmpty(immigrantBirthCity) || dobPicker.getValue() == null || formComboBox.getValue() == null) {
            return false;
        }

        // Validate names
        if (!isValidName(requesterFirstName.getText()) || !isValidName(immigrantFirstName.getText()) ||
            !isValidName(requesterLastName.getText()) || !isValidName(immigrantLastName.getText())) {
            return false;
        }

        // Validate email
        if (!isValidEmail(requesterEmail.getText())) {
            return false;
        }

        // Validate DoB not more than 200 years ago
        if (!isValidDOB(dobPicker.getValue())) {
            return false;
        }

        return true; // All validations passed
    }

    private boolean isFieldEmpty(TextField field) {
        return field.getText() == null || field.getText().trim().isEmpty();
    }

    private boolean isValidName(String name) {
        return Pattern.matches("^[a-zA-Z\\s-]+$", name);
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", email);
    }

    private boolean isValidDOB(LocalDate dob) {
        return !dob.isAfter(LocalDate.now()) && !dob.isBefore(LocalDate.now().minusYears(200));
    }


}

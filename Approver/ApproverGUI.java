package Approver;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.regex.Pattern;

import Shared.Form;
import Shared.SharedDataQueue;

public class ApproverGUI {
    Form form = new Form();
    // Generate and return the scene for the Approver form
    public Scene createApproverScene(SharedDataQueue reviewerQueue, SharedDataQueue queue, Runnable onBackToMenu) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button generateButton = new Button("Retrieve next form ticket.");
        grid.add(generateButton, 1, 0, 2, 1);

        Button backToMenuButton = new Button("Back to Menu");
        backToMenuButton.setOnAction(e -> onBackToMenu.run());  

        TextField requesterFirstNameTextField = new TextField();
        grid.add(new Label("Requester First Name:"), 0, 1);
        grid.add(requesterFirstNameTextField, 1, 1);

        TextField requesterLastNameTextField = new TextField();
        grid.add(new Label("Requester Last Name:"), 0, 2);
        grid.add(requesterLastNameTextField, 1, 2);

        TextField requesterEmailTextField = new TextField();
        grid.add(new Label("Requester Email Address:"), 0, 3);
        grid.add(requesterEmailTextField, 1, 3);

        TextField immigrantFirstNameTextField = new TextField();
        grid.add(new Label("Immigrant First Name:"), 0, 4);
        grid.add(immigrantFirstNameTextField, 1, 4);

        TextField immigrantLastNameTextField = new TextField();
        grid.add(new Label("Immigrant Last Name:"), 0, 5);
        grid.add(immigrantLastNameTextField, 1, 5);

        DatePicker immigrantDoBPicker = new DatePicker();
        grid.add(new Label("Immigrant DoB:"), 0, 6);
        grid.add(immigrantDoBPicker, 1, 6);

        TextField immigrantBirthStateTextField = new TextField();
        grid.add(new Label("Immigrant Place of Birth - State:"), 0, 7);
        grid.add(immigrantBirthStateTextField, 1, 7);

        TextField immigrantBirthCityTextField = new TextField();
        grid.add(new Label("Immigrant Place of Birth - City:"), 0, 8);
        grid.add(immigrantBirthCityTextField, 1, 8);

        ComboBox<String> requestedFormComboBox = new ComboBox<>();
        requestedFormComboBox.getItems().addAll(
            "Naturalization Certificate File",
            "Non-standard C-File",
            "Alien Registration Record",
            "Visa File",
            "Registry File"
        );
        grid.add(new Label("Requested Form:"), 0, 9);
        grid.add(requestedFormComboBox, 1, 9);

        Button acceptButton = new Button("Accept");
        Button denyButton = new Button("Deny");
        grid.add(acceptButton, 0, 10);
        grid.add(denyButton, 1, 10);
        VBox layout = new VBox(10);
        layout.getChildren().add(backToMenuButton);
        layout.getChildren().add(grid);
        // Event handlers
        setEventHandlers(reviewerQueue, queue, generateButton, acceptButton, denyButton, requesterFirstNameTextField, requesterLastNameTextField,
                         requesterEmailTextField, immigrantFirstNameTextField, immigrantLastNameTextField,
                         immigrantBirthStateTextField, immigrantBirthCityTextField, immigrantDoBPicker, requestedFormComboBox);

        return new Scene(layout, 600, 700);
    }

    private void setEventHandlers(SharedDataQueue reviewerQueue, SharedDataQueue queue, Button generate, Button accept, Button deny, 
                                  TextField requesterFirstName, TextField requesterLastName, TextField requesterEmail, 
                                  TextField immigrantFirstName, TextField immigrantLastName, TextField immigrantBirthState, 
                                  TextField immigrantBirthCity, DatePicker dobPicker, ComboBox<String> formComboBox) {
        
        generate.setOnAction(event -> {
            if(queue.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "There are no items in the queue.");
                alert.showAndWait();
                return;
            }
            form = queue.dequeue();
            requesterFirstName.setText(form.getRequesterFirstName());
            requesterLastName.setText(form.getRequesterLastName());
            requesterEmail.setText(form.getRequesterEmail());
            immigrantFirstName.setText(form.getImmigrantFirstName());
            immigrantLastName.setText(form.getImmigrantLastName());
            dobPicker.setValue(form.getDateOfBirth());
            immigrantBirthState.setText(form.getPlaceOfBirth().getState());
            immigrantBirthCity.setText(form.getPlaceOfBirth().getCity());
            formComboBox.getSelectionModel().select(form.getFormType());
        });

        accept.setOnAction(event -> {
            if (validateFields(requesterFirstName, requesterLastName, requesterEmail,
            immigrantFirstName, immigrantLastName, immigrantBirthState,
            immigrantBirthCity, dobPicker, formComboBox)) {
                clearFormFields(requesterFirstName, requesterLastName, requesterEmail,
                immigrantFirstName, immigrantLastName, immigrantBirthState,
                immigrantBirthCity, dobPicker, formComboBox);
                form = null;   
                // Proceed with processing
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Form has been accepted and processed!");
                alert.showAndWait();
 
            } else {
                // Show error message
                Alert alert = new Alert(Alert.AlertType.ERROR, "Validation failed. Please check the input.");
                alert.showAndWait();
            }

        });

        deny.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Form has been sent back to the reviewer.");
            reviewerQueue.enqueue(form);
            alert.showAndWait();
            clearFormFields(requesterFirstName, requesterLastName, requesterEmail,
                            immigrantFirstName, immigrantLastName, immigrantBirthState,
                            immigrantBirthCity, dobPicker, formComboBox);
        });
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
        dobPicker.setValue(null);;
        formComboBox.setValue(null);
    }

    private void clearFormFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear();
        }
    }
}
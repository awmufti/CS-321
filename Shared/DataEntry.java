package Shared;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DataEntry {

    @FXML
    private TextField immigrantDOB;

    @FXML
    private TextField immigrantFirstName;

    @FXML
    private TextField immigrantLastName;

    @FXML
    private TextField immigrantPOB;

    @FXML
    private TextField requesterAddress;

    @FXML
    private TextField requesterEmail;

    @FXML
    private TextField requesterFirstName;

    @FXML
    private TextField requesterLastName;

    @FXML
    void immigrantDOBButton(ActionEvent event) {
        System.out.println("immigrantDOBButton button pressed");
    }

    @FXML
    void immigrantFirstNameButton(ActionEvent event) {
        System.out.println("immigrantFirstNameButton button pressed");
    }

    @FXML
    void immigrantLastNameButton(ActionEvent event) {
        System.out.println("immigrantLastNameButton button pressed");
    }

    @FXML
    void immigrantPOBButton(ActionEvent event) {
        System.out.println("immigrantPOBButton button pressed");
    }

    @FXML
    void requesterAddress(ActionEvent event) {
        System.out.println("requesterAddress button pressed");
    }

    @FXML
    void requesterEmailButton(ActionEvent event) {
        System.out.println("requesterEmailButton button pressed");
    }

    @FXML
    void requesterFirstNameButton(ActionEvent event) {
        System.out.println("requesterFirstNameButton button pressed");
    }

    @FXML
    void requesterLastNameButton(ActionEvent event) {
        System.out.println("requesterLastNameButton button pressed");
    }

    @FXML
    void sendFormButton(ActionEvent event) {
        System.out.println("sendFormButton button pressed");
    }

}


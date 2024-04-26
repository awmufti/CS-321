package Shared;
import Approver.ApproverGUI;
import DataEntry.DataEntryGUI;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        VBox menu = new VBox(10);
        menu.setAlignment(Pos.CENTER);

        Button btnApprover = new Button("Approver Form");
        Button btnOtherForm = new Button("Data Entry Form"); // Replace with actual form name

        btnApprover.setOnAction(e -> switchToApproverForm());
        btnOtherForm.setOnAction(e -> switchToDataEntryForm()); // Implement this method similarly

        menu.getChildren().addAll(btnApprover, btnOtherForm);

        Scene mainScene = new Scene(menu, 400, 300);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void switchToApproverForm() {
        ApproverGUI approverGUI = new ApproverGUI();
        Scene approverScene = approverGUI.createApproverScene(() -> switchToMainMenu());
        primaryStage.setScene(approverScene);
    }

    private void switchToDataEntryForm() {
        DataEntryGUI dataentryGUI = new DataEntryGUI();
        Scene dataentryScene = dataentryGUI.createDataEntryScene(primaryStage, () -> switchToMainMenu());
        primaryStage.setScene(dataentryScene);
    }
    private void switchToMainMenu() {
        // Assume createMainMenuScene() is a method that creates the main menu scene
        Scene mainMenuScene = createMainMenuScene();
        primaryStage.setTitle("Menu");
        primaryStage.setScene(mainMenuScene);
    }
    private Scene createMainMenuScene(){
        VBox menu = new VBox(10);
        menu.setAlignment(Pos.CENTER);

        Button btnApprover = new Button("Approver Form");
        Button btnOtherForm = new Button("Data Entry Form"); // Replace with actual form name

        btnApprover.setOnAction(e -> switchToApproverForm());
        btnOtherForm.setOnAction(e -> switchToDataEntryForm()); // Implement this method similarly

        menu.getChildren().addAll(btnApprover, btnOtherForm);

        Scene mainScene = new Scene(menu, 400, 300);
        return mainScene;
    }
    public static void main(String[] args) {
        launch(args);
    }
}

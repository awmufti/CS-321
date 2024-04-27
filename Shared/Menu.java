package Shared;
import Approver.ApproverGUI;
import DataEntry.DataEntryGUI;
import Reviewer.ReviewerGUI;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application {

    private Stage primaryStage;
    public SharedDataQueue reviewerQueue;
    public SharedDataQueue approverQueue;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        reviewerQueue = new SharedDataQueue();
        approverQueue = new SharedDataQueue();
        VBox menu = new VBox(10);
        menu.setAlignment(Pos.CENTER);

        Button btnDataEntry = new Button("Data Entry Form");
        Button btnReviewer = new Button("Revewier Form"); 
        Button btnApprover = new Button("Approver Form"); 

        btnDataEntry.setOnAction(e -> switchToDataEntryForm());
        btnReviewer.setOnAction(e -> switchToReviewerForm()); 
        btnApprover.setOnAction(e -> switchToApproverForm()); 

        menu.getChildren().addAll(btnDataEntry, btnReviewer, btnApprover);

        Scene mainScene = new Scene(menu, 400, 300);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void switchToApproverForm() {
        ApproverGUI approverGUI = new ApproverGUI();
        primaryStage.setTitle("Approver Form");
        Scene approverScene = approverGUI.createApproverScene(reviewerQueue,approverQueue, () -> switchToMainMenu());
        primaryStage.setScene(approverScene);
    }
    private void switchToReviewerForm() {
        primaryStage.setTitle("Reviewer Form");
        ReviewerGUI reviewerGUI = new ReviewerGUI();
        System.out.println(reviewerQueue.size());
        Scene reviewerScene = reviewerGUI.createReviewerScene(reviewerQueue, approverQueue,() -> switchToMainMenu());
        primaryStage.setScene(reviewerScene);
    }
    private void switchToDataEntryForm() {
        DataEntryGUI dataentryGUI = new DataEntryGUI();
        Scene dataentryScene = dataentryGUI.createDataEntryScene(primaryStage, reviewerQueue, () -> switchToMainMenu());
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


        Button btnDataEntry = new Button("Data Entry Form");
        Button btnReviewer = new Button("Reviewer Form"); 
        Button btnApprover = new Button("Approver Form"); 

        btnDataEntry.setOnAction(e -> switchToDataEntryForm());
        btnReviewer.setOnAction(e -> switchToReviewerForm()); 
        btnApprover.setOnAction(e -> switchToApproverForm()); 

        menu.getChildren().addAll(btnDataEntry, btnReviewer, btnApprover);
        Scene mainScene = new Scene(menu, 400, 300);
        return mainScene;
    }
    public static void main(String[] args) {
        launch(args);
    }
}

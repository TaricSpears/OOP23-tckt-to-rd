package it.unibo.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChoicePopUp extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Choice Pop Up");

        Button openButton = new Button("Open Pop Up");
        openButton.setOnAction(e -> showChoicePopUp());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(openButton);

        root.getChildren().add(layout);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showChoicePopUp() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Choice Pop Up");

        Label label = new Label("Select an option:");

        Button option1Button = new Button("Option 1");
        option1Button.setOnAction(e -> System.out.println("Option 1 selected"));

        Button option2Button = new Button("Option 2");
        option2Button.setOnAction(e -> System.out.println("Option 2 selected"));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(label, option1Button, option2Button);

        Scene popupScene = new Scene(layout, 200, 150);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

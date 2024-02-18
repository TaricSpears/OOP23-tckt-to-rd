package it.unibo.view;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import it.unibo.controller.gamecontroller.api.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class PlayerInterface extends VBox {

    public PlayerInterface(final MainController controller, final double width, final double height) {
        final Screen screen = Screen.getPrimary();
        final Rectangle2D bounds = screen.getVisualBounds();
        final Button endGame = new Button("End Game");
        final Button rules = new Button("Rules");
        final Label phase = new Label(controller.getGameController().getPhaseController().toString());
        final ObjectiveBox objectiveBox = new ObjectiveBox(controller, this);
        final CardBox cardBox = new CardBox(controller, this);

        phase.setWrapText(true);
        phase.setMaxWidth(bounds.getWidth() * 0.15);

        this.getChildren().add(phase);
        this.getChildren().add(endGame);
        this.getChildren().add(rules);

        this.setPadding(new Insets(20));
        this.setSpacing(20);

        endGame.setOnAction(event -> {
            controller.getGameController().endGame();
        });

        rules.setOnAction(event -> {

            String rulesText = "";
            try {
                rulesText = Files.readString(Path.of("src/main/resources/text/Rules.txt"), StandardCharsets.UTF_8);
                Alert alert = new Alert(AlertType.INFORMATION, rulesText);
                alert.setResizable(false);
                alert.getDialogPane().setMaxSize(bounds.getWidth() * 0.6, bounds.getHeight() * 0.5);
                alert.getDialogPane().setMinSize(bounds.getWidth() * 0.6, bounds.getHeight() * 0.5);

                alert.setTitle("Rules");
                alert.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        final Button endTurn = new Button("End Turn");
        endTurn.setOnAction(event -> {
            controller.getGameController().endTurn();
        });
        endTurn.setDisable(!controller.getGameController().getPhaseController().isEndPhase());

        final Button drawTrain = new Button("Draw Train Card");
        drawTrain.setOnAction(event -> {
            new DrawTrainCardPopUp(controller.getGameController().handleDrawTrainCard());
        });
        drawTrain.setDisable(controller.getGameController().getPhaseController().isEndPhase());

        final Button drawObjective = new Button("Draw new Objective");
        drawObjective.setOnAction(event -> {
            controller.getGameController().handleDrawObjectiveCard();
        });
        drawObjective.setDisable(!controller.getGameController().getPhaseController().isMidPhase());

        this.getChildren().addAll(drawTrain, drawObjective, endTurn, objectiveBox, cardBox);

        this.setMinSize(width, height);
    }
}

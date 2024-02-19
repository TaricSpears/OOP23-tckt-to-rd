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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class PlayerInterface extends VBox {

    public PlayerInterface(final MainController controller, final double width, final double height) {
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        final Screen screen = Screen.getPrimary();
        final Rectangle2D bounds = screen.getVisualBounds();
        // final Button endGame = new Button("End Game");
        final Button rules = new Button("Rules");
        final Label phase = new Label(controller.getPhaseController().toString());
        final ObjectiveBox objectiveBox = new ObjectiveBox(controller, this);
        final CardBox cardBox = new CardBox(controller, this);

        phase.setWrapText(true);
        phase.setMaxWidth(this.getMinWidth() * 0.8);
        objectiveBox.setMaxWidth(this.getMinWidth() * 0.8);

        this.getChildren().add(phase);
        // this.getChildren().add(endGame);

        this.setPadding(new Insets(20));
        this.setSpacing(10);

        /*
         * endGame.setOnAction(event -> {
         * controller.getGameController().endGame();
         * });
         */

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
            new EndTurnPopUp(controller);
        });
        endTurn.setDisable(!controller.getPhaseController().isEndPhase(controller));

        final Button drawTrain = new Button("Draw Train Card");
        drawTrain.setOnAction(event -> {
            new DrawTrainCardPopUp(controller.getGameController().handleDrawTrainCard().getColor());
        });
        drawTrain.setDisable(controller.getPhaseController().isEndPhase(controller));

        final Button drawObjective = new Button("Draw new Objective");
        drawObjective.setOnAction(event -> {
            controller.getGameController().handleDrawObjectiveCard();
        });
        drawObjective.setDisable(!controller.getPhaseController().isMidPhase());

        final HBox controlBox = new HBox(endTurn, rules);
        final HBox drawBox = new HBox(drawObjective, drawTrain);
        controlBox.setSpacing(2.0);
        drawBox.setSpacing(2.0);

        this.getChildren().addAll(drawBox, controlBox, objectiveBox, cardBox);

    }
}

package it.unibo.view;

import it.unibo.controller.gamecontroller.api.MainController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerInterface extends VBox {

    public PlayerInterface(final MainController controller, final double width, final double height) {
        final Button endGame = new Button("End Game");
        final Label phase = new Label(controller.getGameController().getPhaseController().toString());

        this.getChildren().add(phase);
        this.getChildren().add(endGame);

        this.setPadding(new Insets(20));
        this.setSpacing(20);

        endGame.setOnAction(event -> {
            controller.getGameController().endGame();
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

        this.getChildren().addAll(drawTrain, drawObjective, endTurn, new ObjectiveBox(controller));

        this.setMinSize(width, height);
    }
}

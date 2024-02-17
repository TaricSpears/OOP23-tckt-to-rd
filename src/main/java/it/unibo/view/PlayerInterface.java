package it.unibo.view;

import it.unibo.controller.gamecontroller.api.MainController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PlayerInterface extends VBox {

    public PlayerInterface(final MainController controller, final double width, final double height) {
        final Button endGame = new Button("End Game");

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

        final Button drawTrain = new Button("Draw Train Card");
        drawTrain.setOnAction(event -> {
            new DrawTrainCardPopUp(controller.handleDrawTrainCard());
        });
        final Button drawObjective = new Button("Draw new Objective");

        this.getChildren().addAll(drawTrain, drawObjective, endTurn, new ObjectiveBox(controller));

        this.setMinSize(width, height);
    }
}

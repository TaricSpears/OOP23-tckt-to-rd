package it.unibo.view;

import java.awt.Color;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.model.card.impl.TrainCardImpl;
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
            new DrawTrainCardPopUp(new TrainCardImpl(Color.BLACK));
        });
        final Button drawObjective = new Button("Draw new Objective");

        this.getChildren().addAll(drawTrain, drawObjective, endTurn, new ObjectiveBox(controller));

        this.setMinSize(width, height);
    }
}

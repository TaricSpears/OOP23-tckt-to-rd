package it.unibo.view.FillRoute.impl;

import it.unibo.controller.fillroutecontroller.api.FillRoute;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;

import java.awt.Color;

/**
 * Represents the frame for the color selection phase in the case the route is
 * gray, extends {@link Dialog}.
 */
public final class FillRouteViewImpl extends Dialog<Color> {

    private final Dialog<String> dialog;
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public FillRouteViewImpl(final FillRoute fillRoute) {

        dialog = new ChoiceDialog<>(null, fillRoute.getAvailableRoutes(fillRoute));
    }

    public Color openPopUp() {
        dialog.setTitle("Fill Route");
        dialog.setHeaderText("Choose the color of cards you want to use to fill the route.");
        dialog.setContentText("Choose your color:");
        dialog.showAndWait();
        if (dialog.getResult() != null) {
            if (dialog.getResult().equals("RED")) {
                return Color.RED;
            } else if (dialog.getResult().equals("BLACK")) {
                return Color.BLACK;
            } else if (dialog.getResult().equals("MAGENTA")) {
                return Color.MAGENTA;
            } else if (dialog.getResult().equals("ORANGE")) {
                return Color.ORANGE;
            } else if (dialog.getResult().equals("YELLOW")) {
                return Color.YELLOW;
            } else if (dialog.getResult().equals("GREEN")) {
                return Color.GREEN;
            } else if (dialog.getResult().equals("BLUE")) {
                return Color.BLUE;
            } else if (dialog.getResult().equals("WHITE")) {
                return Color.WHITE;
            } else {
                alert.setTitle("Error");
                alert.setHeaderText("You have to choose a color.");
                alert.showAndWait();
                return null;

            }
        } else {
            alert.setTitle("Error");
            alert.setHeaderText("You have to choose a color.");
            alert.showAndWait();
            return null;
        }

    }

}

package it.unibo.view.FillRoute.impl;

import it.unibo.controller.fillroutecontroller.api.FillRoute;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;

import java.awt.Color;

/**
 * Implementation of the {@link FillRoute} interface.
 * Represents the frame for the color selection phase in the case the route is
 * gray.
 */
public final class FillRouteViewImpl extends Dialog<Color> {

    private final Dialog<Color> dialog;
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public FillRouteViewImpl(FillRoute fillRoute) {

        dialog = new ChoiceDialog<>(null, fillRoute.getAvailableRoutes(fillRoute));
    }

    public Color openPopUp() {
        dialog.setTitle("Fill Route");
        dialog.setHeaderText("Choose the color of cards you want to use to fill the route.");
        dialog.setContentText("Choose your color:");
        dialog.showAndWait();
        if (dialog.getResult() != null) {
            return dialog.getResult();
        } else {
            alert.setTitle("Error");
            alert.setHeaderText("You have to choose a color.");
            alert.showAndWait();
            return null;
        }

    }

}
package it.unibo.view.FillRoute.impl;

import it.unibo.controller.fillroutecontroller.api.FillRoute;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.awt.Color;
import javafx.stage.Stage;
import it.unibo.model.route.api.Route;
import java.util.Set;

/**
 * Implementation of the {@link FillRoute} interface.
 * Represents the frame for the color selection phase in the case the route is
 * gray.
 */
public class FillRouteViewImpl extends Dialog<Color> {

    private FillRoute fillRoute;
    private Dialog<Color> dialog = new ChoiceDialog<>(null, fillRoute.getAvailableRoutes(fillRoute));
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public FillRouteViewImpl(FillRoute fillRoute) {
        this.fillRoute = fillRoute;
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
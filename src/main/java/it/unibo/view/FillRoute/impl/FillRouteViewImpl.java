package it.unibo.view.FillRoute.impl;

import it.unibo.controller.fillroutecontroller.api.FillRoute;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import it.unibo.model.route.api.Route;
import java.util.Set;

/**
 * Implementation of the {@link FillRoute} interface.
 * Represents the frame for the color selection phase in the case the route is
 * gray.
 */
public class FillRouteViewImpl {

    private static final long serialVersionUID = 1L;
    private FillRoute fillRoute;

    public FillRouteViewImpl(FillRoute fillRoute) {
        this.fillRoute = fillRoute;
    }

    ChoiceDialog<String> dialog = new ChoiceDialog<>(null, fillRoute.getAvailableRoutes(fillRoute));

    public void openPopUp() {
        dialog.setTitle("Fill Route");
        dialog.setHeaderText("Choose the color of cards you want to use to fill the route.");
        dialog.setContentText("Choose your color:");
        dialog.showAndWait();

    }

}
package it.unibo.view.fillroute;

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

    /**
     * Constructor for the class.
     * 
     * @param fillRoute the fillRoute object you want to know the available routes
     *                  of
     */
    public FillRouteViewImpl(final FillRoute fillRoute) {

        dialog = new ChoiceDialog<>(null, fillRoute.getAvailableRoutes(fillRoute));
    }

    /**
     * Opens the pop-up for the color selection.
     * 
     * @return the color chosen by the player
     */
    public Color openPopUp() {
        dialog.setTitle("Fill Route");
        dialog.setHeaderText("Choose the color of cards you want to use to fill the route.");
        dialog.setContentText("Choose your color:");
        dialog.showAndWait();
        if (dialog.getResult() != null) {
            if ("RED".equals(dialog.getResult())) {
                return Color.RED;
            } else if ("BLACK".equals(dialog.getResult())) {
                return Color.BLACK;
            } else if ("MAGENTA".equals(dialog.getResult())) {
                return Color.MAGENTA;
            } else if ("ORANGE".equals(dialog.getResult())) {
                return Color.ORANGE;
            } else if ("YELLOW".equals(dialog.getResult())) {
                return Color.YELLOW;
            } else if ("GREEN".equals(dialog.getResult())) {
                return Color.GREEN;
            } else if ("BLUE".equals(dialog.getResult())) {
                return Color.BLUE;
            } else if ("WHITE".equals(dialog.getResult())) {
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

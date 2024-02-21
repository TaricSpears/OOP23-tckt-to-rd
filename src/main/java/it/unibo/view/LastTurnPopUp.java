package it.unibo.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class to create an alert in case of last turn.
 */
public class LastTurnPopUp {
    /**
     * Constructor for the class.
     */
    private static final double WIDTH = 300;
    private static final double HEIGHT = 200;

    private final Alert alert = new Alert(AlertType.INFORMATION);

    /**
     * Opens the popUp.
     */
    public LastTurnPopUp() {

        alert.setTitle("Ultimo turno");
        alert.setContentText("Ultimo turno di gioco!");

        alert.setResizable(false);
        alert.getDialogPane().setMaxSize(WIDTH, HEIGHT);
        alert.getDialogPane().setMinSize(WIDTH, HEIGHT);
    }

    /**
     * Displays the popUp.
     */
    public void display() {
        alert.showAndWait();
    }
}

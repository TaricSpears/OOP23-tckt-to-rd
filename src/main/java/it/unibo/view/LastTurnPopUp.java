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
    public LastTurnPopUp() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ultimo turno");
        alert.setContentText("Ultimo turno di gioco!");
        // TODO: fix with bounds of the window
        alert.setResizable(false);
        alert.getDialogPane().setMaxSize(300, 200);
        alert.getDialogPane().setMinSize(300, 200);
        alert.showAndWait();
    }
}

package it.unibo.view;

import it.unibo.controller.gamecontroller.api.MainController;
import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

/**
 * This class opens a message showing which player's turn it is.
 */
public class EndTurnPopUp {

    /**
     * Constructor for the class.
     * 
     * @param controller the main controller of the game.
     */
    public EndTurnPopUp(MainController controller) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Turno:  " + controller.getTurnController().getTurnManager().getCurrentTurn());
        alert.setContentText("Tocca a:  " + controller.getTurnController().getCurrentPlayer().getName());

        alert.setResizable(false);
        alert.getDialogPane().setMaxSize(300, 200);
        alert.getDialogPane().setMinSize(300, 200);
        alert.showAndWait();

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> alert.close());
        delay.play();
    }

}

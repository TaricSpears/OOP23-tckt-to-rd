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

    private static final double WIDTH = 300;
    private static final double HEIGHT = 200;
    private static final double DURATION = 1.5;

    /**
     * Constructor for the class.
     * 
     * @param controller the main controller of the game.
     */
    public EndTurnPopUp(final MainController controller) {

        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Turn:  " + controller.getTurnController().getTurnManager().getCurrentTurn());
        alert.setContentText("It's " + controller.getTurnController().getCurrentPlayer().getName() + " turn.");

        alert.setResizable(false);
        alert.getDialogPane().setMaxSize(WIDTH, HEIGHT);
        alert.getDialogPane().setMinSize(WIDTH, HEIGHT);
        alert.show();

        final PauseTransition delay = new PauseTransition(Duration.seconds(DURATION));
        delay.setOnFinished(event -> alert.close());
        delay.play();
    }

}

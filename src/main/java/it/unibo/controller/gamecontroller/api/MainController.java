package it.unibo.controller.gamecontroller.api;

import it.unibo.controller.turncontroller.api.TurnController;
import it.unibo.model.card.api.ObjectiveCard;
import it.unibo.model.card.api.TrainCard;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.view.MainView;

/**
 * It models the main controller that allows to comunicate with other
 * controllers
 */
public interface MainController {

    /**
     * Launches the application
     */
    void startView();

    /**
     * Starts the game, calls the {@link GamePrep} creates a new turn manager
     * controller and launches the main view
     */
    void startGame();

    /**
     * @return the actual game controller
     */
    GameController getGameController();

    /**
     * @return the actual turn controller
     */
    TurnController getTurnController();

    /**
     * @return the game instance that contains info about map and players
     */
    GamePrep getGameInstance();

    /**
     * Sets the view ref to the current main view
     * 
     * @param app the main view
     */
    void setMainApp(MainView app);

    /**
     * @return the drawn train card
     */
    TrainCard handleDrawTrainCard();

    /**
     * @return the drawn objective card
     */
    ObjectiveCard handleDrawObjectiveCard();
}

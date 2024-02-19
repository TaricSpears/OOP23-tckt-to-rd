package it.unibo.controller.gamecontroller.api;

import it.unibo.controller.drawcontroller.api.DrawController;
import it.unibo.controller.phasecontroller.api.PhaseController;
import it.unibo.controller.turncontroller.api.TurnController;
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
     * @return the draw controller
     */
    DrawController getDrawController();

    /**
     * @return the phase controller
     */
    PhaseController getPhaseController();

    /**
     * Sets the pahase controller to the new one
     * 
     * @param phaseController
     */
    void setPhaseController(PhaseController phaseController);

}

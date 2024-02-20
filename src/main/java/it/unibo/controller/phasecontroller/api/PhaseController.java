package it.unibo.controller.phasecontroller.api;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.model.phasemanager.api.PhaseManager;

/**
 * Controller of the phases of the single turn.
 */
public interface PhaseController {

    /**
     * @return the phase manager.
     */
    PhaseManager getPhaseManager();

    /**
     * @return the current phase.
     */
    PhaseManager.Phase getCurrentPhase();

    /**
     * Switch the current phase.
     */
    void switchPhase();

    /**
     * Checks if the current player has more than 2 carriages.
     * 
     * @param mainController the main controller of the game.
     * @return if the current phase is the end phase.
     */
    boolean isEndPhase(MainController mainController);

    /**
     * @return if the current phase is the mid phase.
     */
    boolean isMidPhase();

    /**
     * @return if the current phase is the start phase.
     */
    boolean isStartPhase();

}

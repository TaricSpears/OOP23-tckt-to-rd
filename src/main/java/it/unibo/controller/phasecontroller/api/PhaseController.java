package it.unibo.controller.phasecontroller.api;

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
     * @return if the current phase is the end phase.
     */
    boolean isEndPhase();

    /**
     * @return if the current phase is the mid phase.
     */
    boolean isMidPhase();

    /**
     * @return if the current phase is the start phase.
     */
    boolean isStartPhase();

}

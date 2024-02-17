package it.unibo.controller.phasecontroller.api;

import it.unibo.model.phasemanager.api.PhaseManager;

/*
 * Controller of the phases of the single turn.
 */
public interface PhaseController {

    /*
     * Returns the phase manager.
     */
    PhaseManager getPhaseManager();

    /*
     * @return the current phase.
     */
    PhaseManager.Phase getCurrentPhase();
}

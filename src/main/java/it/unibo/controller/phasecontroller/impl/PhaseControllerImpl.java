package it.unibo.controller.phasecontroller.impl;

import it.unibo.controller.phasecontroller.api.PhaseController;
import it.unibo.model.phasemanager.api.PhaseManager;
import it.unibo.model.phasemanager.impl.PhaseManagerImpl;

/*
 * Implementation of {@link PhaseController}.
 * Controller of the phases of the single turn.
 */
public class PhaseControllerImpl implements PhaseController {

    private PhaseManager phaseManager;

    /*
     * Initializes the new phase Controller.
     */
    public PhaseControllerImpl() {
        this.phaseManager = new PhaseManagerImpl();
    }

    /*
     * Returns the phase manager.
     */
    public PhaseManager getPhaseManager() {
        return this.phaseManager;
    }

    /*
     * Returns the string instructions of the current phase.
     */
    @Override
    public String toString() {
        switch (this.phaseManager.getCurrentPhase()) {
            case START:
                return "Draw a new Train Card.";
            case MID:
                return "Choose whether to draw another Train Card, a new Objective Card or claim a route.";
            case END:
                return "Pass the turn to the next playe";
            default:
                throw new IllegalStateException("Unknown phase");
        }
    }
}

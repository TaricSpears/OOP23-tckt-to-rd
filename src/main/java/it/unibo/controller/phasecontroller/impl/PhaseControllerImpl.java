package it.unibo.controller.phasecontroller.impl;

import it.unibo.controller.phasecontroller.api.PhaseController;
import it.unibo.model.phasemanager.api.PhaseManager;
import it.unibo.model.phasemanager.impl.PhaseManagerImpl;

/**
 * Implementation of {@link PhaseController}.
 * Controller of the phases of the single turn.
 */
public class PhaseControllerImpl implements PhaseController {

    private PhaseManager phaseManager;

    /**
     * {@inheritDoc}
     */
    public PhaseControllerImpl() {
        this.phaseManager = new PhaseManagerImpl();
    }

    /**
     * {@inheritDoc}
     */
    public PhaseManager getPhaseManager() {
        return this.phaseManager;
    }

    /**
     * {@inheritDoc}
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

    /*
     * @return the current phase.
     */
    @Override
    public PhaseManager.Phase getCurrentPhase() {
        return this.phaseManager.getCurrentPhase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchPhase() {
        this.phaseManager.switchPhase();
    }
}

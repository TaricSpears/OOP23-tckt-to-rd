package it.unibo.model.phasemanager.impl;

import it.unibo.model.phasemanager.api.PhaseManager;

/**
 * Implementation of {@link PhaseManager}.
 * Manages the phases of the single turn.
 */
public class PhaseManagerImpl implements PhaseManager {

    private Phase currentPhase;

    /**
     * Initializes the phase manager with the start phase.
     */
    public PhaseManagerImpl() {
        this.currentPhase = Phase.START;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Phase getCurrentPhase() {
        return this.currentPhase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchPhase() {
        this.currentPhase = Phase.values()[(this.currentPhase.ordinal() + 1) % Phase.values().length];
    }

}

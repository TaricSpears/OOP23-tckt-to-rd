package it.unibo.model.phasemanager.impl;

import it.unibo.model.phasemanager.api.phaseManager;

/*
 * Implementation of {@link phaseManager}.
 * Manages the phases of the single turn.
 */
public class phaseManagerImpl implements phaseManager {

    private Phase currentPhase;

    /*
     * Initializes the phase manager with the start phase.
     */
    public phaseManagerImpl() {
        this.currentPhase = Phase.START;
    }

    /*
     * Returns the current phase of the turn.
     */
    @Override
    public Phase getCurrentPhase() {
        return this.currentPhase;
    }

    /*
     * Switches the current phase of the turn.
     */
    @Override
    public void switchPhase() {
        this.currentPhase = Phase.values()[(this.currentPhase.ordinal() + 1) % Phase.values().length];
    }

}

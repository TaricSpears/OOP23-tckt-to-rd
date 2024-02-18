package it.unibo.model.phasemanager.api;

/**
 * Manages the phases of the single turn.
 */
public interface PhaseManager {

    /**
     * Enumerates the possible phases of the game.
     */
    enum Phase {
        /**
         * The start phase of the turn.
         * The player has to draw a trainCard
         */
        START,

        /**
         * The phase in which the player can draw a trainCard, an objectiveCard or claim
         * a route.
         */
        MID,

        /**
         * The end phase of the turn.
         * The player has to click the end turn button.
         */
        END

    }

    /**
     * @return the current phase of the turn.
     */
    Phase getCurrentPhase();

    /**
     * Switches the current phase of the turn.
     */
    void switchPhase();

}

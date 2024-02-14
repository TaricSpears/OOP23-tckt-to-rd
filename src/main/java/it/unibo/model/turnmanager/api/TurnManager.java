package it.unibo.model.turnmanager.api;

import java.util.List;

import it.unibo.model.player.api.Player;

public interface TurnManager {

    /**
     * @return the current player
     */
    Player getCurrentPlayer();

    /**
     * @return the list of players in playing order
     */
    List<Player> getPlayers();

    /**
     * @return the current turn
     */
    int getCurrentTurn();

    /**
     * Passes control to the next player
     */
    void switchToNextPlayer();

    /**
     * Restart the turn cycle
     */
    void resetTurns();

}

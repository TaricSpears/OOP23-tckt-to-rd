package it.unibo.model.turnmanager.api;

import java.util.List;

import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;

/**
 * Manages the order of player turns.
 */
public interface TurnManager {

    /**
     * @return the current player.
     */
    PlayerImpl getCurrentPlayer();

    /**
     * @return the list of players in playing order.
     */
    List<Player> getPlayers();

    /**
     * @return the current turn.
     */
    int getCurrentTurn();

    /**
     * Passes control to the next player.
     */
    void switchToNextPlayer();

    /**
     * @return true if the current player is the last of the turn, false otherwise.
     */
    boolean isLastPlayer();

}

package it.unibo.controller.turncontroller.api;

import java.util.List;

import it.unibo.model.player.api.Player;
import it.unibo.model.turnmanager.api.TurnManager;

/**
 * Interface to control the turn of the game.
 */
public interface TurnController {

    /**
     * @return the turn manager.
     */
    TurnManager getTurnManager();

    /**
     * Checks if the current player is the last one in the current turn.
     * 
     * Ends the turn of the current player.
     */
    void endTurn();

    /**
     * @return the list of players in the order of their turns.
     */
    List<Player> getOrderedPlayers();

    /**
     * @return the current player.
     */
    Player getCurrentPlayer();

    /**
     * @return true if the current player was the last one.
     */
    boolean wasLastPlayer();

}

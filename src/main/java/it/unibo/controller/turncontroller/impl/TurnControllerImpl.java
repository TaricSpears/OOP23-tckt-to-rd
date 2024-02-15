package it.unibo.controller.turncontroller.impl;

import java.util.List;

import it.unibo.controller.turncontroller.api.turnController;
import it.unibo.model.player.api.Player;
import it.unibo.model.turnmanager.api.TurnManager;
import it.unibo.model.turnmanager.impl.TurnManagerImpl;

/**
 * Implementation of {@link turnController}
 * Class to control the turn of the game.
 */
public class turnControllerImpl implements turnController {

    private final TurnManager turnManager;

    /*
     * Constructor for the turn controller.
     * 
     * @param players the list of players in the game.
     */
    public turnControllerImpl(List<Player> players) {
        this.turnManager = new TurnManagerImpl(players);
    }

    /*
     * @return the turn manager.
     */
    @Override
    public TurnManager getTurnManager() {
        return this.turnManager;
    }

    /*
     * Ends the turn of the current player.
     */
    @Override
    public void endTurn() {
        this.turnManager.switchToNextPlayer();
    }

    /*
     * @return the list of players in the order of their turns.
     */
    @Override
    public List<Player> getOrderedPlayers() {
        return this.turnManager.getPlayers();
    }

    /*
     * @return the string representation of the current turn.
     */
    @Override
    public String toString() {
        return "Turn: " + this.turnManager.getCurrentTurn() + " Player: "
                + this.turnManager.getCurrentPlayer().getName();
    }

}

package it.unibo.controller.turncontroller.impl;

import java.util.List;

import it.unibo.controller.turncontroller.api.TurnController;
import it.unibo.model.player.api.Player;
import it.unibo.model.turnmanager.api.TurnManager;
import it.unibo.model.turnmanager.impl.TurnManagerImpl;

/**
 * Implementation of {@link TurnController}
 * Class to control the turn of the game.
 */
public class TurnControllerImpl implements TurnController {

    private final TurnManager turnManager;

    /**
     * Constructor for the turn controller.
     * 
     * @param players the list of players in the game.
     */
    public TurnControllerImpl(List<Player> players) {
        this.turnManager = new TurnManagerImpl(players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TurnManager getTurnManager() {
        return this.turnManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() {
        this.turnManager.switchToNextPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getOrderedPlayers() {
        return this.turnManager.getPlayers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Turn: " + this.turnManager.getCurrentTurn() + " Player: "
                + this.turnManager.getCurrentPlayer().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return this.turnManager.getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    public boolean wasLastTurn() {
        return this.turnManager.isLastPlayer();
    }

}

package it.unibo.model.turnmanager.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import it.unibo.model.player.api.Player;
import it.unibo.model.turnmanager.api.TurnManager;

/**
 * Implementation of {@link TurnManager}.
 * 
 * Manages the order of player turns.
 */
public class TurnManagerImpl implements TurnManager {

    private final List<Player> players;

    private Iterator<Player> playersIterator;
    private Player currentPlayer;
    private int currentTurn;

    /**
     * Initializes the player order and creates the iterator.
     * 
     * @param players the list of players.
     */
    public TurnManagerImpl(final List<Player> players) {
        this.players = new ArrayList<>(players);
        Collections.shuffle(this.players);

        this.playersIterator = this.players.iterator();
        this.currentPlayer = this.playersIterator.next();
        this.currentTurn = 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentTurn() {
        return this.currentTurn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToNextPlayer() {
        if (isLastPlayer()) {
            this.currentTurn++;
            resetTurns();
        }

        this.currentPlayer = this.playersIterator.next();
    }

    @Override
    public boolean isLastPlayer() {
        return !this.playersIterator.hasNext();
    }

    private void resetTurns() {
        this.playersIterator = this.players.iterator();
    }

}

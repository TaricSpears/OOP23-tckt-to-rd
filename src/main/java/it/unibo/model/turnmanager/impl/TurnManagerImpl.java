package it.unibo.model.turnmanager.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.turnmanager.api.TurnManager;

/**
 * Implementation of {@link TurnManager}.
 * 
 * Manages the order of player turns.
 */
public class TurnManagerImpl implements TurnManager {

    private final List<PlayerImpl> players;

    private Iterator<PlayerImpl> playersIterator;
    private PlayerImpl currentPlayer;
    private int currentTurn;

    /**
     * Initializes the player order and creates the iterator.
     * 
     * @param players the list of players.
     */
    public TurnManagerImpl(final List<PlayerImpl> players) {
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
    public PlayerImpl getCurrentPlayer() {
        return this.currentPlayer.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLastPlayer() {
        return !this.playersIterator.hasNext();
    }

    /**
     * Resets the player iterator to the first player.
     */
    private void resetTurns() {
        this.playersIterator = this.players.iterator();
    }

}

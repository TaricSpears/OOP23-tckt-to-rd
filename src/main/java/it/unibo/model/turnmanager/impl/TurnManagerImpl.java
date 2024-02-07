package it.unibo.model.turnmanager.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import it.unibo.model.player.api.Player;
import it.unibo.model.turnmanager.api.TurnManager;

public class TurnManagerImpl implements TurnManager {

    private final List<Player> players;

    private Iterator<Player> playersIterator;
    private Player currentPlayer;
    private int currentTurn;

    public TurnManagerImpl(final List<Player> players) {
        this.players = players;
        Collections.shuffle(this.players);

        this.playersIterator = this.players.iterator();
        this.currentPlayer = this.playersIterator.next();
        this.currentTurn = 1;
    }

    /**
     * @return the current player
     */
    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * @return the list of players in playing order
     */
    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * @return the current turn
     */
    @Override
    public int getCurrentTurn() {
        return this.currentTurn;
    }

    /**
     * Passes control to the next player
     */
    @Override
    public void switchToNextPlayer() {
        if (!this.playersIterator.hasNext())
            resetTurns();

        this.currentPlayer = this.playersIterator.next();
    }

    /**
     * Restart the turn cycle
     */
    @Override
    public void resetTurns() {
        this.playersIterator = this.players.iterator();
    }

}
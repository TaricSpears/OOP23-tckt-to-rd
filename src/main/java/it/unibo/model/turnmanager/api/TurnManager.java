package it.unibo.model.turnmanager.api;

import it.unibo.model.player.api.Player;

public interface TurnManager {

    Player getCurrentPlayer();

    int getCurrentTurn();

    void switchToNextPlayer();

    void resetTurns();

}

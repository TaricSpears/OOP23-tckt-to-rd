package it.unibo.model.scorecalculator.api;

import java.util.List;

import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.commons.Pair;

/**
 * Calculates the score of the players.
 */
public interface ScoreCalculator {

    /**
     * @param players the list of all the players of the match.
     * @return a list of pairs of player and his final score, ordered by score
     *         ascending.
     */
    List<Pair<String, Double>> getScoreBoard(List<PlayerImpl> players);
}

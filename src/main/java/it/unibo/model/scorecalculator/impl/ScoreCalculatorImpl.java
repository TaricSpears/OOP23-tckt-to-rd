package it.unibo.model.scorecalculator.impl;

import java.util.List;

import it.unibo.model.player.api.Player;
import it.unibo.model.scorecalculator.api.ScoreCalculator;
import it.unibo.commons.Pair;

/**
 * Implementation of {@link ScoreCalculator}.
 * 
 * Calculates the score of the players.
 */
public class ScoreCalculatorImpl implements ScoreCalculator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<String, Double>> getScoreBoard(final List<Player> players) {
        return players.stream()
                .map(p -> new Pair<String, Double>(p.getName(), p.getRouteScore() + p.getObjectiveScore()))
                .sorted((x, y) -> Double.compare(y.second(), x.second()))
                .toList();
    }
}

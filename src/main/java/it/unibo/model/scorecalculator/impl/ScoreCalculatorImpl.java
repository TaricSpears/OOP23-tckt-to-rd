package it.unibo.model.scorecalculator.impl;

import java.util.List;

import it.unibo.model.player.api.Player;
import it.unibo.model.scorecalculator.api.ScoreCalculator;
import it.unibo.commons.Pair;

public class ScoreCalculatorImpl implements ScoreCalculator {

    /**
     * @param players the set of all the players of the match
     * @return a list of pairs of player and his final score, ordered by score
     *         ascending
     */
    @Override
    public List<Pair<String, Integer>> getScoreBoard(final List<Player> players) {
        return players.stream()
                .map(p -> new Pair<String, Integer>(p.getName(), p.getObjectiveScore() + p.getRouteScore()))
                .sorted((x, y) -> y.second() - x.second())
                .toList();
    }
}

package it.unibo.model.scorecalculator.impl;

import java.util.List;
import java.util.Set;

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
    public List<Pair<Player, Integer>> getScoreBoard(final Set<Player> players) {
        return players.stream()
                .map(p -> new Pair<Player, Integer>(p, p.getObjectiveScore() + p.getRouteScore()))
                .sorted((x, y) -> x.second() - y.second())
                .toList();
    }
}

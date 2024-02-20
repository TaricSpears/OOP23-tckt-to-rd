package it.unibo.scorecalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import it.unibo.commons.Pair;
import it.unibo.commons.TestDataPreparation;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.route.api.Route;
import it.unibo.model.scorecalculator.api.ScoreCalculator;
import it.unibo.model.scorecalculator.impl.ScoreCalculatorImpl;

/**
 * This class is used for testing the ScoreCalculator.
 */
class TestScoreCalculator {
    @Test
    void testGetScoreBoard() {
        final GamePrep gamePrep = TestDataPreparation.gamePrep();
        final ScoreCalculator scoreCalculator = new ScoreCalculatorImpl();
        final List<PlayerImpl> players = gamePrep.getPlayers();

        final Set<Route> routes = gamePrep.getGraph().edgeSet();

        final Iterator<Route> routesIterator = routes.iterator();
        for (int i = 0; i < routes.size(); i++) {
            players.get(i).addRoute(routesIterator.next());
        }

        final List<Pair<String, Double>> score = scoreCalculator.getScoreBoard(gamePrep.getPlayers());

        assertEquals(players.get(2).getName(), score.get(0).first());
        assertEquals(players.get(2).getRouteScore() + players.get(2).getObjectiveScore(), score.get(0).second());

        assertEquals(players.get(0).getName(), score.get(1).first());
        assertEquals(players.get(1).getName(), score.get(2).first());
    }
}

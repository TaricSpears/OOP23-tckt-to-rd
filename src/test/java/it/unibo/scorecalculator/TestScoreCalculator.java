package it.unibo.scorecalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.city.impl.CityImpl;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;
import it.unibo.model.scorecalculator.api.ScoreCalculator;
import it.unibo.model.scorecalculator.impl.ScoreCalculatorImpl;

import java.awt.Color;

/**
 * This class is used for testing the ScoreCalculator
 */
class TestScoreCalculator {
    final List<Pair<String, Color>> playerData = List.of(new Pair<String, Color>("Player1", Color.RED),
            new Pair<String, Color>("Player2", Color.BLUE), new Pair<String, Color>("Player3", Color.GREEN),
            new Pair<String, Color>("Player4", Color.YELLOW), new Pair<String, Color>("Player5", Color.BLACK),
            new Pair<String, Color>("Player6", Color.ORANGE));

    final City city1 = new CityImpl("Rome");
    final City city2 = new CityImpl("Milan");
    final City city3 = new CityImpl("Naples");

    final Route route1 = new RouteImpl(new EdgeData(city1, city2, 5), Color.RED, 0, null);
    final Route route2 = new RouteImpl(new EdgeData(city1, city3, 3), Color.BLACK, 1, null);
    final Route route3 = new RouteImpl(new EdgeData(city2, city3, 7), Color.GREEN, 2, null);

    final List<Route> routeData = List.of(route1, route2, route3);

    final GamePrep gamePrep = new GamePrep();

    TestScoreCalculator() {
        gamePrep.prepGame(playerData, routeData);
    }

    @Test
    void testGetScoreBoard() {
        final ScoreCalculator scoreCalculator = new ScoreCalculatorImpl();
        final List<Player> players = gamePrep.getPlayers();

        players.get(0).addRoute(route1);
        players.get(1).addRoute(route2);
        players.get(2).addRoute(route3);

        final List<Pair<String, Double>> score = scoreCalculator.getScoreBoard(gamePrep.getPlayers());

        assertEquals(players.get(2).getName(), score.get(0).first());
        assertEquals(players.get(2).getRouteScore() + players.get(2).getObjectiveScore(), score.get(0).second());

        assertEquals(players.get(0).getName(), score.get(1).first());
        assertEquals(players.get(1).getName(), score.get(2).first());
    }
}

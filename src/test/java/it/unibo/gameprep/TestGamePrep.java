package it.unibo.gameprep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.city.impl.CityImpl;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;

import java.awt.Color;

/**
 * This class is used for testing the GamePrep class.
 */
class TestGamePrep {

    private final static int CARRIAGE_DEFAULT_NUMBER = 45;

    final List<Pair<String, Color>> playerData = List.of(new Pair<String, Color>("Player1", Color.RED),
            new Pair<String, Color>("Player2", Color.BLUE), new Pair<String, Color>("Player3", Color.GREEN),
            new Pair<String, Color>("Player4", Color.YELLOW), new Pair<String, Color>("Player5", Color.BLACK),
            new Pair<String, Color>("Player6", Color.ORANGE));

    final City city1 = new CityImpl("Rome");
    final City city2 = new CityImpl("Milan");
    final City city3 = new CityImpl("Naples");

    final Set<Route> routeData = Set.of(
            new RouteImpl(new EdgeData(city1, city2, 5), Color.RED, 0, null),
            new RouteImpl(new EdgeData(city1, city3, 3), Color.BLACK, 1, null),
            new RouteImpl(new EdgeData(city2, city3, 7), Color.GREEN, 2, null));

    final GamePrep gamePrep = new GamePrep();

    @BeforeEach
    void setUp() {
        gamePrep.prepGame(playerData, routeData);
    }

    @Test
    void testPrepPlayers() {

        final List<Player> players = gamePrep.getPlayers();

        assertEquals(players.size(), 6);
        assertEquals(players.get(0).getName(), "Player1");
        assertEquals(players.get(0).getColor(), Color.RED);
        assertEquals(players.get(0).getCarriageNum(), CARRIAGE_DEFAULT_NUMBER);
    }

    @Test
    void testPrepGraph() {

        final SimpleDirectedWeightedGraph<City, Route> graph = gamePrep.getGraph();
        assertEquals(graph.vertexSet().size(), 3);
        assertEquals(graph.edgeSet().size(), 3);
        assertEquals(graph.getEdgeWeight(graph.getEdge(city1, city2)), 5);
        assertEquals(graph.getEdgeWeight(graph.getEdge(city1, city3)), 3);
        assertEquals(graph.getEdgeWeight(graph.getEdge(city2, city3)), 7);

    }
}

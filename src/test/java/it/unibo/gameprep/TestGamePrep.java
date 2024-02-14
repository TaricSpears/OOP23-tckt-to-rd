package it.unibo.gameprep;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import java.awt.Color;

/**
 * This class is used for testing the GamePrep class.
 */
class TestGamePrep {

    private final static int CARRIAGE_DEFAULT_NUMBER = 45;

    final Set<Pair<String, Color>> playerData = Set.of(new Pair<String, Color>("Player1", Color.RED),
            new Pair<String, Color>("Player2", Color.BLUE), new Pair<String, Color>("Player3", Color.GREEN),
            new Pair<String, Color>("Player4", Color.YELLOW), new Pair<String, Color>("Player5", Color.BLACK),
            new Pair<String, Color>("Player6", Color.ORANGE));

    final City city1 = new CityImpl("Rome");
    final City city2 = new CityImpl("Milan");
    final City city3 = new CityImpl("Naples");

    final Set<EdgeData> routeData = Set.of(
            new EdgeData(city1, city2, 5),
            new EdgeData(city1, city3, 3),
            new EdgeData(city2, city3, 7));

    final GamePrep gamePrep = new GamePrep();

    @BeforeEach
    void setUp() {
        gamePrep.prepGame(playerData, routeData);
    }

    @Test
    void testPrepPlayers() {

        final List<Player> players = gamePrep.getPlayers();
        players.sort((x, y) -> x.getName().compareTo(y.getName()));

        assertEquals(players.size(), 6);
        assertEquals(players.get(0).getName(), "Player1");
        assertEquals(players.get(0).getColor(), Color.RED);
        assertEquals(players.get(0).getCarriageNum(), CARRIAGE_DEFAULT_NUMBER);
    }

    @Test
    void testPrepGraph() {

        final SimpleDirectedWeightedGraph<City, Route> graph = gamePrep.getGraph();

        assertNotNull(city1.getRoutes());
        assertNotNull(city2.getRoutes());
        assertNotNull(city3.getRoutes());
        assertEquals(graph.vertexSet().size(), 3);
        assertEquals(graph.edgeSet().size(), 3);
        assertEquals(graph.getEdgeWeight(graph.getEdge(city1, city2)), 5);
        assertEquals(graph.getEdgeWeight(graph.getEdge(city1, city3)), 3);
        assertEquals(graph.getEdgeWeight(graph.getEdge(city2, city3)), 7);

    }
}

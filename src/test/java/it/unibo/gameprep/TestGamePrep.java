package it.unibo.gameprep;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.jgrapht.graph.WeightedPseudograph;
import org.junit.jupiter.api.Test;

import it.unibo.commons.TestDataPreparation;
import it.unibo.model.city.api.City;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

import java.awt.Color;

/**
 * This class is used for testing the GamePrep class.
 */
class TestGamePrep {

    private static final int CARRIAGE_DEFAULT_NUMBER = 45;

    @Test
    void testPrepPlayers() {
        final GamePrep gamePrep = TestDataPreparation.testPrep();
        final List<Player> players = gamePrep.getPlayers();

        assertEquals(players.size(), 6);
        assertEquals(players.get(0).getName(), "Player1");
        assertEquals(players.get(0).getColor(), Color.RED);
        assertEquals(players.get(0).getCarriageNum(), CARRIAGE_DEFAULT_NUMBER);
    }

    @Test
    void testPrepGraph() {
        final GamePrep gamePrep = TestDataPreparation.testPrep();
        final WeightedPseudograph<City, Route> graph = gamePrep.getGraph();

        assertEquals(graph.vertexSet().size(), 3);
        assertEquals(graph.edgeSet().size(), 3);
    }
}

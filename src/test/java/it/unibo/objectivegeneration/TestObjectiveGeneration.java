package it.unibo.objectivegeneration;

import org.junit.jupiter.api.Test;

import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.city.impl.CityImpl;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.model.objectivegeneration.api.ObjectiveGenerator;
import it.unibo.model.objectivegeneration.impl.ObjectiveGeneratorImpl;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Color;

import java.util.List;

class TestObjectiveGeneration {

    final private List<Pair<String, Color>> playerData = List.of(new Pair<String, Color>("Player1", Color.RED),
            new Pair<String, Color>("Player2", Color.BLUE), new Pair<String, Color>("Player3", Color.GREEN),
            new Pair<String, Color>("Player4", Color.YELLOW), new Pair<String, Color>("Player5", Color.BLACK),
            new Pair<String, Color>("Player6", Color.ORANGE));

    final private City city1 = new CityImpl("Rome");
    final private City city2 = new CityImpl("Milan");
    final private City city3 = new CityImpl("Naples");

    final private Route route1 = new RouteImpl(new EdgeData(city1, city2, 5), Color.RED, 0, null);
    final private Route route2 = new RouteImpl(new EdgeData(city1, city3, 3), Color.BLACK, 1, null);
    final private Route route3 = new RouteImpl(new EdgeData(city2, city3, 7), Color.GREEN, 2, null);

    final private List<Route> routeData = List.of(route1, route2, route3);

    final private GamePrep gamePrep = new GamePrep();

    TestObjectiveGeneration() {
        gamePrep.prepGame(playerData, routeData);
    }

    @Test
    void testObjectiveGeneration() {
        final ObjectiveGenerator objectiveGenerator = new ObjectiveGeneratorImpl(gamePrep.getGraph());

        final Pair<City, City> obj1 = objectiveGenerator.generateObjective();
        assertNotEquals(obj1.first(), obj1.second());
        assertTrue(objectiveGenerator.calculateScore(obj1) > 5.0);

        final Pair<City, City> obj2 = objectiveGenerator.generateObjective();
        assertNotEquals(obj2.first(), obj2.second());
        assertTrue(objectiveGenerator.calculateScore(obj2) > 5.0);
    }
}

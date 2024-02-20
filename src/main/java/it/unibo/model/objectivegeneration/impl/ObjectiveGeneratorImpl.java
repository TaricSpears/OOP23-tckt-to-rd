package it.unibo.model.objectivegeneration.impl;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.commons.GraphCopier;
import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.objectivegeneration.api.ObjectiveGenerator;
import it.unibo.model.route.api.Route;

/**
 * Implementation of {@link ObjectiveGenerator}.
 *
 * Generates the objective cards.
 */
public class ObjectiveGeneratorImpl implements ObjectiveGenerator {

    private static final double MIN_DISTANCE = 5.0;
    private final WeightedPseudograph<City, Route> graph;
    private final BellmanFordShortestPath<City, Route> bellmanFordAlg;

    /**
     * Constructor of the objective generator.
     * 
     * @param graph the graph of cities and routes.
     */
    public ObjectiveGeneratorImpl(final WeightedPseudograph<City, Route> graph) {
        this.graph = GraphCopier.copyGraph(graph);
        this.bellmanFordAlg = new BellmanFordShortestPath<>(this.graph);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<City, City> generateObjective() {
        final Random random = new Random();
        final Set<City> vertexSet = graph.vertexSet();
        final List<City> cities = vertexSet.stream().collect(Collectors.toList());
        final City city1 = getRandomCity(cities, random);
        City city2 = getRandomCity(cities, random);
        while (city1.equals(city2) || this.bellmanFordAlg.getPathWeight(city1, city2) < MIN_DISTANCE) {
            city2 = getRandomCity(cities, random);
        }
        return new Pair<>(city1, city2);
    }

    /**
     * Returns a random city from the list.
     * 
     * @param cities the list of cities.
     * @param random the random object.
     * @return a random city from the list.
     */
    private City getRandomCity(final List<City> cities, final Random random) {
        return cities.get(random.nextInt(cities.size()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateScore(final Pair<City, City> objective) {
        return this.bellmanFordAlg.getPathWeight(objective.first(), objective.second());
    }
}

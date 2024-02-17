package it.unibo.model.objectivegeneration.impl;

import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.objectivegeneration.api.ObjectiveGenerator;
import it.unibo.model.route.api.Route;

public class ObjectiveGeneratorImpl implements ObjectiveGenerator {

    final private WeightedPseudograph<City, Route> graph;

    public ObjectiveGeneratorImpl(final WeightedPseudograph<City, Route> graph) {
        this.graph = graph;
    }

    @Override
    public Pair<City, City> generateObjective() {
        final Random random = new Random();
        final Set<City> vertexSet = graph.vertexSet();
        List<City> cities = vertexSet.stream().collect(Collectors.toList());
        City city1 = getRandomCity(cities, random);
        City city2 = getRandomCity(cities, random);
        return new Pair<>(city1, city2);
    }

    private City getRandomCity(List<City> cities, Random random) {
        return cities.get(random.nextInt(cities.size()));
    }

    @Override
    public double calculateScore(Pair<City, City> objective) {
        return new BellmanFordShortestPath<>(this.graph).getPathWeight(objective.first(), objective.second());
    }
}

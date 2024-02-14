package it.unibo.model.gameprep.impl;

import it.unibo.model.city.api.City;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;
import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class GamePrep {

    private final static int CARRIAGE_DEFAULT_NUMBER = 45;

    public List<Player> prepPlayers(final Set<Pair<String, Color>> playerData) {
        final List<Player> players = new LinkedList<>();
        for (final var player : playerData) {
            players.add(new PlayerImpl(player.first(), player.second(), CARRIAGE_DEFAULT_NUMBER));
        }
        return players;
    }

    public SimpleDirectedWeightedGraph<City, Route> prepGraph(Set<EdgeData> routeData) {

        final SimpleDirectedWeightedGraph<City, Route> graph = new SimpleDirectedWeightedGraph<>(
                RouteImpl.class);
        for (final EdgeData edge : routeData) {
            graph.addVertex(edge.city1());
            graph.addVertex(edge.city2());
            var newRoute = new RouteImpl(edge);
            graph.addEdge(edge.city1(), edge.city2(), newRoute);
            edge.city1().addOutGoingRoutes(newRoute);
            edge.city2().addOutGoingRoutes(newRoute);
            graph.setEdgeWeight(edge.city1(), edge.city2(), edge.weight());
        }

        return graph;
    }

}
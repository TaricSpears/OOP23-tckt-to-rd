package it.unibo.model.gameprep.impl;

import it.unibo.model.board.impl.BoardImpl;
import it.unibo.model.city.api.City;
import it.unibo.model.player.api.Player;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;
import it.unibo.commons.Pair;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class GamePrep {

    private final static int CARRIAGE_DEFAULT_NUMBER = 45;

    private BoardImpl board = null;

    private List<Player> prepPlayers(final List<Pair<String, Color>> playerData) {
        final List<Player> players = new LinkedList<>();
        for (final var player : playerData) {
            players.add(new PlayerImpl(player.first(), player.second(), CARRIAGE_DEFAULT_NUMBER));
        }
        return players;
    }

    private SimpleDirectedWeightedGraph<City, Route> prepGraph(Set<Route> routeData) {

        final SimpleDirectedWeightedGraph<City, Route> graph = new SimpleDirectedWeightedGraph<>(
                RouteImpl.class);
        for (final Route route : routeData) {
            final City city1 = route.getConnectedCity().first();
            final City city2 = route.getConnectedCity().second();

            graph.addVertex(city1);
            graph.addVertex(city2);
            graph.addEdge(city1, city2, route);
            graph.setEdgeWeight(city1, city2, route.getScore());
        }

        return graph;
    }

    public void prepGame(final List<Pair<String, Color>> playerData, final Set<Route> routeData) {
        board = new BoardImpl(prepPlayers(playerData), prepGraph(routeData));
    }

    public List<Player> getPlayers() {
        return board.getPlayers();
    }

    public SimpleDirectedWeightedGraph<City, Route> getGraph() {
        return board.getGraph();
    }
}
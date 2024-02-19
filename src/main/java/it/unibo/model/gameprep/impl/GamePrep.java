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

import org.jgrapht.graph.WeightedPseudograph;

/**
 * Prepares the game.
 */
public class GamePrep {

    private static final int CARRIAGE_DEFAULT_NUMBER = 45;
    private BoardImpl board;

    /**
     * Prepares the players.
     * 
     * @param playerData informations about the players.
     * @return the list of players.
     */
    private List<Player> prepPlayers(final List<Pair<String, Color>> playerData) {
        final List<Player> players = new LinkedList<>();
        for (final var player : playerData) {
            players.add(new PlayerImpl(player.first(), player.second(), CARRIAGE_DEFAULT_NUMBER));
        }
        return players;
    }

    /**
     * Prepares the graph of cities and routes.
     * 
     * @param routeData informations about the routes.
     * @return the graph.
     */
    private WeightedPseudograph<City, Route> prepGraph(final List<Route> routeData) {

        final WeightedPseudograph<City, Route> graph = new WeightedPseudograph<>(
                RouteImpl.class);
        for (final Route route : routeData) {
            final City city1 = route.getConnectedCity().first();
            final City city2 = route.getConnectedCity().second();

            graph.addVertex(city1);
            graph.addVertex(city2);
            graph.addEdge(city1, city2, route);
            graph.setEdgeWeight(route, route.getScore());

        }

        return graph;
    }

    /**
     * Prepares the game.
     * 
     * @param playerData informations about the players.
     * @param routeData  informations about the routes.
     */
    public void prepGame(final List<Pair<String, Color>> playerData, final List<Route> routeData) {
        board = new BoardImpl(prepPlayers(playerData), prepGraph(routeData), routeData);
    }

    /**
     * @return the players.
     */
    public List<Player> getPlayers() {
        return board.getPlayers();
    }

    /**
     * @return the graph.
     */
    public WeightedPseudograph<City, Route> getGraph() {
        return board.getGraph();
    }

    /**
     * @return the list of routes.
     */
    public List<Route> getRoutes() {
        return this.board.getRouteData();
    }
}

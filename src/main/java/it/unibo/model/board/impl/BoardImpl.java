package it.unibo.model.board.impl;

import java.util.List;
import java.util.Collections;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.model.board.api.Board;
import it.unibo.model.city.api.City;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

/**
 * Implementation of {@link Board}.
 * 
 * Regulates the functioning of the board of the game.
 */
public class BoardImpl {

    final private List<Player> players;
    private WeightedPseudograph<City, Route> graph;
    final private List<Route> routeData;

    /**
     * Constructor for the board.
     * 
     * @param players   the list of players.
     * @param graph     the graph of cities and routes.
     * @param routeData the informations about the routes.
     * 
     */
    public BoardImpl(List<Player> players, WeightedPseudograph<City, Route> graph, List<Route> routeData) {
        this.players = players;
        this.graph = graph;
        this.routeData = routeData;
    }

    /**
     * {@inheritDoc}
     */
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    /**
     * {@inheritDoc}
     */
    public WeightedPseudograph<City, Route> getGraph() {
        return graph;
    }

    /**
     * {@inheritDoc}
     */
    public List<Route> getRouteData() {
        return Collections.unmodifiableList(this.routeData);
    }
}

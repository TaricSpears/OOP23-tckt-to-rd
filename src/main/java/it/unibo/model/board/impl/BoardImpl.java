package it.unibo.model.board.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.commons.GraphCopier;
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

    private final List<Player> players;
    private final WeightedPseudograph<City, Route> graph;
    private final List<Route> routeData;

    /**
     * Constructor for the board.
     * 
     * @param players   the list of players.
     * @param graph     the graph of cities and routes.
     * @param routeData the informations about the routes.
     * 
     */
    public BoardImpl(final List<Player> players, final WeightedPseudograph<City, Route> graph,
            final List<Route> routeData) {
        this.players = new ArrayList<>(players);
        this.graph = GraphCopier.copyGraph(graph);
        this.routeData = new ArrayList<>(routeData);
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
        return this.graph;
    }

    /**
     * {@inheritDoc}
     */
    public List<Route> getRouteData() {
        return Collections.unmodifiableList(this.routeData);
    }
}

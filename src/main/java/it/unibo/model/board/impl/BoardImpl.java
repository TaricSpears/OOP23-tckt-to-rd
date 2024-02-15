package it.unibo.model.board.impl;

import java.util.List;
import java.util.Collections;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.model.city.api.City;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

public class BoardImpl {

    final private List<Player> players;
    private WeightedPseudograph<City, Route> graph;

    public BoardImpl(List<Player> players, WeightedPseudograph<City, Route> graph) {
        this.players = players;
        this.graph = graph;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public WeightedPseudograph<City, Route> getGraph() {
        return graph;
    }
}

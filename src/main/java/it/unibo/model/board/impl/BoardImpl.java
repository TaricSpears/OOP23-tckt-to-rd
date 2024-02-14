package it.unibo.model.board.impl;

import java.util.List;
import java.util.Collections;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.unibo.model.city.api.City;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

public class BoardImpl {

    final private List<Player> players;
    private SimpleDirectedWeightedGraph<City, Route> graph;

    public BoardImpl(List<Player> players, SimpleDirectedWeightedGraph<City, Route> graph) {
        this.players = players;
        this.graph = graph;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public SimpleDirectedWeightedGraph<City, Route> getGraph() {
        return graph;
    }
}

package it.unibo.model.board.impl;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.gameprep.impl.GamePrep;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

public class BoardImpl {

    final private List<Player> players = new LinkedList<>();
    final SimpleDirectedWeightedGraph<City, Route> graph;

    public BoardImpl(Set<Pair<String, Color>> playerData, Set<EdgeData> routeData) {

        final GamePrep game = new GamePrep();
        players.addAll(game.prepPlayers(playerData));
        graph = game.prepGraph(routeData);
    }
}

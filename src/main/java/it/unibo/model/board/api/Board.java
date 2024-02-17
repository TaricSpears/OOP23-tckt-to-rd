package it.unibo.model.board.api;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.model.city.api.City;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

import java.util.List;

public interface Board {
    List<Player> getPlayers();

    WeightedPseudograph<City, Route> getGraph();
}

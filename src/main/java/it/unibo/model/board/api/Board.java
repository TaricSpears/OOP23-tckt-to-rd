package it.unibo.model.board.api;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.model.city.api.City;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

import java.util.List;

/**
 * Regulates the functioning of the board of the game.
 */
public interface Board {

    /**
     * @return the list of players.
     */
    List<Player> getPlayers();

    /**
     * @return the graph of cities and routes.
     */
    WeightedPseudograph<City, Route> getGraph();

    /**
     * @return the informations about the routes.
     */
    List<Route> getRouteData();
}

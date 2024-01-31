package it.unibo.model.gameprep.impl;

import it.unibo.model.city.api.City;
import it.unibo.model.player.impl.PlayerImpl;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;
import it.unibo.commons.Pair;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.readercontroller.api.ReaderController;

import java.awt.Color;
import java.util.Set;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;

public class GamePrep {

    private final static int CARRIAGE_DEFAULT_NUMBER = 45;

    private void prepPlayers(final MainController mainController, final Set<Pair<String, Color>> playerData) {
        for (final var player : playerData) {
            mainController.addNewPlayer(new PlayerImpl(player.first(), player.second(), CARRIAGE_DEFAULT_NUMBER));
        }
    }

    private void prepGraph(final MainController mainController) {
        final ReaderController readerController = mainController.getReaderController();
        final DefaultDirectedWeightedGraph<City, Route> graph = new DefaultDirectedWeightedGraph<>(
                RouteImpl.class);
    }

    public void prep(final MainController mainController, final Set<Pair<String, Color>> playerData) {
        prepPlayers(mainController, playerData);
        prepGraph(mainController);
    }
}

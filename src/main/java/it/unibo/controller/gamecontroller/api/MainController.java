package it.unibo.controller.gamecontroller.api;

import java.util.List;

import it.unibo.commons.Pair;
import it.unibo.model.card.impl.ObjectiveCardImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

public interface MainController {

    void endTurn();

    void disableRoute(int idRoute);

    void sendMessage(String message);

    void handleClaimRoute(Player player, Route route);

    void handleObjectiveCompleted(Player player, ObjectiveCardImpl objective);

    void handleDrawCard();

    List<Pair<String, Integer>> getScore();
}

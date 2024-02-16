package it.unibo.controller.gamecontroller.impl;

import java.util.List;

import it.unibo.commons.Pair;
import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.controller.gamecontroller.api.StartController;
import it.unibo.model.card.impl.ObjectiveCardImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;
import it.unibo.model.scorecalculator.api.ScoreCalculator;
import it.unibo.model.scorecalculator.impl.ScoreCalculatorImpl;

public class MainControllerImpl implements MainController {

    final private StartController startController;

    public MainControllerImpl(final StartController startController) {
        this.startController = startController;
    }

    @Override
    public void endTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }

    @Override
    public void disableRoute(int idRoute) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disableRoute'");
    }

    @Override
    public void sendMessage(String message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

    /**
     * @param player the player that is filling the route
     * @param route  the route to be handled
     * @return void
     */
    @Override
    public void handleClaimRoute(final Player player, final Route route) {
        // fillo la route la setto a filled ecc.
        player.setRouteScore(route.getScore());
    }

    /**
     * @param player    the player that completed the objective
     * @param objective the objective been completed by the player
     * @return void
     */
    @Override
    public void handleObjectiveCompleted(final Player player, final ObjectiveCardImpl objective) {
        // imposto l'obiettivo a completato e poi aggiungo i punti
        player.setObjectiveScore(objective.getScore());
    }

    @Override
    public void handleDrawCard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleDrawCard'");
    }

    @Override
    public List<Pair<String, Integer>> getScore() {
        final ScoreCalculator scoreCalculator = new ScoreCalculatorImpl();
        return scoreCalculator.getScoreBoard(startController.getGameInstance().getPlayers());
    }
}

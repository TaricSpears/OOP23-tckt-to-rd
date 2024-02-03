package it.unibo.controller.gamecontroller.impl;

import it.unibo.controller.gamecontroller.api.MainController;
import it.unibo.model.card.impl.ObjectiveCardImpl;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

public class MainControllerImpl implements MainController {

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
}

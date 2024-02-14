package it.unibo.controller.fillroutecontroller.impl;

import it.unibo.controller.fillroutecontroller.api.FillRoute;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

//this class is used to fill the routes with the color of the player

public class FillRouteImpl implements FillRoute {

    // this function returns true if the player has enough train cards with the
    // color of the chosen route

    @Override
    public boolean isRouteValid(Player player, Route route) {
        final long totCards = player.getTrainCards().stream().filter(card -> card.getColor().equals(route.getColor()))
                .count();

        if (route.isCompleted() || totCards < route.getLength()) {
            return false;
        } else if (totCards >= route.getLength() && !route.isCompleted()) {
            return true;
        }
    }
    // this function opens a pop up if the player can fill a route with both color
    // or joily train cards and let the player decide

    @Override
    public void openPopUp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'openPopUp'");
    }

    // this function sets the color of the button of the chosen route with the
    // player color
    @Override
    public void setColor(Player player, Route route) {

    }

}

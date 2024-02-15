package it.unibo.controller.fillroutecontroller.api;

import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

public interface FillRoute {

    void clickRoute(Player player, Route route);

    /*
     * this function returns true if the player has enough train cards with the
     * color of the chosen route
     */
    boolean isRouteValid(Player player, Route route);

    /*
     * this function sets the color of the button of the chosen route with the
     * player color
     */
    void setColor(Player player, Route route);
}

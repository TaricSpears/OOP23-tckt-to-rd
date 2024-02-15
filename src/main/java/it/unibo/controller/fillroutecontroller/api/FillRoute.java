package it.unibo.controller.fillroutecontroller.api;

import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;
import java.awt.Color;

public interface FillRoute {
    void removeCards(Player player, Color color, int quantity);
    boolean isColorEnough(Player player, Color color, Route route);

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

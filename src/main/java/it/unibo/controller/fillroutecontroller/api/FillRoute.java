package it.unibo.controller.fillroutecontroller.api;

import javafx.collections.ObservableList;

import java.awt.Color;

/**
 * This class is used to fill the routes with the color of the player.
 * It provides methods to check if a route can be filled by a player, open a
 * pop-up for player's decision,
 * set the color of a route with the player's color, and handle the click event
 * on a route.
 */
public interface FillRoute {
    /**
     * @return true if the cards of the player are enough to fill the route
     */
    boolean isColorEnough(Color color);

    /**
     * @return true if the action of filling the route is successful
     */
    boolean clickRoute();

    /**
     * @return if a certain route can be filled by the player
     */
    boolean isRouteValid();

    /**
     * @return the list of the Colors that can fill a GRAY route
     */
    ObservableList<String> getAvailableRoutes(FillRoute fillRoute);

}

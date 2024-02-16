package it.unibo.controller.fillroutecontroller.api;

import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.awt.Color;
import java.util.Set;

public interface FillRoute {
    void removeCards(Color color, int quantity);

    boolean isColorEnough(Color color);

    void clickRoute();

    /*
     * this function returns true if the player has enough train cards with the
     * color of the chosen route
     */
    boolean isRouteValid();

    /*
     * this function sets the color of the button of the chosen route with the
     * player color
     */
    void setColor();

    ObservableList<String> getAvailableRoutes(FillRoute fillRoute);

}

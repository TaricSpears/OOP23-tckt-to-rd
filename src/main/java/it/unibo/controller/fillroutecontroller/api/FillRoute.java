package it.unibo.controller.fillroutecontroller.api;

import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.awt.Color;
import java.util.Set;

public interface FillRoute {

    boolean isColorEnough(Color color);

    boolean clickRoute();

    /*
     * this function returns true if the player has enough train cards with the
     * color of the chosen route
     */
    boolean isRouteValid();

    ObservableList<Color> getAvailableRoutes(FillRoute fillRoute);

}

package it.unibo.model.route.api;

import java.util.Set;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.carriage.impl.Carriage;

import java.awt.Color;

/**
 * Represents a route in the game.
 */
public interface Route {

    /**
     * @return the two cities connected by the route.
     */
    Pair<City, City> getConnectedCity();

    /**
     * @return true if the route is completed, false otherwise.
     */
    boolean isCompleted();

    /**
     * sets the route as completed.
     */
    void setFilled();

    /**
     * @return the score of the route.
     */
    int getScore();

    /**
     * @return the color of the route.
     */
    Color getColor();

    /**
     * @return the id of the route.
     */
    int getId();

    /**
     * @return the rail units of the route.
     */
    Set<Carriage> getRailUnits();

    /**
     * @return the string representation of the route.
     */
    @Override
    String toString();

}

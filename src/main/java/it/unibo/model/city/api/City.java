package it.unibo.model.city.api;

import it.unibo.commons.Pair;

/**
 * Represents a city in the game.
 */
public interface City {

    /**
     * @return the name of the city.
     */
    String getName();

    /**
     * @return the id of the city.
     */
    int getId();

    /**
     * @return the coordinates of the city.
     */
    Pair<Double, Double> getCoordinates();

    /**
     * @return the radius of the city.
     */
    Double getRadius();

    /**
     * @return the string representation of the city.
     */
    @Override
    String toString();
}

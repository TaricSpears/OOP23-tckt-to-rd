package it.unibo.model.city.api;

import it.unibo.commons.Pair;

public interface City {
    String getName();

    int getId();

    Pair<Double, Double> getCoordinates();

    Double getRadius();

    boolean equals(City toCheck);

    String toString();
}

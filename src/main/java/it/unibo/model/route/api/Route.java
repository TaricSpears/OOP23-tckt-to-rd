package it.unibo.model.route.api;

import java.util.Set;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.carriage.impl.Carriage;

import java.awt.Color;

public interface Route {

    Pair<City, City> getConnectedCity();

    boolean isCompleted();

    void setFilled();

    int getScore();

    Color getColor();

    int getId();

    Set<Carriage> getRailUnits();

    boolean equals (Route toCheck);

    String toString();

}

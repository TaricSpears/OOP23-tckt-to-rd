package it.unibo.model.route.api;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import java.awt.Color;

public interface Route {

    Pair<City, City> getConnectedCity();

    boolean isCompleted();

    void setFilled();

    int getScore();

    int getLength();

    Color getColor();
}

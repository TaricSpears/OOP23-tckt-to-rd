package it.unibo.model.route.api;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;

public interface Route {

    Pair<City, City> getConnectedCity();

    boolean isCompleted();

    void setFilled();

    int getScore();
}

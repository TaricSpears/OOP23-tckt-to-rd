package it.unibo.model.city.api;

import it.unibo.model.route.api.Route;

import java.util.Set;

public interface City {
    String getName();

    Set<Route> getRoutes();
}

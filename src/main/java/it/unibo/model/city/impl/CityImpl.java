package it.unibo.model.city.impl;

import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;

import java.util.Set;
import java.util.Collections;

public class CityImpl implements City {

    private final String name;
    private final Set<Route> outgoingRoutes;

    public CityImpl(final String name, final Set<Route> outgoingRoutes) {
        this.name = name;
        this.outgoingRoutes = outgoingRoutes;
    }

    public String getName() {
        return this.name;
    }

    public Set<Route> getRoutes() {
        return Collections.unmodifiableSet(this.outgoingRoutes);
    }

}

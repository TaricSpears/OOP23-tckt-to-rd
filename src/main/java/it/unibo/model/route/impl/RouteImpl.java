package it.unibo.model.route.impl;

import org.jgrapht.graph.DefaultEdge;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;

public class RouteImpl extends DefaultEdge implements Route {

    private final Pair<City, City> connectedCity;
    private boolean filled;

    public RouteImpl(final Pair<City, City> connectedCity) {
        this.connectedCity = connectedCity;
        this.filled = false;
    }

    public Pair<City, City> getConnectedCity() {
        return new Pair<City, City>(this.connectedCity.first(), this.connectedCity.second());
    }

    public boolean isCompleted() {
        return this.filled;
    }

    public void setFilled() {
        this.filled = true;
    }
}

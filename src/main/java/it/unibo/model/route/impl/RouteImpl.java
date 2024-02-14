package it.unibo.model.route.impl;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;

public class RouteImpl extends DefaultWeightedEdge implements Route {

    private final EdgeData connectedCity;
    private boolean filled;

    public RouteImpl(final EdgeData connectedCity) {
        this.connectedCity = connectedCity;
        this.filled = false;
    }

    public Pair<City, City> getConnectedCity() {
        return new Pair<City, City>(this.connectedCity.city1(), this.connectedCity.city2());
    }

    public boolean isCompleted() {
        return this.filled;
    }

    public void setFilled() {
        this.filled = true;
    }

    public int getScore() {
        return this.connectedCity.weight();
    }
}

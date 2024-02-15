package it.unibo.model.route.impl;

import java.util.Set;

import java.awt.Color;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.unibo.view.Shape;
import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;

public class RouteImpl extends DefaultWeightedEdge implements Route {

    private final EdgeData connectedCity;
    private boolean filled;
    private final int id;
    private final Set<Shape> railUnits;
    private Color color;

    public RouteImpl(final EdgeData connectedCity, final Color color, final int id, final Set<Shape> railUnits) {
        this.connectedCity = connectedCity;
        this.filled = false;
        this.color = color;
        this.id = id;
        this.railUnits = railUnits;
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

    @Override
    public int getLength() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLength'");
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
package it.unibo.model.route.impl;

<<<<<<< HEAD
import java.util.Set;
=======
import java.awt.Color;
>>>>>>> f9773e118587a7e343e7d61e8402ef1ecbaaa727

import org.jgrapht.graph.DefaultWeightedEdge;

import it.unibo.view.Shape;
import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;

public class RouteImpl extends DefaultWeightedEdge implements Route {

    private final EdgeData connectedCity;
    private boolean filled;
<<<<<<< HEAD
    private final int id;
    private final Set<Shape> railUnits;

    public RouteImpl(final EdgeData connectedCity, final int id, final Set<Shape> railUnits) {
        this.connectedCity = connectedCity;
        this.filled = false;
        this.id = id;
        this.railUnits = railUnits;
=======
    private Color color;

    public RouteImpl(final EdgeData connectedCity, final Color color) {
        this.connectedCity = connectedCity;
        this.filled = false;
        this.color = color;
>>>>>>> f9773e118587a7e343e7d61e8402ef1ecbaaa727
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

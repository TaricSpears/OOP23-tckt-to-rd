package it.unibo.model.route.impl;

import java.util.Objects;
import java.util.Set;

import java.awt.Color;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;
import it.unibo.model.carriage.impl.Carriage;
import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;

/**
 * Implementation of {@link Route}, extends{@link DefaultWeightedEdge}.
 *
 * Represents a route in the game.
 */
public class RouteImpl extends DefaultWeightedEdge implements Route {

    private static final long serialVersionUID = 567742502623265945L;

    private final EdgeData connectedCity;
    private boolean filled;
    private final int id;
    private final Set<Carriage> railUnits;
    private final Color color;

    /**
     * Constructor for the route.
     * 
     * @param connectedCity
     * @param color
     * @param id
     * @param railUnits
     */
    public RouteImpl(final EdgeData connectedCity, final Color color, final int id, final Set<Carriage> railUnits) {
        this.connectedCity = connectedCity;
        this.filled = false;
        this.color = color;
        this.id = id;
        this.railUnits = railUnits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<City, City> getConnectedCity() {
        return new Pair<City, City>(this.connectedCity.city1(), this.connectedCity.city2());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCompleted() {
        return this.filled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFilled() {
        this.filled = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.connectedCity.weight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Carriage> getRailUnits() {
        return Set.copyOf(this.railUnits);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Route toCheck) {
        return this.id == toCheck.getId()
                && this.getConnectedCity().first().equals(toCheck.getConnectedCity().first())
                && this.getConnectedCity().second().equals(toCheck.getConnectedCity().second())
                && this.getScore() == toCheck.getScore()
                && Objects.equals(this.color, toCheck.getColor());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "\tROUTE ID = " + this.id
                + "\nCITY1: " + this.connectedCity.city1()
                + "\nCITY2:  " + this.connectedCity.city2()
                + "\nWEIGHT = " + this.connectedCity.weight()
                + "\nCOLOR = " + this.color;
    }
}

package it.unibo.model.route.impl;

import java.util.Objects;
import java.util.Set;

import java.awt.Color;

import org.jgrapht.graph.DefaultWeightedEdge;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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

    private final transient EdgeData connectedCity;
    private boolean filled;
    private final int id;
    private final transient Set<Carriage> railUnits;
    private final Color color;

    /**
     * Constructor for the route.
     * 
     * @param connectedCity
     * @param color
     * @param id
     * @param railUnits
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need to expose the railUnits")
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
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final RouteImpl route = (RouteImpl) obj;
        return this.id == route.getId()
                && this.getConnectedCity().first().equals(route.getConnectedCity().first())
                && this.getConnectedCity().second().equals(route.getConnectedCity().second())
                && this.getScore() == route.getScore()
                && Objects.equals(this.color, route.getColor());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
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

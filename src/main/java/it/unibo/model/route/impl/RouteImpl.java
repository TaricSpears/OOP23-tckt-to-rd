package it.unibo.model.route.impl;

import java.util.Set;

import java.awt.Color;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.unibo.commons.EdgeData;
import it.unibo.commons.Pair;
import it.unibo.model.carriage.impl.Carriage;
import it.unibo.model.city.api.City;
import it.unibo.model.player.api.Player;
import it.unibo.model.route.api.Route;

public class RouteImpl extends DefaultWeightedEdge implements Route {

    private final EdgeData connectedCity;
    private boolean filled;
    private final int id;
    private final Set<Carriage> railUnits;
    private Color color;
    private Player player;

    public RouteImpl(final EdgeData connectedCity, final Color color, final int id, final Set<Carriage> railUnits) {
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
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }
}
package it.unibo.model.city.impl;

import it.unibo.commons.Pair;
import it.unibo.model.city.api.City;

public class CityImpl implements City {
    private final double radius;
    private final String name;
    private final int id;
    private final Pair<Double, Double> coordinates;

    public CityImpl(final String name, final int id, final Pair<Double, Double> coords, final double radius) {
        this.name = name;
        this.id = id;
        this.coordinates = coords;
        this.radius = radius;
    }

    public CityImpl(final String name){
        this(name, 0, new Pair<>(0.0, 0.0), 0);
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Pair<Double, Double> getCoordinates() {
        return this.coordinates;
    }

    @Override
    public Double getRadius() {
        return this.radius;
    }

}
